import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise2Test {

    @Test
    void sumsNumbersUpToTheFirstZero() {
        assertEquals(6, Exercise2.sumUntilZero(new int[]{1, 2, 3, 0, 100}));
    }

    @Test
    void anEmptyRunOfNumbersSumsToZero() {
        assertEquals(0, Exercise2.sumUntilZero(new int[]{0, 5, 5}));
    }

    @Test
    void findsTheFirstMultipleOfSeventySeven() {
        assertEquals(77, Exercise2.firstDivisibleBySevenAndEleven(1));
        assertEquals(154, Exercise2.firstDivisibleBySevenAndEleven(78));
    }
}
