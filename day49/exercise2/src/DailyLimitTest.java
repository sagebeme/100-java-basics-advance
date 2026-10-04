import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DailyLimitTest {

    private final LocalDate today = LocalDate.of(2026, 10, 4);
    private final List<LocalDate> history = List.of(today.minusDays(1), today, today, today.minusDays(1), today);

    @Test
    void countsOnlyThatDay() {
        assertEquals(3, DailyLimit.sentOn(history, today));
        assertEquals(0, DailyLimit.sentOn(history, today.plusDays(1)));
    }

    @Test
    void neverGoesBelowZero() {
        assertEquals(2, DailyLimit.remaining(history, today, 5));
        assertEquals(0, DailyLimit.remaining(history, today, 2));
        assertEquals(5, DailyLimit.remaining(List.of(), today, 5));
    }
}
