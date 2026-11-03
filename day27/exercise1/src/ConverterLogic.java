import java.math.BigDecimal;
import java.math.RoundingMode;

// The rules behind the converter window: no JavaFX here, so they can be tested.
public class ConverterLogic {

    // "1,500" at 129.50 shillings to the dollar → "KES 1,500 = USD 11.58". A friendly message for bad input.
    public static String kesToUsd(String text, BigDecimal kesPerUsd) {
        // TODO: blank → "Type an amount in shillings."
        // TODO: not digits (with up to 2 decimals, commas allowed) → "\"elfu moja\" isn't an amount. Use digits, like 1500."
        // TODO: otherwise divide, round to cents, and format
        return "";
    }
}
