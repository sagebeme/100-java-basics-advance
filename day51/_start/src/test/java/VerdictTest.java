import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerdictTest {

    private static List<SpeedResult> runs(double... downs) {
        return java.util.Arrays.stream(downs).mapToObj(d -> new SpeedResult(d, d / 4, 20)).toList();
    }

    @Test
    void theMedianIgnoresOneOddResult() {
        assertEquals(7.1, Verdict.median(List.of(6.2, 30.0, 7.1)), 1e-9);
        assertEquals(6.65, Verdict.median(List.of(6.2, 7.1, 30.0, 6.0)), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Verdict.median(List.of()));
    }

    @Test
    void complainsOnlyBelowTheShare() {
        assertTrue(Verdict.shouldComplain(runs(6.2, 7.8, 5.9), 20, 0.7));
        assertFalse(Verdict.shouldComplain(runs(14.3, 18.9, 12.6), 20, 0.7));
    }

    @Test
    void theComplaintGivesTheEvidence() {
        String text = Verdict.complaint(runs(6.2, 7.8, 5.9), 20);
        assertTrue(text.startsWith("My plan is 20 Mbps, but I measured a median download speed of 6.2 Mbps (31% of the plan) over 3 tests:"));
        assertTrue(text.contains("test 2: 7.8 Mbps down, 2.0 Mbps up, 20 ms ping"));
        assertTrue(text.endsWith("Please check my line."));
    }
}
