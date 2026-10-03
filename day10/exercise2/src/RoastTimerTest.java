import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoastTimerTest {

    @Test
    void worksOutTheSecondsToFirstCrack() {
        assertEquals(460, RoastTimer.secondsToFirstCrack(150, 6));
    }

    @Test
    void roundsUpToAWholeSecond() {
        assertEquals(461, RoastTimer.secondsToFirstCrack(150, 5.99)); // 460.77 seconds
    }

    @Test
    void alreadyHotEnoughIsZero() {
        assertEquals(0, RoastTimer.secondsToFirstCrack(196, 6));
        assertEquals(0, RoastTimer.secondsToFirstCrack(205, 6));
    }

    @Test
    void noHeatMeansNever() {
        assertEquals(-1, RoastTimer.secondsToFirstCrack(150, 0));
        assertEquals(-1, RoastTimer.secondsToFirstCrack(150, -2));
    }

    @Test
    void showsMinutesAndPaddedSeconds() {
        assertEquals("7:40", RoastTimer.clock(460));
        assertEquals("1:05", RoastTimer.clock(65));
        assertEquals("0:00", RoastTimer.clock(0));
    }
}
