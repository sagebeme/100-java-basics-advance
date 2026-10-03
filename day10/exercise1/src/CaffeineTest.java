import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CaffeineTest {

    @Test
    void knowsEachDrink() {
        assertEquals(63, Caffeine.caffeineMg("espresso"));
        assertEquals(95, Caffeine.caffeineMg("filter"));
        assertEquals(62, Caffeine.caffeineMg("instant"));
        assertEquals(40, Caffeine.caffeineMg("chai"));
        assertEquals(3, Caffeine.caffeineMg("decaf"));
    }

    @Test
    void ignoresCapitalsAndSpaces() {
        assertEquals(63, Caffeine.caffeineMg("  Espresso "));
    }

    @Test
    void anUnknownDrinkHasNone() {
        assertEquals(0, Caffeine.caffeineMg("water"));
    }

    @Test
    void aNormalDayIsUnderTheLimit() {
        assertFalse(Caffeine.overDailyLimit("filter", "filter", "chai"));
        assertFalse(Caffeine.overDailyLimit());
    }

    @Test
    void tooManyCupsGoOverTheLimit() {
        assertTrue(Caffeine.overDailyLimit("filter", "filter", "filter", "filter", "espresso"));
    }
}
