import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise2Test {

    @Test
    void dividesTwoNumbers() {
        assertEquals("Result: 5", Exercise2.safeDivide(10, 2));
    }

    @Test
    void divisionByZeroReturnsAnErrorInsteadOfThrowing() {
        assertEquals("Error: cannot divide by zero", Exercise2.safeDivide(10, 0));
    }

    @Test
    void parsesAValidNumber() {
        assertEquals("Parsed: 42", Exercise2.safeParse("42"));
    }

    @Test
    void invalidTextReturnsAnErrorInsteadOfThrowing() {
        assertEquals("Error: \"forty-two\" is not a number", Exercise2.safeParse("forty-two"));
    }
}
