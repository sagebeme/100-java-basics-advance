import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ShuffleTest {

    private final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Test
    void keepsEveryItemExactlyOnce() {
        List<Integer> result = new ArrayList<>(Shuffle.shuffled(numbers, new Random(3)));
        Collections.sort(result);
        assertEquals(numbers, result);
    }

    @Test
    void actuallyChangesTheOrder() {
        assertNotEquals(numbers, Shuffle.shuffled(numbers, new Random(3)));
    }

    @Test
    void matchesFisherYatesForTheSameSeed() {
        List<Integer> expected = new ArrayList<>(numbers);
        Random r = new Random(42);
        for (int i = expected.size() - 1; i > 0; i--) Collections.swap(expected, i, r.nextInt(i + 1));
        assertEquals(expected, Shuffle.shuffled(numbers, new Random(42)));
    }

    @Test
    void leavesTheOriginalAlone() {
        List<String> original = new ArrayList<>(List.of("a", "b", "c"));
        Shuffle.shuffled(original, new Random(1));
        assertEquals(List.of("a", "b", "c"), original);
    }
}
