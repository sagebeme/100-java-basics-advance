import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountdownTimerTest {

    @Test
    void doesNotCountDownUntilStarted() {
        CountdownTimer timer = new CountdownTimer(10);
        timer.tick();
        assertEquals(10, timer.getSecondsLeft());
    }

    @Test
    void countsDownOnceStarted() {
        CountdownTimer timer = new CountdownTimer(10);
        timer.start();
        timer.tick();
        assertEquals(9, timer.getSecondsLeft());
    }

    @Test
    void pausingStopsTheCountdown() {
        CountdownTimer timer = new CountdownTimer(10);
        timer.start();
        timer.tick();
        timer.pause();
        timer.tick();
        assertEquals(9, timer.getSecondsLeft());
    }

    @Test
    void isCompleteOnceItReachesZero() {
        CountdownTimer timer = new CountdownTimer(1);
        timer.start();
        timer.tick();
        assertTrue(timer.isComplete());
    }

    @Test
    void doesNotGoNegative() {
        CountdownTimer timer = new CountdownTimer(0);
        timer.start();
        timer.tick();
        assertEquals(0, timer.getSecondsLeft());
    }

    @Test
    void formatsSecondsAsMinutesAndSeconds() {
        assertEquals("05:00", CountdownTimer.format(300));
        assertEquals("00:09", CountdownTimer.format(9));
    }
}
