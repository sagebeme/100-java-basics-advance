import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PomodoroCycleTest {

    @Test
    void startsInWorkPhase() {
        PomodoroCycle cycle = new PomodoroCycle(4);
        assertEquals(PomodoroCycle.Phase.WORK, cycle.getPhase());
    }

    @Test
    void goesToAShortBreakAfterWorkSession() {
        PomodoroCycle cycle = new PomodoroCycle(4);
        cycle.advance();
        assertEquals(PomodoroCycle.Phase.SHORT_BREAK, cycle.getPhase());
    }

    @Test
    void returnsToWorkAfterAShortBreak() {
        PomodoroCycle cycle = new PomodoroCycle(4);
        cycle.advance(); // work -> short break
        cycle.advance(); // short break -> work
        assertEquals(PomodoroCycle.Phase.WORK, cycle.getPhase());
    }

    @Test
    void takesALongBreakAfterTheConfiguredNumberOfSessions() {
        PomodoroCycle cycle = new PomodoroCycle(2);
        cycle.advance(); // work 1 -> short break
        cycle.advance(); // -> work
        cycle.advance(); // work 2 -> long break
        assertEquals(PomodoroCycle.Phase.LONG_BREAK, cycle.getPhase());
        assertEquals(2, cycle.getCompletedWorkSessions());
    }
}
