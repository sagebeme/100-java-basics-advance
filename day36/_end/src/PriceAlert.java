import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriceAlert {

    static final BigDecimal THRESHOLD = new BigDecimal("5");

    public static List<Headline> newsFor(Stock stock, List<Headline> news, int howMany) {
        return news.stream()
                .filter(h -> h.mentions(stock.symbol()) || h.mentions(stock.name()))
                .sorted(Comparator.comparing(Headline::published).reversed())
                .limit(howMany)
                .toList();
    }

    public static String alertText(Stock stock, List<Headline> headlines) {
        BigDecimal change = stock.percentChange();
        String arrow = change.signum() >= 0 ? "▲" : "▼";
        StringBuilder text = new StringBuilder(String.format("%s %s %s%% (KES %s → %s) %s",
                stock.symbol(), arrow, change.abs().toPlainString(),
                stock.previousClose().setScale(2).toPlainString(), stock.latestClose().setScale(2).toPlainString(),
                stock.name()));
        for (Headline h : headlines) {
            text.append("\n  • ").append(h.title()).append(" (").append(h.source()).append(")");
        }
        return text.toString();
    }

    // Alerts for every stock that moved at least `threshold` percent either way, biggest move first.
    public static List<String> alerts(List<Stock> stocks, List<Headline> news, BigDecimal threshold) {
        return stocks.stream()
                .filter(s -> s.percentChange().abs().compareTo(threshold) >= 0)
                .sorted(Comparator.comparing((Stock s) -> s.percentChange().abs()).reversed())
                .map(s -> alertText(s, newsFor(s, news, 2)))
                .toList();
    }

    public static void main(String[] args) throws IOException {
        List<Stock> stocks = Market.parsePrices(Files.readString(Path.of("prices.json")));
        List<Headline> news = Market.parseNews(Files.readString(Path.of("news.json")));

        List<String> alerts = alerts(stocks, news, THRESHOLD);
        alerts.forEach(System.out::println);

        List<String> quiet = new ArrayList<>();
        for (Stock s : stocks) {
            if (s.percentChange().abs().compareTo(THRESHOLD) < 0) quiet.add(s.symbol());
        }
        System.out.println(alerts.size() + " alerts." + (quiet.isEmpty() ? "" : " Quiet day for " + String.join(", ", quiet) + "."));
    }
}
