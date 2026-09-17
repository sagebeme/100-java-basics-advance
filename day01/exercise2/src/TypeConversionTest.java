import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeConversionTest {

    @Test
    void convertsIntToDouble() {
        assertEquals(10.0, TypeConversion.intToDouble(10));
    }

    @Test
    void castsDoubleToIntByTruncating() {
        assertEquals(15, TypeConversion.doubleToInt(15.7));
    }

    @Test
    void parsesAStringIntoAnInt() {
        assertEquals(25, TypeConversion.stringToInt("25"));
    }
}
