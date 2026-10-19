package com.learning.tikiti;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EventController {

    // Answered from the token alone: no database lookup. That's the speed a JWT buys.
    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt) {
        return Map.of("username", jwt.getSubject(), "role", jwt.getClaimAsString("role"));
    }

    // SecurityConfig only lets organisers through. A fan gets 403: logged in, but not allowed.
    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createEvent() {
        return Map.of("created", true);
    }
}
