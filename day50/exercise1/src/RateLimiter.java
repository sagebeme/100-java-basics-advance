import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

public class RateLimiter {

    private final int max;
    private final Duration per;
    private final Clock clock;
    private final Deque<Instant> recent = new ArrayDeque<>();

    public RateLimiter(int max, Duration per, Clock clock) {
        this.max = max;
        this.per = per;
        this.clock = clock;
    }

    // At most `max` actions in any window of `per`.
    public boolean tryAcquire() {
        // TODO: forget actions older than `per`; allow (and remember) this one if fewer than `max` remain
        return true;
    }
}
