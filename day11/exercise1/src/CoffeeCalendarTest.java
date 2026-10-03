import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoffeeCalendarTest {

    @Test
    void octoberToDecemberIsTheMainCrop() {
        assertEquals("main crop", CoffeeCalendar.seasonFor(10));
        assertEquals("main crop", CoffeeCalendar.seasonFor(12));
    }

    @Test
    void aprilToJuneIsTheFlyCrop() {
        assertEquals("fly crop", CoffeeCalendar.seasonFor(4));
        assertEquals("fly crop", CoffeeCalendar.seasonFor(6));
    }

    @Test
    void theRestOfTheYearIsForGrowing() {
        assertEquals("growing", CoffeeCalendar.seasonFor(1));
        assertEquals("growing", CoffeeCalendar.seasonFor(7));
        assertEquals("growing", CoffeeCalendar.seasonFor(9));
    }

    @Test
    void refusesImpossibleMonths() {
        assertEquals("not a month", CoffeeCalendar.seasonFor(0));
        assertEquals("not a month", CoffeeCalendar.seasonFor(13));
    }

    @Test
    void sixMonthsOfTheYearAreHarvestMonths() {
        assertEquals(6, CoffeeCalendar.harvestMonths());
    }
}
