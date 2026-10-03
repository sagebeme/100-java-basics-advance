import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiggestDeliveryTest {

    @Test
    void findsTheHeaviestDelivery() {
        assertEquals("Otieno with 31.0 kg", BiggestDelivery.biggestDelivery(
                new ArrayList<>(List.of("Wanjiru", "Otieno", "Achieng")),
                new ArrayList<>(List.of(23.5, 31.0, 12.0))));
    }

    @Test
    void theFirstOfATieWins() {
        assertEquals("Wanjiru with 20.0 kg", BiggestDelivery.biggestDelivery(
                new ArrayList<>(List.of("Wanjiru", "Otieno")),
                new ArrayList<>(List.of(20.0, 20.0))));
    }

    @Test
    void noDeliveriesSaysSo() {
        assertEquals("No deliveries yet", BiggestDelivery.biggestDelivery(new ArrayList<>(), new ArrayList<>()));
    }
}
