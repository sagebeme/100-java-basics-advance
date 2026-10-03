import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriceAlert {

    static final BigDecimal THRESHOLD = new BigDecimal("5");

    // The newest `howMany` headlines that mention the stock's symbol or name.
    public static List<Headline> newsFor(Stock stock, List<Headline> news, int howMany) {
        // TODO: filter, sort newest first, limit
        return List.of();
    }

    // "SCOM ▲ 6.43% (KES 17.10 → 18.20) Safaricom", then one "\n  • title (source)" line per headline
    public static String alertText(Stock stock, List<Headline> headlines) {
        // TODO
        return "";
    }

    // Alerts for every stock that moved at least `threshold` percent either way, biggest move first.
    public static List<String> alerts(List<Stock> stocks, List<Headline> news, BigDecimal threshold) {
        // TODO
        return List.of();
    }

    public static void main(String[] args) throws IOException {
        List<Stock> stocks = Market.parsePrices(Files.readString(Path.of("prices.json")));
        List<Headline> news = Market.parseNews(Files.readString(Path.of("news.json")));

        // TODO: print each alert, then "N alerts. Quiet day for ..."
    }
}
