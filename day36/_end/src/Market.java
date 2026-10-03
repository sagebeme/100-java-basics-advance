import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// Reads the two feeds. They arrive as JSON text, from a file today and from an API one day.
public class Market {

    public static List<Stock> parsePrices(String json) {
        JSONObject symbols = new JSONObject(json).getJSONObject("symbols");
        List<Stock> stocks = new ArrayList<>();
        for (String symbol : symbols.keySet()) {
            JSONObject s = symbols.getJSONObject(symbol);
            JSONArray closes = s.getJSONArray("closes");
            if (closes.length() < 2) continue; // need two days to see a change
            stocks.add(new Stock(symbol, s.getString("name"),
                    closes.getJSONObject(closes.length() - 2).getBigDecimal("close"),
                    closes.getJSONObject(closes.length() - 1).getBigDecimal("close")));
        }
        stocks.sort((a, b) -> a.symbol().compareTo(b.symbol()));
        return stocks;
    }

    public static List<Headline> parseNews(String json) {
        JSONArray articles = new JSONObject(json).getJSONArray("articles");
        List<Headline> headlines = new ArrayList<>();
        for (int i = 0; i < articles.length(); i++) {
            JSONObject a = articles.getJSONObject(i);
            headlines.add(new Headline(a.getString("title"),
                    a.getJSONObject("source").getString("name"),
                    Instant.parse(a.getString("publishedAt"))));
        }
        return headlines;
    }
}
