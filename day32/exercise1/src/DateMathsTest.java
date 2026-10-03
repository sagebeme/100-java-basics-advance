import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateMathsTest {

    private final LocalDate born = LocalDate.of(1991, 10, 2);

    @Test
    void ageGoesUpOnTheBirthdayItself() {
        assertEquals(34, DateMaths.ageOn(born, LocalDate.of(2026, 10, 1)));
        assertEquals(35, DateMaths.ageOn(born, LocalDate.of(2026, 10, 2)));
    }

    @Test
    void countsTheDaysToTheNextBirthday() {
        assertEquals(2, DateMaths.daysUntilNextBirthday(born, LocalDate.of(2026, 9, 30)));
        assertEquals(0, DateMaths.daysUntilNextBirthday(born, LocalDate.of(2026, 10, 2)));
        assertEquals(364, DateMaths.daysUntilNextBirthday(born, LocalDate.of(2026, 10, 3)));
    }

    @Test
    void aLeapDayBirthdayFallsOnThe28thInOrdinaryYears() {
        assertEquals(1, DateMaths.daysUntilNextBirthday(LocalDate.of(1996, 2, 29), LocalDate.of(2027, 2, 27)));
    }

    @Test
    void writesADateInFull() {
        assertEquals("Friday, 2 October 2026", DateMaths.friendly(LocalDate.of(2026, 10, 2)));
    }
}
