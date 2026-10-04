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
        Matcher m = Pattern.compile("\\d+").matcher(text.replace(",", ""));
        return m.find() ? OptionalInt.of(Integer.parseInt(m.group())) : OptionalInt.empty();
    }

    // "2BR apartment" → 2, "Bedsitter" or "Studio" → 0, "4BR townhouse" → 4, "Office space" → empty
    public static OptionalInt bedrooms(String title) {
        String t = title.toLowerCase();
        if (t.contains("bedsitter") || t.contains("studio")) return OptionalInt.of(0);
        Matcher m = BEDROOMS.matcher(t);
        return m.find() ? OptionalInt.of(Integer.parseInt(m.group(1))) : OptionalInt.empty();
    }

    // Clean rows, without duplicates or rows missing a rent or a bedroom count.
    public static List<Rental> clean(List<String[]> raw) {
        // TODO: a Rental for each row with a bedroom count and a rent, without duplicates, in order
        return new ArrayList<>();
    }
}
