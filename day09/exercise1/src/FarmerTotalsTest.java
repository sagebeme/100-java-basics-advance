import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FarmerTotalsTest {

    @Test
    void addsUpEachFarmersDeliveries() {
        Map<String, Double> totals = FarmerTotals.totalsByFarmer(
                List.of("Wanjiru", "Otieno", "Wanjiru"), List.of(23.5, 31.0, 10.0));
        assertEquals(33.5, totals.get("Wanjiru"), 0.0001);
        assertEquals(31.0, totals.get("Otieno"), 0.0001);
        assertEquals(2, totals.size());
    }

    @Test
    void keepsTheOrderFarmersFirstArrived() {
        Map<String, Double> totals = FarmerTotals.totalsByFarmer(
                List.of("Otieno", "Wanjiru", "Otieno", "Achieng"), List.of(1.0, 2.0, 3.0, 4.0));
        assertEquals(List.of("Otieno", "Wanjiru", "Achieng"), List.copyOf(totals.keySet()));
    }

    @Test
    void noDeliveriesGiveAnEmptyMap() {
        assertTrue(FarmerTotals.totalsByFarmer(List.of(), List.of()).isEmpty());
    }
}
