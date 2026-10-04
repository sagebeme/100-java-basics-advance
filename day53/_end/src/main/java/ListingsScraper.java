import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// The listings page needs no JavaScript, so jsoup (Day 43) reads it faster than a browser would.
public class ListingsScraper {

    public static List<String[]> raw(String html) {
        Document page = Jsoup.parse(html);
        List<String[]> rows = new ArrayList<>();
        for (Element li : page.select("li.listing")) {
            rows.add(new String[]{li.select(".area").text(), li.select("h3").text(), li.select(".rent").text()});
        }
        return rows;
    }
}
