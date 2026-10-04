import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatsTest {

    @Test
    void medianOfOddAndEvenCounts() {
        assertEquals(7.1, Stats.median(List.of(6.2, 30.0, 7.1)), 1e-9);
        assertEquals(6.65, Stats.median(List.of(6.2, 7.1, 30.0, 6.0)), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Stats.median(List.of()));
    }

    @Test
    void trimmedMeanDropsTheExtremes() {
        assertEquals(6.5, Stats.trimmedMean(List.of(5.0, 6.0, 7.0, 30.0)), 1e-9);
        assertEquals(5.5, Stats.trimmedMean(List.of(5.0, 6.0)), 1e-9);
    }

    @Test
    void leavesTheListAlone() {
        List<Double> values = new ArrayList<>(List.of(3.0, 1.0, 2.0));
        Stats.median(values);
        Stats.trimmedMean(values);
        assertEquals(List.of(3.0, 1.0, 2.0), values);
    }
}
