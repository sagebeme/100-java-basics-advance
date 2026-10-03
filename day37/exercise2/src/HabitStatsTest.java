import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HabitStatsTest {

    private static final LocalDate TODAY = LocalDate.of(2026, 10, 3); // Saturday

    @Test
    void ratesOnlyTheWindow() {
        Set<LocalDate> done = new HashSet<>();
        for (int i = 0; i < 24; i++) done.add(TODAY.minusDays(i));
        done.add(TODAY.minusDays(45));
        assertEquals(80, HabitStats.rate(done, TODAY, 30));
        assertEquals(0, HabitStats.rate(Set.of(), TODAY, 30));
    }

    @Test
    void roundsToTheNearestPercent() {
        assertEquals(33, HabitStats.rate(Set.of(TODAY), TODAY, 3));
        assertEquals(67, HabitStats.rate(Set.of(TODAY, TODAY.minusDays(1)), TODAY, 3));
    }

    @Test
    void findsTheBestWeekday() {
        Set<LocalDate> done = Set.of(
                LocalDate.of(2026, 9, 14), LocalDate.of(2026, 9, 21), LocalDate.of(2026, 9, 28),        // 3 Mondays
                LocalDate.of(2026, 9, 12), LocalDate.of(2026, 9, 19), LocalDate.of(2026, 9, 26), TODAY, // 4 Saturdays
                LocalDate.of(2026, 9, 27));                                                             // 1 Sunday
        assertEquals(DayOfWeek.SATURDAY, HabitStats.bestWeekday(done));
    }

    @Test
    void theEarlierDayWinsATie() {
        assertEquals(DayOfWeek.MONDAY, HabitStats.bestWeekday(Set.of(LocalDate.of(2026, 9, 28), TODAY)));
        assertNull(HabitStats.bestWeekday(Set.of()));
    }
}
