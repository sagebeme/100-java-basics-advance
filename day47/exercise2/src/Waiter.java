import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.function.BooleanSupplier;

public class Waiter {

    public interface Sleeper {
        void sleep(Duration d) throws InterruptedException;
    }

    private final Clock clock;
    private final Sleeper sleeper;

    public Waiter(Clock clock, Sleeper sleeper) {
        this.clock = clock;
        this.sleeper = sleeper;
    }

    public static Waiter real() {
        return new Waiter(Clock.systemUTC(), d -> Thread.sleep(d.toMillis()));
    }

    public boolean until(BooleanSupplier condition, Duration timeout, Duration poll) throws InterruptedException {
        // TODO: check, sleep, check again... until the condition is true or the timeout has passed
        return false;
    }
}
