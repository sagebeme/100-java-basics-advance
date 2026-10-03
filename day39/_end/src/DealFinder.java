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
        return fares.stream()
                .filter(f -> f.to().equals(wish.code()) && f.priceKes() <= wish.maxPriceKes())
                .min(Comparator.comparingInt(Fare::priceKes));
    }

    // Biggest saving first.
    public static List<Deal> sortDeals(List<Deal> deals) {
        List<Deal> sorted = new ArrayList<>(deals);
        sorted.sort(Comparator.comparingInt(Deal::saving).reversed());
        return sorted;
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

            List<Deal> deals = new ArrayList<>();
            List<String> noDeal = new ArrayList<>();
            for (WishItem wish : readWishlist(Path.of("wishlist.csv"))) {
                List<Fare> fares = client.search("NBO", wish.code(), weekend.start(), weekend.end());
                Optional<Fare> best = cheapestWithin(wish, fares);
                if (best.isPresent()) {
                    deals.add(new Deal(wish, best.get()));
                } else {
                    int cheapest = fares.stream().mapToInt(Fare::priceKes).min().orElse(-1);
                    noDeal.add(wish.city() + (cheapest < 0 ? " (no flights)"
                            : String.format(" (cheapest KES %,d, your limit KES %,d)", cheapest, wish.maxPriceKes())));
                }
            }

            System.out.println(deals.size() + " deals from Nairobi:");
            for (Deal d : sortDeals(deals)) {
                System.out.printf("  %-10s %s  KES %,7d  %-14s %-7s KES %,d under your limit%n", d.wish().city(),
                        d.fare().to(), d.fare().priceKes(), d.fare().airline(), d.fare().route(), d.saving());
            }
            if (!noDeal.isEmpty()) System.out.println("No deal yet: " + String.join(", ", noDeal));
        }
    }
}
