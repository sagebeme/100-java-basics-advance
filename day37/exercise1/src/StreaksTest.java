import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreaksTest {

    private final Set<LocalDate> done = Set.of(LocalDate.of(2026, 9, 28), LocalDate.of(2026, 9, 29),
            LocalDate.of(2026, 9, 30), LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 3));

    @Test
    void countsBackFromToday() {
        assertEquals(2, Streaks.current(done, LocalDate.of(2026, 10, 3)));
    }

    @Test
    void countsFromYesterdayWhenTodayIsntDoneYet() {
        assertEquals(2, Streaks.current(done, LocalDate.of(2026, 10, 4)));
        assertEquals(0, Streaks.current(done, LocalDate.of(2026, 10, 5)));
    }

    @Test
    void findsTheLongestRun() {
        assertEquals(3, Streaks.longest(done));
        assertEquals(0, Streaks.longest(Set.of()));
        assertEquals(1, Streaks.longest(Set.of(LocalDate.of(2026, 1, 1))));
    }

    @Test
    void aRunCanCrossAMonthAndAYearEnd() {
        assertEquals(3, Streaks.longest(Set.of(LocalDate.of(2025, 12, 31), LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 2))));
    }
}
