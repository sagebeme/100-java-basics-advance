import java.math.BigDecimal;
import java.math.RoundingMode;

public record Stock(String symbol, String name, BigDecimal previousClose, BigDecimal latestClose) {

    // From the previous close to the latest, as a percentage with 2 decimal places: 17.10 → 18.20 is 6.43.
    public BigDecimal percentChange() {
        // TODO: (latest - previous) * 100 / previous, rounded HALF_UP to 2 places
        return BigDecimal.ZERO;
    }
}
