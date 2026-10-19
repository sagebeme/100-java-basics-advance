package com.learning.tikiti;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public static final String REFRESH_COOKIE = "refresh";

    public record LoginRequest(String username, String password) {}

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService users;
    private final TokenService tokens;
    private final RefreshTokenService refreshTokens;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService users, TokenService tokens, RefreshTokenService refreshTokens) {
        this.authenticationManager = authenticationManager;
        this.users = users;
        this.tokens = tokens;
        this.refreshTokens = refreshTokens;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        } catch (AuthenticationException e) {
            // The same answer whether the username or the password was wrong.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Username or password is wrong"));
        }
        String username = authentication.getName();
        return withTokens(username, refreshTokens.issue(username));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refresh(@CookieValue(name = REFRESH_COOKIE, required = false) String refreshToken) {
        RefreshTokenService.Rotation rotation = refreshTokens.rotate(refreshToken);
        if (!rotation.ok()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header(HttpHeaders.SET_COOKIE, refreshCookie("", Duration.ZERO).toString())
                    .body(Map.of("error", "Log in again"));
        }
        return withTokens(rotation.username(), rotation.token());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue(name = REFRESH_COOKIE, required = false) String refreshToken) {
        refreshTokens.revoke(refreshToken);
        return ResponseEntity.noContent().header(HttpHeaders.SET_COOKIE, refreshCookie("", Duration.ZERO).toString()).build();
    }

    private ResponseEntity<Map<String, Object>> withTokens(String username, String refreshToken) {
        String role = users.loadUserByUsername(username).getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshCookie(refreshToken, RefreshTokenService.LIFETIME).toString())
                .body(Map.of(
                        "accessToken", tokens.accessTokenFor(username, role),
                        "tokenType", "Bearer",
                        "expiresIn", TokenService.ACCESS_TOKEN_LIFETIME.toSeconds()));
    }

    private static ResponseCookie refreshCookie(String value, Duration maxAge) {
        return ResponseCookie.from(REFRESH_COOKIE, value)
                .httpOnly(true)      // page JavaScript never sees it
                .secure(true)        // HTTPS only
                .sameSite("Strict")  // never sent when another site starts the request
                .path("/auth")       // only sent to /auth/*, not with every API call
                .maxAge(maxAge)
                .build();
    }
}
