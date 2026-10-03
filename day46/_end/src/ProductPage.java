import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// Reads a product page. Prefers the page's structured data (schema.org "itemprop" tags), which shops add
// for search engines and rarely change, over the visible text, which changes with every redesign.
public class ProductPage {

    public static Product read(Document page) {
        String name = textOf(page, "[itemprop=name]", "h1");
        String sku = textOf(page, "[itemprop=sku]", ".sku");
        int price = priceOf(page);
        Element availability = page.selectFirst("[itemprop=availability]");
        boolean inStock = availability != null && availability.attr("href").endsWith("/InStock");
        Element was = page.selectFirst("del.was, s.was");
        int wasKes = was == null ? 0 : Integer.parseInt("0" + was.text().replaceAll("[^0-9]", ""));
        return new Product(sku, name, price, wasKes, inStock);
    }

    static int priceOf(Document page) {
        Element meta = page.selectFirst("meta[itemprop=price]");
        if (meta != null && meta.attr("content").matches("\\d+(\\.\\d+)?")) {
            return (int) Math.round(Double.parseDouble(meta.attr("content")));
        }
        Element shown = page.selectFirst(".price"); // fall back to the visible price
        if (shown == null) throw new IllegalArgumentException("No price on this page");
        return Integer.parseInt(shown.text().replaceAll("[^0-9]", ""));
    }

    private static String textOf(Document page, String preferred, String fallback) {
        Element e = page.selectFirst(preferred);
        if (e == null) e = page.selectFirst(fallback);
        return e == null ? "" : e.text().trim();
    }
}
