import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PercentChangeTest {

    @Test
    void roundsHalfUpToTwoPlaces() {
        assertEquals(new BigDecimal("6.43"), PercentChange.percentChange("17.10", "18.20"));
        assertEquals(new BigDecimal("-6.05"), PercentChange.percentChange("38.00", "35.70"));
    }

    @Test
    void avoidsDoubleRoundingErrors() {
        assertEquals(new BigDecimal("200.00"), PercentChange.percentChange("0.10", "0.30"));
    }

    @Test
    void refusesAChangeFromZero() {
        assertThrows(IllegalArgumentException.class, () -> PercentChange.percentChange("0.00", "5"));
    }

    @Test
    void describesTheDirection() {
        assertEquals("▲ 6.43%", PercentChange.describe(new BigDecimal("6.43")));
        assertEquals("▼ 6.05%", PercentChange.describe(new BigDecimal("-6.05")));
        assertEquals("● 0.00%", PercentChange.describe(new BigDecimal("0")));
    }
}
