import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PriceWatch {

    private final HttpClient http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    private final long delayMs;
    private final int maxPages;

    public PriceWatch(long delayMs, int maxPages) {
        this.delayMs = delayMs;
        this.maxPages = maxPages;
    }

    String get(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("User-Agent", "PriceWatch/1.0 (learning project)")
                .timeout(Duration.ofSeconds(10))
                .build();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) throw new IOException("HTTP " + response.statusCode() + " for " + uri);
        return response.body();
    }

    // Listings on one page: each li.listing with its data-sku, cleaned .title, lower-cased .tag,
    // .condition and price. Sold items (no price) are left out.
    public static List<Listing> readPage(Document page) {
        List<Listing> found = new ArrayList<>();
        // TODO
        return found;
    }

    // Starts at `first` and follows rel="next" links, politely, until there are no more pages,
    // the robots.txt says stop, or maxPages is reached. Each listing appears once, keyed by SKU.
    public List<Listing> crawl(URI first) throws IOException, InterruptedException {
        RobotsRules robots = new RobotsRules(get(first.resolve("/robots.txt")));
        Map<String, Listing> bySku = new LinkedHashMap<>();
        // TODO: a while loop: fetch, parse with the page's URL as base URI, keep new SKUs,
        //       find a[rel=next], and Thread.sleep(delayMs) before every request after the first
        return new ArrayList<>(bySku.values());
    }

    public static Map<String, Listing> cheapestPerCategory(List<Listing> listings) {
        // TODO: category -> its cheapest listing, categories A to Z
        return new TreeMap<>();
    }

    public static int averagePrice(List<Listing> listings) {
        // TODO: rounded to the nearest shilling, 0 for no listings
        return 0;
    }

    public static void main(String[] args) throws Exception {
        try (PracticeShop shop = new PracticeShop(Path.of("."))) {
            List<Listing> listings = new PriceWatch(1000, 10).crawl(shop.base().resolve("/listings?page=1"));
            System.out.println("Found " + listings.size() + " listings for sale (" + shop.requests() + " requests, 1 second apart)");
            System.out.println("Average price: KES " + String.format("%,d", averagePrice(listings)));
            System.out.println("Cheapest in each category:");
            cheapestPerCategory(listings).forEach((category, l) ->
                    System.out.printf("  %-8s KES %,6d  %s (%s)%n", category, l.priceKes(), l.title(), l.condition()));

            StringBuilder csv = new StringBuilder("sku,category,condition,priceKes,title\n");
            listings.stream().sorted(Comparator.comparingInt(Listing::priceKes)).forEach(l ->
                    csv.append(l.sku()).append(',').append(l.category()).append(',').append(l.condition()).append(',')
                            .append(l.priceKes()).append(",\"").append(l.title().replace("\"", "\"\"")).append("\"\n"));
            Files.writeString(Path.of("listings.csv"), csv);
            System.out.println("Saved listings.csv, cheapest first");
        }
    }
}
