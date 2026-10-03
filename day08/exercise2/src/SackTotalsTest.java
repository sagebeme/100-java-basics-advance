import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SackTotalsTest {

    @Test
    void addsAnyNumberOfSacks() {
        assertEquals(40.5, SackTotals.totalKg(12.5, 8, 20), 0.0001);
        assertEquals(7, SackTotals.totalKg(7), 0.0001);
    }

    @Test
    void noSacksWeighNothing() {
        assertEquals(0, SackTotals.totalKg(), 0.0001);
    }

    @Test
    void averagesTheSacks() {
        assertEquals(20, SackTotals.averageKg(10, 20, 30), 0.0001);
    }

    @Test
    void theAverageOfNoSacksIsZero() {
        assertEquals(0, SackTotals.averageKg(), 0.0001);
    }
}
