import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise1Test {

    @Test
    void addsTwoNumbers() {
        assertEquals(5, Exercise1.sum(2, 3));
    }

    @Test
    void multipliesThreeNumbers() {
        assertEquals(24, Exercise1.product(2, 3, 4));
    }

    @Test
    void picksTheLargerNumber() {
        assertEquals(3, Exercise1.largest(2, 3));
    }

    @Test
    void formatsALabelledValue() {
        assertEquals("Total: 9.50", Exercise1.formatted("Total", 9.5));
    }

    @Test
    void recognisesPrimeNumbers() {
        assertTrue(Exercise1.isPrime(7));
        assertFalse(Exercise1.isPrime(8));
        assertFalse(Exercise1.isPrime(1));
    }

    @Test
    void recognisesEvenNumbers() {
        assertTrue(Exercise1.isEven(4));
        assertFalse(Exercise1.isEven(7));
    }
}
