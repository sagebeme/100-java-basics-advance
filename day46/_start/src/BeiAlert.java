import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BeiAlert {

    static Document load(String page) throws IOException {
        if (page.startsWith("http")) {
            return Jsoup.connect(page).userAgent("BeiAlert/1.0 (learning project)").timeout(10_000).get();
        }
        return Jsoup.parse(Files.readString(Path.of(page)));
    }

    public static void main(String[] args) throws IOException {
        LocalDate today = args.length > 1 ? LocalDate.parse(args[1]) : LocalDate.now();
        String to = args.length > 0 ? args[0] : "you@example.com";
        Path historyFile = Path.of("history.csv");
        PriceHistory history = PriceHistory.read(historyFile);
        Mailer mailer = new OutboxMailer(Path.of("outbox"));

        List<String> lines = Files.readAllLines(Path.of("watchlist.csv"));
        for (String line : lines.subList(1, lines.size())) {
            if (line.isBlank()) continue;
            String[] w = line.split(",");
            Product product = ProductPage.read(load(w[1]));
            Verdict verdict = AlertRules.judge(product, Integer.parseInt(w[2].trim()), history, today);

            System.out.printf("%s %-42s KES %,7d  %s%n", verdict.alert() ? "🔔" : "  ", product.name(), product.priceKes(), verdict.reason());
            if (verdict.alert()) {
                mailer.send(new Email(to, "Price alert: " + product.name(),
                        product.name() + " is now KES " + String.format("%,d", product.priceKes()) + ".\n" + verdict.reason() + "\n"));
            }
            PriceHistory.append(historyFile, new PricePoint(today, product.sku(), product.priceKes()));
        }
    }
}
