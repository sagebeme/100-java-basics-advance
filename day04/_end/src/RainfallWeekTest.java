import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RainfallWeekTest {

    private final double[] week = {12.5, 0.0, 3.2, 8.0, 0.4, 6.9, 0.0};

    @Test
    void addsUpTheWholeWeek() {
        assertEquals(31.0, RainfallWeek.total(week), 0.0001);
    }

    @Test
    void averagesOverSevenDays() {
        assertEquals(31.0 / 7, RainfallWeek.average(week), 0.0001);
    }

    @Test
    void theFirstOfTwoEquallyDryDaysWins() {
        assertEquals("Tuesday", RainfallWeek.driestDay(week));
    }

    @Test
    void findsADriestDayAtTheEndOfTheWeek() {
        assertEquals("Sunday", RainfallWeek.driestDay(new double[]{5, 4, 3, 2, 2.5, 1.5, 0.5}));
    }

    @Test
    void countsDaysWithAtLeastTheGivenRain() {
        assertEquals(4, RainfallWeek.rainyDays(week, 1.0));
        assertEquals(2, RainfallWeek.rainyDays(week, 8.0));
    }
}
