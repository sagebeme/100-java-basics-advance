import java.time.LocalDate;
import java.util.OptionalInt;

public class AlertRules {

    static final int WINDOW_DAYS = 30;

    // Alert when it's in stock and at or under the target. Call out a fake sale: the page shows a
    // crossed-out "was" price, but the product cost the same or less earlier this month.
    public static Verdict judge(Product product, int targetKes, PriceHistory history, LocalDate today) {
        // TODO, in this order:
        //   out of stock                         → no alert, "Out of stock"
        //   claims a discount, and the 30-day low is at or below today's price
        //                                        → "Not a real sale: it was KES 5,499 earlier this month"
        //                                          (an alert only if it's also at the target, starting "At your target. ")
        //   at or under the target               → alert, "KES 18,499 is at or under your target of KES 19,000"
        //   otherwise                            → no alert, "KES 999 to go"
        return new Verdict(false, "");
    }
}
