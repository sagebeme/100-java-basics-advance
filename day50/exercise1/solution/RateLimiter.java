import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

// At most `max` actions in any window of `per`. tryAcquire() says whether one more is allowed right now.
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

    public boolean tryAcquire() {
        Instant now = clock.instant();
        while (!recent.isEmpty() && !recent.peekFirst().isAfter(now.minus(per))) recent.removeFirst();
        if (recent.size() >= max) return false;
        recent.addLast(now);
        return true;
    }
}
