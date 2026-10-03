import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeaveDaysTest {

    private final Set<LocalDate> holidays = Set.of(LocalDate.of(2026, 12, 25), LocalDate.of(2026, 12, 26), LocalDate.of(2027, 1, 1));

    @Test
    void aLongWeekendNeedsNoLeave() {
        assertEquals(0, LeaveDays.leaveDaysNeeded(LocalDate.of(2026, 12, 25), LocalDate.of(2026, 12, 27), holidays));
    }

    @Test
    void countsOnlyWorkingWeekdays() {
        assertEquals(2, LeaveDays.leaveDaysNeeded(LocalDate.of(2026, 12, 23), LocalDate.of(2026, 12, 27), holidays));
        assertEquals(4, LeaveDays.leaveDaysNeeded(LocalDate.of(2026, 12, 28), LocalDate.of(2027, 1, 1), holidays));
    }

    @Test
    void aOneDayTripCounts() {
        assertEquals(1, LeaveDays.leaveDaysNeeded(LocalDate.of(2026, 12, 23), LocalDate.of(2026, 12, 23), Set.of()));
    }

    @Test
    void refusesATripThatEndsBeforeItStarts() {
        assertThrows(IllegalArgumentException.class,
                () -> LeaveDays.leaveDaysNeeded(LocalDate.of(2026, 12, 27), LocalDate.of(2026, 12, 25), holidays));
    }
}
