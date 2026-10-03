import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Sellers write the same thing many ways. These turn it into one clean form.
public class Cleaning {

    private static final Pattern PRICE = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*(k)?", Pattern.CASE_INSENSITIVE);

    public static String collapseSpaces(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }

    // "KES 1,200" → 1200, "Ksh 2,500" → 2500, "850/=" → 850, "KES 1.8k" → 1800, "SOLD" → -1
    public static int priceKes(String text) {
        Matcher m = PRICE.matcher(text.replace(",", ""));
        if (!m.find()) return -1;
        double amount = Double.parseDouble(m.group(1));
        if (m.group(2) != null) amount *= 1000;
        return (int) Math.round(amount);
    }
}
