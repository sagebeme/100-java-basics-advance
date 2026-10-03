import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheapestFaresTest {

    @Test
    void keepsTheCheapestFareForEachCity() {
        Map<String, CheapestFares.SimpleFare> best = CheapestFares.cheapestPerCity(List.of(
                new CheapestFares.SimpleFare("Mombasa", 7900, "Twiga Air"),
                new CheapestFares.SimpleFare("Kisumu", 4800, "Ndege Link"),
                new CheapestFares.SimpleFare("Mombasa", 6200, "Ndege Link"),
                new CheapestFares.SimpleFare("Kisumu", 5600, "Twiga Air")));
        assertEquals(6200, best.get("Mombasa").priceKes());
        assertEquals("Ndege Link", best.get("Kisumu").airline());
    }

    @Test
    void listsCitiesAToZ() {
        Map<String, CheapestFares.SimpleFare> best = CheapestFares.cheapestPerCity(List.of(
                new CheapestFares.SimpleFare("Zanzibar", 21500, "a"),
                new CheapestFares.SimpleFare("Diani", 8900, "b"),
                new CheapestFares.SimpleFare("Kigali", 27400, "c")));
        assertEquals(List.of("Diani", "Kigali", "Zanzibar"), List.copyOf(best.keySet()));
    }

    @Test
    void noFaresGiveAnEmptyMap() {
        assertTrue(CheapestFares.cheapestPerCity(List.of()).isEmpty());
    }
}
