import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountdownTimerTest {

    @Test
    void aNewTimerWaitsToBeStarted() {
        CountdownTimer timer = new CountdownTimer(60);
        assertFalse(timer.isRunning());
        timer.tick();
        assertEquals(60, timer.getSecondsLeft());
    }

    @Test
    void countsDownWhileRunning() {
        CountdownTimer timer = new CountdownTimer(60);
        timer.start();
        timer.tick();
        timer.tick();
        assertEquals(58, timer.getSecondsLeft());
        assertTrue(timer.isRunning());
    }

    @Test
    void pausingStopsTheCountAndStartingCarriesOn() {
        CountdownTimer timer = new CountdownTimer(60);
        timer.start();
        timer.tick();
        timer.pause();
        timer.tick();
        timer.tick();
        assertEquals(59, timer.getSecondsLeft());
        timer.start();
        timer.tick();
        assertEquals(58, timer.getSecondsLeft());
    }

    @Test
    void ringsOnceAtZeroThenStops() {
        CountdownTimer timer = new CountdownTimer(2);
        timer.start();
        assertFalse(timer.tick());
        assertTrue(timer.tick());
        assertTrue(timer.isComplete());
        assertFalse(timer.isRunning());
        assertFalse(timer.tick());
        assertEquals(0, timer.getSecondsLeft());
    }

    @Test
    void aFinishedTimerOnlyStartsAgainAfterAReset() {
        CountdownTimer timer = new CountdownTimer(1);
        timer.start();
        timer.tick();
        timer.start();
        assertFalse(timer.isRunning());
        timer.reset();
        assertEquals(1, timer.getSecondsLeft());
        assertFalse(timer.isComplete());
        timer.start();
        assertTrue(timer.isRunning());
    }

    @Test
    void formatsMinutesAndSeconds() {
        assertEquals("25:00", CountdownTimer.format(1500));
        assertEquals("01:05", CountdownTimer.format(65));
        assertEquals("00:00", CountdownTimer.format(0));
        assertEquals("75:00", CountdownTimer.format(4500));
    }

    @Test
    void refusesATimerOfNothing() {
        assertThrows(IllegalArgumentException.class, () -> new CountdownTimer(0));
    }
}
