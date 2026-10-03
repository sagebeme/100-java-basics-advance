import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class StructuredPrice {

    public static int price(Document page) {
        // TODO: the meta tag first, then the visible .price, else -1
        return -1;
    }

    public static String currency(Document page) {
        // TODO
        return "";
    }
}
