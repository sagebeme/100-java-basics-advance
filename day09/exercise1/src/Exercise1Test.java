import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void countsHowManyTimesEachWordAppears() {
        String[] words = {"chai", "mandazi", "chai", "chai", "mandazi"};
        Map<String, Integer> counts = Exercise1.countOccurrences(words);

        assertEquals(3, counts.get("chai"));
        assertEquals(2, counts.get("mandazi"));
    }

    @Test
    void aWordSeenOnceCountsAsOne() {
        Map<String, Integer> counts = Exercise1.countOccurrences(new String[]{"solo"});
        assertEquals(1, counts.get("solo"));
    }
}
