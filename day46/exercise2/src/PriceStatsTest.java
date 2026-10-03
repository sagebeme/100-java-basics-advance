import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceStatsTest {

    private final List<Integer> history = List.of(18999, 18999, 21999, 21999);

    @Test
    void howFarBelowTheHighest() {
        assertEquals(15.9, PriceStats.percentBelowHigh(18499, history), 0.0001);
        assertEquals(0.0, PriceStats.percentBelowHigh(22999, history), 0.0001);
        assertEquals(0.0, PriceStats.percentBelowHigh(500, List.of()), 0.0001);
    }

    @Test
    void lowestYetMeansStrictlyLower() {
        assertTrue(PriceStats.isLowestYet(18499, history));
        assertFalse(PriceStats.isLowestYet(18999, history));
        assertTrue(PriceStats.isLowestYet(100, List.of()));
    }
}
