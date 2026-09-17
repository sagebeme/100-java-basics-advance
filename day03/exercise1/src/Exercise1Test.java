import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise1Test {

    @Test
    void classifiesAPositiveNumber() {
        assertEquals("positive", Exercise1.classifySign(5));
    }

    @Test
    void classifiesANegativeNumber() {
        assertEquals("negative", Exercise1.classifySign(-5));
    }

    @Test
    void classifiesZero() {
        assertEquals("zero", Exercise1.classifySign(0));
    }

    @Test
    void recognisesEvenNumbers() {
        assertTrue(Exercise1.isEven(4));
    }

    @Test
    void recognisesOddNumbers() {
        assertFalse(Exercise1.isEven(7));
    }
}
