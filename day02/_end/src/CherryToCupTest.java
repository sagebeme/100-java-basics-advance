import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CherryToCupTest {

    @Test
    void sixKilosOfCherryMakeOneKiloOfGreenBeans() {
        assertEquals(1.0, CherryToCup.greenKg(6), 0.0001);
    }

    @Test
    void roastingKeepsEightyFivePercentOfTheWeight() {
        assertEquals(8.5, CherryToCup.roastedKg(10), 0.0001);
    }

    @Test
    void aKiloOfRoastedCoffeeMakesAHundredCups() {
        assertEquals(100, CherryToCup.cups(1.0));
    }

    @Test
    void aPartCupIsDroppedNotRoundedUp() {
        assertEquals(332, CherryToCup.cups(3.3299));
    }

    @Test
    void summarisesTheWholeJourney() {
        assertEquals("60.0 kg of cherry makes 10.00 kg of green beans, 8.50 kg roasted, about 850 cups.",
                CherryToCup.summary(60));
    }
}
