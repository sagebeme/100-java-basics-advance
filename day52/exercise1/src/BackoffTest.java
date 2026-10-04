import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackoffTest {

    @Test
    void doublesEachTime() {
        Backoff b = new Backoff(Duration.ofMillis(500), Duration.ofMinutes(1));
        assertEquals(500, b.nextDelay().toMillis());
        assertEquals(1000, b.nextDelay().toMillis());
        assertEquals(2000, b.nextDelay().toMillis());
    }

    @Test
    void neverPassesTheCapEvenAfterManyFailures() {
        Backoff b = new Backoff(Duration.ofSeconds(1), Duration.ofSeconds(30));
        for (int i = 0; i < 100; i++) b.nextDelay();
        assertEquals(30_000, b.nextDelay().toMillis());
    }

    @Test
    void startsAgainAfterASuccess() {
        Backoff b = new Backoff(Duration.ofSeconds(1), Duration.ofSeconds(30));
        b.nextDelay();
        b.nextDelay();
        b.reset();
        assertEquals(1000, b.nextDelay().toMillis());
    }
}
