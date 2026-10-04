import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Turns what landlords type into tidy rows a form will accept.
public class Cleaner {

    private static final Pattern BEDROOMS = Pattern.compile("(\\d+)\\s*(?:br|bed|bedroom)", Pattern.CASE_INSENSITIVE);

    // "KES 65,000 / month" → 65000; "Call for price" → empty
    public static OptionalInt rent(String text) {
        // TODO
        return OptionalInt.empty();
    }

    // "2BR apartment" → 2, "Bedsitter" or "Studio" → 0, "3 bedroom maisonette" → 3, "Office space" → empty
    public static OptionalInt bedrooms(String title) {
        // TODO
        return OptionalInt.empty();
    }

    // Clean rows (area, title, rent), without duplicates or rows missing a rent or a bedroom count.
    public static List<Rental> clean(List<String[]> raw) {
        // TODO
        return new ArrayList<>();
    }
}
