import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrategyTest {

    private final List<Upgrade> shop = List.of(
            new Upgrade("pan", 15, 1, 0),      // pays back in 15 s
            new Upgrade("helper", 100, 5, 0),  // 20 s
            new Upgrade("jiko", 600, 24, 0));  // 25 s

    @Test
    void buysTheFastestPaybackItCanAfford() {
        assertEquals("pan", Strategy.bestBuy(shop, 700).orElseThrow().id());
        List<Upgrade> pricierPans = List.of(new Upgrade("pan", 40, 1, 6), new Upgrade("helper", 100, 5, 0));
        assertEquals("helper", Strategy.bestBuy(pricierPans, 150).orElseThrow().id());
    }

    @Test
    void buysNothingItCantAfford() {
        assertTrue(Strategy.bestBuy(shop, 10).isEmpty());
    }

    @Test
    void savesUpWhenTheBetterDealIsCloseEnough() {
        List<Upgrade> s = List.of(new Upgrade("pan", 60, 1, 10), new Upgrade("helper", 100, 5, 0));
        assertTrue(Strategy.shouldSaveFor(s, 90, 10));   // helper is 1 s away; a pan takes 60 s to pay back
        assertFalse(Strategy.shouldSaveFor(s, 90, 0));   // with no income, waiting gets you nowhere
        assertFalse(Strategy.shouldSaveFor(s, 100, 10)); // we can already afford the best one
    }
}
