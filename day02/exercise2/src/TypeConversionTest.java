import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeConversionTest {

    @Test
    void parsesAStringIntoAnInt() {
        assertEquals(123, TypeConversion.parseStringToInt("123"));
    }

    @Test
    void convertsAnIntToAString() {
        assertEquals("456", TypeConversion.intToString(456));
    }

    @Test
    void castsADoubleToAnIntByTruncating() {
        assertEquals(3, TypeConversion.doubleToInt(3.14));
    }

    @Test
    void convertsAnIntToADouble() {
        assertEquals(100.0, TypeConversion.intToDouble(100));
    }
}
