import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FarmMathsTest {

    @Test
    void averageKeepsTheDecimals() {
        assertEquals(2.5, FarmMaths.averageKgPerTree(25, 10), 0.0001);
    }

    @Test
    void aPartFullSackStillCounts() {
        assertEquals(3, FarmMaths.sacksNeeded(120, 50));
    }

    @Test
    void anExactFitNeedsNoExtraSack() {
        assertEquals(2, FarmMaths.sacksNeeded(100, 50));
    }

    @Test
    void percentageOfTwoWholeNumbers() {
        assertEquals(75, FarmMaths.percentRipe(45, 60));
    }
}
