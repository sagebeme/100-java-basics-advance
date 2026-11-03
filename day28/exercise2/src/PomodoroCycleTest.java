import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PomodoroCycleTest {

    @Test
    void startsOnTheFirstWorkSession() {
        PomodoroCycle cycle = PomodoroCycle.classic();
        assertEquals(PomodoroCycle.Phase.WORK, cycle.phase());
        assertEquals(25, cycle.minutes());
        assertEquals("Work 1 of 4", cycle.label());
        assertEquals(0, cycle.completedSessions());
    }

    @Test
    void workIsFollowedByAShortBreakThenWorkAgain() {
        PomodoroCycle cycle = PomodoroCycle.classic();
        cycle.advance();
        assertEquals(PomodoroCycle.Phase.SHORT_BREAK, cycle.phase());
        assertEquals(5, cycle.minutes());
        assertEquals("Short break", cycle.label());
        assertEquals(1, cycle.completedSessions());
        cycle.advance();
        assertEquals("Work 2 of 4", cycle.label());
        assertEquals(1, cycle.completedSessions());
    }

    @Test
    void everyFourthSessionEarnsALongBreakThenTheSetStartsAgain() {
        PomodoroCycle cycle = PomodoroCycle.classic();
        List<String> day = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            day.add(cycle.label());
            cycle.advance();
        }
        assertEquals(List.of(
                "Work 1 of 4", "Short break", "Work 2 of 4", "Short break", "Work 3 of 4",
                "Short break", "Work 4 of 4", "Long break", "Work 1 of 4", "Short break"), day);
    }

    @Test
    void theLongBreakHasItsOwnLength() {
        PomodoroCycle cycle = new PomodoroCycle(50, 10, 30, 2);
        cycle.advance();
        cycle.advance();
        cycle.advance();
        assertEquals(PomodoroCycle.Phase.LONG_BREAK, cycle.phase());
        assertEquals(30, cycle.minutes());
    }

    @Test
    void countsTheWorkDone() {
        PomodoroCycle cycle = new PomodoroCycle(50, 10, 30, 2);
        for (int i = 0; i < 5; i++) {
            cycle.advance();
        }
        assertEquals(3, cycle.completedSessions());
        assertEquals(150, cycle.workMinutesDone());
    }

    @Test
    void refusesNonsenseLengths() {
        assertThrows(IllegalArgumentException.class, () -> new PomodoroCycle(25, 0, 15, 4));
        assertThrows(IllegalArgumentException.class, () -> new PomodoroCycle(25, 5, 15, 0));
    }
}
