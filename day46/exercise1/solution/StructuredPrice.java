import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class StructuredPrice {

    public static int price(Document page) {
        Element meta = page.selectFirst("meta[itemprop=price]");
        if (meta != null && meta.attr("content").matches("\\d+(\\.\\d+)?")) {
            return (int) Math.round(Double.parseDouble(meta.attr("content")));
        }
        Element shown = page.selectFirst(".price");
        if (shown == null) return -1;
        String digits = shown.text().replaceAll("[^0-9]", "");
        return digits.isEmpty() ? -1 : Integer.parseInt(digits);
    }

    public static String currency(Document page) {
        Element meta = page.selectFirst("meta[itemprop=priceCurrency]");
        return meta == null || meta.attr("content").isBlank() ? "KES" : meta.attr("content");
    }
}
