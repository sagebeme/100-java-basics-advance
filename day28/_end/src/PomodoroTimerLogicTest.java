import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PomodoroTimerLogicTest {

    @Test
    void formatsAFullTwentyFiveMinutes() {
        assertEquals("25:00", PomodoroTimerLogic.formatTime(25 * 60));
    }

    @Test
    void padsSingleDigitSeconds() {
        assertEquals("00:05", PomodoroTimerLogic.formatTime(5));
    }

    @Test
    void formatsZeroAsAllZeroes() {
        assertEquals("00:00", PomodoroTimerLogic.formatTime(0));
    }
}
