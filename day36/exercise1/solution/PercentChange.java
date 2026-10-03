import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentChange {

    public static BigDecimal percentChange(String before, String after) {
        BigDecimal from = new BigDecimal(before);
        BigDecimal to = new BigDecimal(after);
        if (from.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Can't work out a percentage change from zero");
        }
        return to.subtract(from).multiply(BigDecimal.valueOf(100)).divide(from, 2, RoundingMode.HALF_UP);
    }

    public static String describe(BigDecimal change) {
        String symbol = switch (change.signum()) {
            case 1 -> "▲";
            case -1 -> "▼";
            default -> "●";
        };
        return symbol + " " + change.abs().setScale(2, RoundingMode.HALF_UP).toPlainString() + "%";
    }

    public static void main(String[] args) {
        System.out.println(describe(percentChange("17.10", "18.20")));
    }
}
