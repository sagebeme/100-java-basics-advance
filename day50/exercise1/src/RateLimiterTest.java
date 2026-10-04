import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RateLimiterTest {

    static class FakeClock extends Clock {
        Instant now = Instant.parse("2026-10-04T08:00:00Z");
        public ZoneOffset getZone() { return ZoneOffset.UTC; }
        public Clock withZone(java.time.ZoneId z) { return this; }
        public Instant instant() { return now; }
    }

    @Test
    void allowsUpToTheLimitInAWindow() {
        FakeClock c = new FakeClock();
        RateLimiter r = new RateLimiter(2, Duration.ofMinutes(1), c);
        assertTrue(r.tryAcquire());
        assertTrue(r.tryAcquire());
        assertFalse(r.tryAcquire());
    }

    @Test
    void theWindowSlides() {
        FakeClock c = new FakeClock();
        RateLimiter r = new RateLimiter(2, Duration.ofMinutes(1), c);
        r.tryAcquire();
        c.now = c.now.plusSeconds(40);
        r.tryAcquire();
        c.now = c.now.plusSeconds(30);   // the first action is now 70 s old
        assertTrue(r.tryAcquire());
        assertFalse(r.tryAcquire());
    }

    @Test
    void aRefusedActionDoesntUseUpTheBudget() {
        FakeClock c = new FakeClock();
        RateLimiter r = new RateLimiter(1, Duration.ofMinutes(1), c);
        r.tryAcquire();
        for (int i = 0; i < 5; i++) assertFalse(r.tryAcquire());
        c.now = c.now.plusSeconds(61);
        assertTrue(r.tryAcquire());
    }
}
