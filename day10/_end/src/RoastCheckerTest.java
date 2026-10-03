import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoastCheckerTest {

    @Test
    void namesEachRoastLevelAtItsEdges() {
        assertEquals("UNDERDEVELOPED", RoastChecker.roastLevel(195));
        assertEquals("LIGHT", RoastChecker.roastLevel(196));
        assertEquals("LIGHT", RoastChecker.roastLevel(205));
        assertEquals("MEDIUM", RoastChecker.roastLevel(206));
        assertEquals("MEDIUM", RoastChecker.roastLevel(218));
        assertEquals("DARK", RoastChecker.roastLevel(219));
        assertEquals("DARK", RoastChecker.roastLevel(230));
        assertEquals("BURNT", RoastChecker.roastLevel(231));
    }

    @Test
    void weightLossIsAPercentOfTheGreenWeight() {
        assertEquals(15.5, RoastChecker.weightLossPercent(1000, 845), 0.0001);
    }

    @Test
    void impossibleWeightsReturnMinusOne() {
        assertEquals(-1, RoastChecker.weightLossPercent(0, 0), 0.0001);
        assertEquals(-1, RoastChecker.weightLossPercent(1000, 1100), 0.0001);
    }

    @Test
    void namesTheLevelFromTheLoss() {
        assertEquals("LIGHT", RoastChecker.levelFromLoss(13.9));
        assertEquals("MEDIUM", RoastChecker.levelFromLoss(14));
        assertEquals("DARK", RoastChecker.levelFromLoss(17));
        assertEquals("UNKNOWN", RoastChecker.levelFromLoss(-1));
    }

    @Test
    void readingsAgreeWhenBothSayTheSame() {
        assertTrue(RoastChecker.readingsAgree(212, 1000, 845));
    }

    @Test
    void readingsDisagreeWhenTheyDiffer() {
        assertFalse(RoastChecker.readingsAgree(200, 1000, 800)); // LIGHT by temperature, DARK by weight
        assertFalse(RoastChecker.readingsAgree(212, 0, 0));      // impossible weights never agree
    }
}
