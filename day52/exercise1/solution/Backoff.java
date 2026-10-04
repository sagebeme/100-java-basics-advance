import java.time.Duration;

// Exponential backoff: wait 1 s, then 2, 4, 8... up to a cap. Each refusal doubles the wait,
// which gives a busy site room to recover instead of hammering it.
public class Backoff {

    private final Duration first;
    private final Duration cap;
    private int failures = 0;

    public Backoff(Duration first, Duration cap) {
        this.first = first;
        this.cap = cap;
    }

    public Duration nextDelay() {
        long millis = first.toMillis() * (1L << Math.min(failures, 20));
        failures++;
        return Duration.ofMillis(Math.min(millis, cap.toMillis()));
    }

    public void reset() {
        failures = 0;
    }
}
