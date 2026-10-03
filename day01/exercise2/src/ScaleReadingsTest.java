import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScaleReadingsTest {

    @Test
    void parsesAReadingAndIgnoresSurroundingSpaces() {
        assertEquals(23.5, ScaleReadings.parseReading(" 23.5 "), 0.0001);
    }

    @Test
    void castingToIntDropsTheDecimalsInsteadOfRounding() {
        assertEquals(23, ScaleReadings.wholeKilos(23.9));
    }

    @Test
    void multipliesSacksByTheirWeight() {
        assertEquals(150.0, ScaleReadings.kgFromSacks(3, 50), 0.0001);
    }

    @Test
    void turnsAWholeNumberIntoText() {
        assertEquals("42", ScaleReadings.asText(42));
    }
}
