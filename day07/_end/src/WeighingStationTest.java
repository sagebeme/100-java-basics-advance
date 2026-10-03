import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeighingStationTest {

    private ArrayList<String> farmers;
    private ArrayList<Double> kgs;

    @BeforeEach
    void anAfternoonOfDeliveries() {
        farmers = new ArrayList<>();
        kgs = new ArrayList<>();
        WeighingStation.record(farmers, kgs, "Wanjiru", 23.5);
        WeighingStation.record(farmers, kgs, "Otieno", 31);
        WeighingStation.record(farmers, kgs, "Wanjiru", 10);
    }

    @Test
    void recordsADeliveryInBothLists() {
        assertTrue(WeighingStation.record(farmers, kgs, "Achieng", 5));
        assertEquals("Achieng", farmers.get(3));
        assertEquals(5.0, kgs.get(3), 0.0001);
    }

    @Test
    void refusesZeroOrNegativeWeightsAndEmptyNames() {
        assertFalse(WeighingStation.record(farmers, kgs, "Achieng", 0));
        assertFalse(WeighingStation.record(farmers, kgs, "Achieng", -4));
        assertFalse(WeighingStation.record(farmers, kgs, "   ", 12));
        assertEquals(3, farmers.size());
        assertEquals(3, kgs.size());
    }

    @Test
    void addsUpEveryDelivery() {
        assertEquals(64.5, WeighingStation.totalKg(kgs), 0.0001);
    }

    @Test
    void addsUpOneFarmerIgnoringCapitals() {
        assertEquals(33.5, WeighingStation.totalFor(farmers, kgs, "wanjiru"), 0.0001);
        assertEquals(0, WeighingStation.totalFor(farmers, kgs, "Kip"), 0.0001);
    }

    @Test
    void countsOneFarmersDeliveries() {
        assertEquals(2, WeighingStation.deliveriesBy(farmers, "WANJIRU"));
        assertEquals(1, WeighingStation.deliveriesBy(farmers, "Otieno"));
    }

    @Test
    void summarisesTheDay() {
        assertEquals("3 deliveries, 64.5 kg in total", WeighingStation.summary(farmers, kgs));
    }
}
