import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentChange {

    public static BigDecimal percentChange(String before, String after) {
        // TODO
        return BigDecimal.ZERO;
    }

    public static String describe(BigDecimal change) {
        // TODO: ▲, ▼ or ●, then the size and a %
        return "";
    }

    public static void main(String[] args) {
        System.out.println(describe(percentChange("17.10", "18.20")));
    }
}
