import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise2Test {

    @Test
    void addsTwoNumbers() { assertEquals(6.0, Exercise2.add(4, 2)); }

    @Test
    void subtractsTwoNumbers() { assertEquals(2.0, Exercise2.subtract(4, 2)); }

    @Test
    void multipliesTwoNumbers() { assertEquals(8.0, Exercise2.multiply(4, 2)); }

    @Test
    void dividesTwoNumbers() { assertEquals(2.0, Exercise2.divide(4, 2)); }

    @Test
    void findsTheRemainder() { assertEquals(1.0, Exercise2.modulo(5, 2)); }

    @Test
    void raisesToAPower() { assertEquals(1024.0, Exercise2.power(2, 10)); }

    @Test
    void findsASquareRoot() { assertEquals(4.0, Exercise2.squareRoot(16)); }
}
