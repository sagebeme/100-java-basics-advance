import java.time.LocalDate;
import java.util.OptionalInt;

public class AlertRules {

    static final int WINDOW_DAYS = 30;

    // Alert when it's in stock and at or under the target. Call out a fake sale: the page shows a
    // crossed-out "was" price, but the product cost the same or less earlier this month.
    public static Verdict judge(Product product, int targetKes, PriceHistory history, LocalDate today) {
        if (!product.inStock()) {
            return new Verdict(false, "Out of stock");
        }
        OptionalInt low = history.lowest(product.sku(), today, WINDOW_DAYS);
        if (product.claimsADiscount() && low.isPresent() && low.getAsInt() <= product.priceKes()) {
            String fake = String.format("Not a real sale: it was KES %,d earlier this month", low.getAsInt());
            return product.priceKes() <= targetKes ? new Verdict(true, "At your target. " + fake) : new Verdict(false, fake);
        }
        if (product.priceKes() <= targetKes) {
            return new Verdict(true, String.format("KES %,d is at or under your target of KES %,d", product.priceKes(), targetKes));
        }
        return new Verdict(false, String.format("KES %,d to go", product.priceKes() - targetKes));
    }
}
