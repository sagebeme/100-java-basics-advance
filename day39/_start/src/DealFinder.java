import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DealFinder {

    // The cheapest fare at or under the wish's limit, if there is one.
    public static Optional<Fare> cheapestWithin(WishItem wish, List<Fare> fares) {
        // TODO: only fares to wish.code() at or under the limit; the cheapest of those
        return Optional.empty();
    }

    // Biggest saving first.
    public static List<Deal> sortDeals(List<Deal> deals) {
        // TODO
        return deals;
    }

    public static List<WishItem> readWishlist(Path csv) throws IOException {
        return Files.readAllLines(csv).stream()
                .filter(line -> !line.isBlank() && !line.startsWith("city,"))
                .map(WishItem::fromCsv)
                .toList();
    }

    public static void main(String[] args) throws Exception {
        LocalDate today = args.length > 0 ? LocalDate.parse(args[0]) : LocalDate.now();
        LongWeekend weekend = Holidays.next(Holidays.read(Path.of("holidays.csv")), today);
        if (weekend == null) {
            System.out.println("No more long weekends in holidays.csv. Add next year's holidays.");
            return;
        }
        System.out.println("Next long weekend: " + weekend);

        String apiUrl = System.getenv("FARE_API_URL"); // a real API one day; the practice server today
        try (PracticeFareServer practice = apiUrl == null ? new PracticeFareServer(Path.of("fares.json")) : null) {
            FareClient client = new FareClient(apiUrl != null ? apiUrl : practice.baseUri().toString());

            // TODO: for each wish: search, keep the cheapest fare within the limit as a Deal,
            //       or note "City (cheapest KES x, your limit KES y)" when there's no deal
            // TODO: print the deals, biggest saving first, then the wishes with no deal
        }
    }
}
