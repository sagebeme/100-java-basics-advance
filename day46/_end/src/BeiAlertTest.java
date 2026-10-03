import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeiAlertTest {

    private static final LocalDate TODAY = LocalDate.of(2026, 10, 3);

    private static Document page(String name) throws IOException {
        try (InputStream in = BeiAlertTest.class.getResourceAsStream("/" + name)) {
            return Jsoup.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    private static PriceHistory history() throws Exception {
        return PriceHistory.read(Path.of(BeiAlertTest.class.getResource("/history.csv").toURI()));
    }

    @Test
    void readsTheProductFromStructuredData() throws IOException {
        Product phone = ProductPage.read(page("phone.html"));
        assertEquals(new Product("PHONE-01", "Spark 20 Smartphone, 128 GB", 18499, 21999, true), phone);
        assertTrue(phone.claimsADiscount());
        assertFalse(ProductPage.read(page("earbuds.html")).inStock());
    }

    @Test
    void fallsBackToTheVisiblePrice() {
        Document page = Jsoup.parse("<h1>Kettle</h1><p class=price>KES 2,350</p>");
        assertEquals(2350, ProductPage.read(page).priceKes());
    }

    @Test
    void findsTheLowestAndHighestOfTheLastThirtyDays() throws Exception {
        PriceHistory h = history();
        assertEquals(18999, h.lowest("PHONE-01", TODAY, 30).getAsInt());
        assertEquals(21999, h.highest("PHONE-01", TODAY, 30).getAsInt());
        assertTrue(h.lowest("PHONE-01", LocalDate.of(2026, 9, 1), 30).isEmpty());
    }

    @Test
    void alertsWhenARealDropReachesTheTarget() throws Exception {
        Verdict v = AlertRules.judge(ProductPage.read(page("phone.html")), 19000, history(), TODAY);
        assertTrue(v.alert());
        assertEquals("KES 18,499 is at or under your target of KES 19,000", v.reason());
    }

    @Test
    void spotsAFakeSale() throws Exception {
        Verdict v = AlertRules.judge(ProductPage.read(page("blender.html")), 5000, history(), TODAY);
        assertFalse(v.alert());
        assertEquals("Not a real sale: it was KES 5,499 earlier this month", v.reason());
    }

    @Test
    void aRealDiscountIsNotCalledFake() throws Exception {
        Product phone = ProductPage.read(page("phone.html")); // "was 21,999"; the lowest this month was 18,999
        assertEquals("KES 1,499 to go", AlertRules.judge(phone, 17000, history(), TODAY).reason());
    }

    @Test
    void neverAlertsForSomethingYouCantBuy() throws Exception {
        assertEquals(new Verdict(false, "Out of stock"),
                AlertRules.judge(ProductPage.read(page("earbuds.html")), 5000, history(), TODAY));
    }

    @Test
    void appendsToTheHistoryFile() throws IOException {
        Path file = Files.createTempFile("history", ".csv");
        Files.delete(file);
        PriceHistory.append(file, new PricePoint(TODAY, "X-1", 100));
        PriceHistory.append(file, new PricePoint(TODAY.plusDays(1), "X-1", 90));
        assertEquals("date,sku,priceKes\n2026-10-03,X-1,100\n2026-10-04,X-1,90\n", Files.readString(file));
        assertEquals(100, PriceHistory.read(file).lowest("X-1", TODAY.plusDays(1), 30).getAsInt());
        Files.delete(file);
    }
}
