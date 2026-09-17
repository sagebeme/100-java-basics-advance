import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void factorialOfFiveIsOneHundredTwenty() {
        assertEquals(120, Exercise1.factorial(5));
    }

    @Test
    void factorialOfZeroIsOne() {
        assertEquals(1, Exercise1.factorial(0));
    }

    @Test
    void buildsAMultiplicationTable() {
        assertArrayEquals(new int[]{7, 14, 21, 28}, Exercise1.multiplicationTable(7, 4));
    }
}
