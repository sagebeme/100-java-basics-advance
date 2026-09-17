import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void addsTwoNumbers() {
        assertEquals(7.0, Calculator.add(3, 4));
    }

    @Test
    void subtractsTwoNumbers() {
        assertEquals(-1.0, Calculator.subtract(3, 4));
    }

    @Test
    void multipliesTwoNumbers() {
        assertEquals(12.0, Calculator.multiply(3, 4));
    }

    @Test
    void dividesTwoNumbers() {
        assertEquals(2.0, Calculator.divide(8, 4));
    }

    @Test
    void divisionByZeroReturnsZeroInsteadOfCrashing() {
        assertEquals(0.0, Calculator.divide(5, 0));
    }
}
