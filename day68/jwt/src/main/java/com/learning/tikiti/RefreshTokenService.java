package com.learning.tikiti;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// A refresh token is a random string, not a JWT, and only its hash is stored, like a password.
// Each refresh uses it up and issues a new one in the same family (one family per login, so per device).
// If a used-up token comes back, two people hold it: revoke the whole family.
@Service
public class RefreshTokenService {

    public static final Duration LIFETIME = Duration.ofDays(30);

    public enum Failure { UNKNOWN, EXPIRED, REUSED, REVOKED }

    public record Rotation(String token, String username, Failure failure) {
        public boolean ok() {
            return failure == null;
        }
    }

    private static final class Stored {
        final String username;
        final String family;
        final Instant expiresAt;
        boolean used;

        Stored(String username, String family, Instant expiresAt) {
            this.username = username;
            this.family = family;
            this.expiresAt = expiresAt;
        }
    }

    // In a real app these are database tables. Maps keep today about the rules, not the SQL.
    private final Map<String, Stored> tokens = new ConcurrentHashMap<>();
    private final Set<String> revokedFamilies = ConcurrentHashMap.newKeySet();
    private final SecureRandom random = new SecureRandom();
    private final Clock clock;

    public RefreshTokenService(Clock clock) {
        this.clock = clock;
    }

    public String issue(String username) {
        return issue(username, newToken());
    }

    private String issue(String username, String family) {
        String token = newToken();
        tokens.put(hash(token), new Stored(username, family, clock.instant().plus(LIFETIME)));
        return token;
    }

    // synchronized: two requests rotating the same token at the same moment must not both succeed.
    public synchronized Rotation rotate(String token) {
        Stored stored = token == null ? null : tokens.get(hash(token));
        if (stored == null) return failed(Failure.UNKNOWN);
        if (revokedFamilies.contains(stored.family)) return failed(Failure.REVOKED);
        if (stored.used) {
            revokedFamilies.add(stored.family); // a stolen copy: shut the whole family down
            return failed(Failure.REUSED);
        }
        if (!clock.instant().isBefore(stored.expiresAt)) return failed(Failure.EXPIRED);
        stored.used = true;
        return new Rotation(issue(stored.username, stored.family), stored.username, null);
    }

    public void revoke(String token) {
        Optional.ofNullable(token).map(t -> tokens.get(hash(t))).ifPresent(stored -> revokedFamilies.add(stored.family));
    }

    // What's actually stored: hashes only. The tests look in here.
    Set<String> storedKeys() {
        return Set.copyOf(tokens.keySet());
    }

    private static Rotation failed(Failure failure) {
        return new Rotation(null, null, failure);
    }

    private String newToken() {
        byte[] bytes = new byte[32]; // 256 random bits: unguessable
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    static String hash(String token) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(token.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
