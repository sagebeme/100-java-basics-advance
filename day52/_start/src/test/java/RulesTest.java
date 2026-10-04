import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RulesTest {

    @Test
    void spotsSpamAccounts() {
        assertTrue(SpamFilter.looksReal(new Follower("wanjiru.codes", 58, 960, false)));
        assertFalse(SpamFilter.looksReal(new Follower("free_phones_4u", 0, 9, false)));
        assertFalse(SpamFilter.looksReal(new Follower("xx_win_now_xx", 1, 4, false)));
        assertFalse(SpamFilter.looksReal(new Follower("promo_kenya", 40, 900, false)));
        assertFalse(SpamFilter.looksReal(new Follower("user48213", 12, 300, false)));
    }

    @Test
    void backoffDoublesUpToTheCapAndResets() {
        Backoff b = new Backoff(Duration.ofSeconds(1), Duration.ofSeconds(5));
        assertEquals(1000, b.nextDelay().toMillis());
        assertEquals(2000, b.nextDelay().toMillis());
        assertEquals(4000, b.nextDelay().toMillis());
        assertEquals(5000, b.nextDelay().toMillis());
        assertEquals(5000, b.nextDelay().toMillis());
        b.reset();
        assertEquals(1000, b.nextDelay().toMillis());
    }
}
