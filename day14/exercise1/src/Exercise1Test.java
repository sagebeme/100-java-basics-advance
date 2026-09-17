import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise1Test {

    @Test
    void celebrityFollowersHasTheExpectedEntries() {
        Map<String, Integer> data = Exercise1.celebrityFollowers();
        assertEquals(500, data.get("Instagram"));
        assertTrue(data.containsKey("Cristiano Ronaldo"));
    }

    @Test
    void countryPopulationsHasTheExpectedEntries() {
        Map<String, Integer> data = Exercise1.countryPopulations();
        assertEquals(55, data.get("Kenya"));
        assertEquals(220, data.get("Nigeria"));
    }
}
