import java.math.BigDecimal;
import java.math.RoundingMode;

// The rules behind the converter window: no JavaFX here, so they can be tested.
public class ConverterLogic {

    // "1,500" at 129.50 shillings to the dollar → "KES 1,500 = USD 11.58". A friendly message for bad input.
    public static String kesToUsd(String text, BigDecimal kesPerUsd) {
        String cleaned = text == null ? "" : text.replace(",", "").trim();
        if (cleaned.isEmpty()) return "Type an amount in shillings.";
        if (!cleaned.matches("\\d+(\\.\\d{1,2})?")) return "\"" + text.trim() + "\" isn't an amount. Use digits, like 1500.";
        BigDecimal kes = new BigDecimal(cleaned);
        BigDecimal usd = kes.divide(kesPerUsd, 2, RoundingMode.HALF_UP);
        return String.format("KES %,.2f = USD %,.2f", kes, usd).replace(".00 =", " =");
    }
}
