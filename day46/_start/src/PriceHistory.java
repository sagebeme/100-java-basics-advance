import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

// Every price we've seen, one line per day per product, in a CSV file that only ever grows.
public class PriceHistory {

    private final List<PricePoint> points = new ArrayList<>();

    public static PriceHistory read(Path csv) throws IOException {
        PriceHistory history = new PriceHistory();
        if (!Files.exists(csv)) return history;
        for (String line : Files.readAllLines(csv)) {
            if (line.isBlank() || line.startsWith("date,")) continue;
            String[] p = line.split(",");
            history.points.add(new PricePoint(LocalDate.parse(p[0]), p[1], Integer.parseInt(p[2])));
        }
        return history;
    }

    public void add(PricePoint point) {
        points.add(point);
    }

    public static void append(Path csv, PricePoint p) throws IOException {
        if (!Files.exists(csv)) Files.writeString(csv, "date,sku,priceKes\n");
        Files.writeString(csv, p.date() + "," + p.sku() + "," + p.priceKes() + "\n", StandardOpenOption.APPEND);
    }

    // Prices for one product in the `days` days before `today` (today not included).
    public List<Integer> before(String sku, LocalDate today, int days) {
        LocalDate from = today.minusDays(days);
        return points.stream()
                .filter(p -> p.sku().equals(sku) && !p.date().isBefore(from) && p.date().isBefore(today))
                .map(PricePoint::priceKes)
                .toList();
    }

    public OptionalInt lowest(String sku, LocalDate today, int days) {
        return before(sku, today, days).stream().mapToInt(Integer::intValue).min();
    }

    public OptionalInt highest(String sku, LocalDate today, int days) {
        return before(sku, today, days).stream().mapToInt(Integer::intValue).max();
    }
}
