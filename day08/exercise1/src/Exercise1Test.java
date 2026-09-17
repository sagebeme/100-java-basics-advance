import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void describesAPersonUsingAllThreeParameters() {
        assertEquals("Amina is 30 years old and 1.68m tall.", Exercise1.describePerson("Amina", 30, 1.68));
    }

    @Test
    void dividesTwoNumbers() {
        assertEquals(5.0, Exercise1.safeDivide(10, 2));
    }

    @Test
    void divisionByZeroReturnsZeroInsteadOfThrowing() {
        assertEquals(0.0, Exercise1.safeDivide(10, 0));
    }
}
