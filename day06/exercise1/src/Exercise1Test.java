import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void addsTwoNumbers() {
        assertEquals(8.0, Exercise1.add(5, 3));
    }

    @Test
    void subtractsTwoNumbers() {
        assertEquals(2.0, Exercise1.subtract(5, 3));
    }

    @Test
    void multipliesTwoNumbers() {
        assertEquals(15.0, Exercise1.multiply(5, 3));
    }

    @Test
    void dividesTwoNumbers() {
        assertEquals(2.0, Exercise1.divide(6, 3));
    }

    @Test
    void findsTheLargerOfTwoNumbers() {
        assertEquals(5.0, Exercise1.max(5, 3));
        assertEquals(3.0, Exercise1.max(1, 3));
    }
}
