package com.learning.tikiti;

import com.learning.tikiti.RefreshTokenService.Failure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class RefreshTokenServiceTest {

    private MutableClock clock;
    private RefreshTokenService service;

    @BeforeEach
    void setUp() {
        clock = new MutableClock(Instant.parse("2026-10-05T09:00:00Z"));
        service = new RefreshTokenService(clock);
    }

    @Test
    void issuesAnUnguessableTokenAndStoresOnlyItsHash() {
        String token = service.issue("amina");
        assertTrue(token.matches("[\\w-]{43}"), token);
        assertEquals(1, service.storedKeys().size());
        assertTrue(service.storedKeys().contains(RefreshTokenService.hash(token)));
        assertFalse(service.storedKeys().contains(token));
    }

    @Test
    void rotatingUsesTheTokenUpAndGivesANewOne() {
        String first = service.issue("amina");
        var rotated = service.rotate(first);
        assertTrue(rotated.ok());
        assertEquals("amina", rotated.username());
        assertNotEquals(first, rotated.token());
        assertTrue(service.rotate(rotated.token()).ok());
    }

    @Test
    void aUsedUpTokenComingBackRevokesTheWholeFamily() {
        String stolenCopy = service.issue("amina");
        var rotated = service.rotate(stolenCopy); // the real user refreshes
        assertEquals(Failure.REUSED, service.rotate(stolenCopy).failure()); // the thief tries the old one
        assertEquals(Failure.REVOKED, service.rotate(rotated.token()).failure()); // and the real user must log in again
    }

    @Test
    void otherDevicesKeepWorking() {
        String phone = service.issue("amina");
        String laptop = service.issue("amina");
        service.rotate(phone);
        service.rotate(phone); // the phone's family is now revoked
        assertTrue(service.rotate(laptop).ok());
    }

    @Test
    void expiresAfterThirtyDaysAndDoesNotKnowMadeUpTokens() {
        String token = service.issue("amina");
        clock.advance(Duration.ofDays(30));
        assertEquals(Failure.EXPIRED, service.rotate(token).failure());
        assertEquals(Failure.UNKNOWN, service.rotate("made-up").failure());
        assertEquals(Failure.UNKNOWN, service.rotate(null).failure());
    }

    @Test
    void revokeLogsThatDeviceOutForGood() {
        String token = service.issue("amina");
        service.revoke(token);
        assertEquals(Failure.REVOKED, service.rotate(token).failure());
    }
}
