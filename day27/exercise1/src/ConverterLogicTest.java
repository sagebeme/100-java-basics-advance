import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterLogicTest {

    private static final BigDecimal RATE = new BigDecimal("129.50");

    @Test
    void convertsAndFormats() {
        assertEquals("KES 1,500 = USD 11.58", ConverterLogic.kesToUsd("1,500", RATE));
        assertEquals("KES 99.50 = USD 0.77", ConverterLogic.kesToUsd(" 99.50 ", RATE));
    }

    @Test
    void explainsBadInput() {
        assertEquals("Type an amount in shillings.", ConverterLogic.kesToUsd("  ", RATE));
        assertEquals("\"elfu moja\" isn't an amount. Use digits, like 1500.", ConverterLogic.kesToUsd("elfu moja", RATE));
        assertEquals("\"-5\" isn't an amount. Use digits, like 1500.", ConverterLogic.kesToUsd("-5", RATE));
    }
}
