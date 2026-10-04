import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaiterTest {

    // A clock that only moves when the fake sleeper "sleeps", so tests run instantly.
    static class FakeTime extends Clock {
        Instant now = Instant.parse("2026-10-04T08:00:00Z");
        int sleeps = 0;
        @Override public ZoneOffset getZone() { return ZoneOffset.UTC; }
        @Override public Clock withZone(java.time.ZoneId zone) { return this; }
        @Override public Instant instant() { return now; }
        void sleep(Duration d) { now = now.plus(d); sleeps++; }
    }

    @Test
    void returnsAtOnceWhenTheConditionIsAlreadyTrue() throws InterruptedException {
        FakeTime t = new FakeTime();
        assertTrue(new Waiter(t, t::sleep).until(() -> true, Duration.ofSeconds(10), Duration.ofMillis(500)));
        assertEquals(0, t.sleeps);
    }

    @Test
    void keepsCheckingUntilItBecomesTrue() throws InterruptedException {
        FakeTime t = new FakeTime();
        Instant ready = t.now.plusMillis(1200);
        assertTrue(new Waiter(t, t::sleep).until(() -> !t.now.isBefore(ready), Duration.ofSeconds(10), Duration.ofMillis(500)));
        assertEquals(3, t.sleeps); // checked at 0, 500, 1000 and 1500 ms
    }

    @Test
    void givesUpAfterTheTimeout() throws InterruptedException {
        FakeTime t = new FakeTime();
        assertFalse(new Waiter(t, t::sleep).until(() -> false, Duration.ofSeconds(2), Duration.ofMillis(500)));
        assertEquals(4, t.sleeps);
    }
}
