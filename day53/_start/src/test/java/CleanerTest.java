import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.OptionalInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanerTest {

    @Test
    void readsRents() {
        assertEquals(OptionalInt.of(65000), Cleaner.rent("KES 65,000 / month"));
        assertEquals(OptionalInt.of(12500), Cleaner.rent("Ksh 12,500"));
        assertEquals(OptionalInt.empty(), Cleaner.rent("Call for price"));
    }

    @Test
    void readsBedrooms() {
        assertEquals(OptionalInt.of(2), Cleaner.bedrooms("2BR apartment, 3rd floor"));
        assertEquals(OptionalInt.of(3), Cleaner.bedrooms("3 bedroom maisonette"));
        assertEquals(OptionalInt.of(0), Cleaner.bedrooms("Bedsitter near TRM"));
        assertEquals(OptionalInt.of(0), Cleaner.bedrooms("Studio apartment"));
        assertEquals(OptionalInt.empty(), Cleaner.bedrooms("Office space"));
    }

    @Test
    void scrapesAndCleansThePracticePage() throws Exception {
        String html;
        try (InputStream in = CleanerTest.class.getResourceAsStream("/site/listings.html")) {
            html = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
        List<Rental> rows = Cleaner.clean(ListingsScraper.raw(html));
        assertEquals(6, rows.size()); // 8 listings: one duplicate, one office with no price
        assertEquals(new Rental("Kilimani", 2, 65000), rows.get(0));
        assertEquals(new Rental("Kileleshwa", 4, 180000), rows.get(5));
    }
}
