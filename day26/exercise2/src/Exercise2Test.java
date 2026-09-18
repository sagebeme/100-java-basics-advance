import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise2Test {

    private final List<Integer> numbers = List.of(1, 2, 3, 4, 5);

    @Test
    void filtersEvenNumbers() {
        assertEquals(List.of(2, 4), Exercise2.evenNumbers(numbers));
    }

    @Test
    void doublesEveryNumber() {
        assertEquals(List.of(2, 4, 6, 8, 10), Exercise2.doubled(numbers));
    }

    @Test
    void reducesToTheirSum() {
        assertEquals(15, Exercise2.sum(numbers));
    }
}
