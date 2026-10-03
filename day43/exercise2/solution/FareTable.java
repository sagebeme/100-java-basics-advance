import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FareTable {

    public record FareRow(String route, String from, String to, int priceKes) {}

    private static final Pattern NUMBER = Pattern.compile("\\d+");

    public static int priceKes(String text) {
        Matcher m = NUMBER.matcher(text.replace(",", ""));
        return m.find() ? Integer.parseInt(m.group()) : -1;
    }

    public static List<FareRow> read(Document page) {
        List<FareRow> rows = new ArrayList<>();
        for (Element tr : page.select("#fares tr")) {
            Elements cells = tr.select("td");
            if (cells.size() != 4) continue; // the header (th cells) and the note (one wide cell)
            int price = priceKes(cells.get(3).text());
            if (price < 0) continue;
            rows.add(new FareRow(cells.get(0).text(), cells.get(1).text(), cells.get(2).text(), price));
        }
        return rows;
    }
}
