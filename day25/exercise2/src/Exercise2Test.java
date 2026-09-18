import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise2Test {

    private final List<Integer> data = List.of(5, 2, 9, 1, 7);

    @Test
    void filtersNumbersAboveAThreshold() {
        assertEquals(List.of(5, 9, 7), Exercise2.filterAbove(data, 4));
    }

    @Test
    void sortsIntoAscendingOrder() {
        assertEquals(List.of(1, 2, 5, 7, 9), Exercise2.sortAscending(data));
    }

    @Test
    void calculatesTheAverage() {
        assertEquals(4.8, Exercise2.average(data), 0.001);
    }
}
