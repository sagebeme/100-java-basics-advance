import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FareTable {

    public record FareRow(String route, String from, String to, int priceKes) {}

    public static int priceKes(String text) {
        // TODO: the first whole number, ignoring commas, or -1
        return -1;
    }

    public static List<FareRow> read(Document page) {
        List<FareRow> rows = new ArrayList<>();
        // TODO: rows of #fares with exactly 4 td cells and a price
        return rows;
    }
}
