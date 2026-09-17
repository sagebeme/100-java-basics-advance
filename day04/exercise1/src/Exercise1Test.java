import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    private final int[] numbers = {4, 9, 2, 7, 5};

    @Test
    void findsTheMaximum() {
        assertEquals(9, Exercise1.max(numbers));
    }

    @Test
    void findsTheMinimum() {
        assertEquals(2, Exercise1.min(numbers));
    }

    @Test
    void calculatesTheAverage() {
        assertEquals(5.4, Exercise1.average(numbers), 0.001);
    }
}
