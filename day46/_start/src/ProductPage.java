import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// Reads a product page. Prefers the page's structured data (schema.org "itemprop" tags), which shops add
// for search engines and rarely change, over the visible text, which changes with every redesign.
public class ProductPage {

    // name: [itemprop=name] (or h1); sku: [itemprop=sku] (or .sku); price: priceOf(page);
    // wasKes: the number in del.was, or 0; inStock: [itemprop=availability] href ends with "/InStock".
    public static Product read(Document page) {
        // TODO
        return new Product("", "", priceOf(page), 0, false);
    }

    // meta[itemprop=price] content if it's a number, otherwise the digits of the visible .price
    static int priceOf(Document page) {
        // TODO
        return 0;
    }
}
