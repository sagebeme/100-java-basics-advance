import java.time.Duration;

public class Backoff {

    private final Duration first;
    private final Duration cap;
    private int failures = 0;

    public Backoff(Duration first, Duration cap) {
        this.first = first;
        this.cap = cap;
    }

    // first, first×2, first×4 ... never more than cap
    public Duration nextDelay() {
        // TODO
        return first;
    }

    public void reset() {
        // TODO
    }
}
