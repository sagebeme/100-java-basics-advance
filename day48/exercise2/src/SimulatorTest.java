import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulatorTest {

    @Test
    void withNoTimeNothingIsEarned() {
        assertEquals(0, Simulator.simulate(true, 0, 5));
    }

    @Test
    void withoutBuyingAnythingYouEarnJustYourClicks() {
        assertEquals(10, Simulator.simulate(true, 10, 1)); // 10 chapatis never reach the 15 a pan costs
    }

    @Test
    void theSmartStrategyBeatsTheNaiveOne() {
        long naive = Simulator.simulate(false, 600, 5);
        long smart = Simulator.simulate(true, 600, 5);
        assertTrue(smart > naive, "smart " + smart + " vs naive " + naive);
    }
}
