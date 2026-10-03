import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceAlertTest {

    private static String fixture(String name) throws IOException {
        try (InputStream in = PriceAlertTest.class.getResourceAsStream("/" + name)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private static Stock stock(String before, String after) {
        return new Stock("SCOM", "Safaricom", new BigDecimal(before), new BigDecimal(after));
    }

    @Test
    void percentChangeIsRoundedToTwoPlaces() {
        assertEquals(new BigDecimal("6.43"), stock("17.10", "18.20").percentChange());
        assertEquals(new BigDecimal("-6.05"), stock("38.00", "35.70").percentChange());
        assertEquals(new BigDecimal("0.00"), stock("10", "10").percentChange());
    }

    @Test
    void readsTheLastTwoClosesForEachSymbol() throws IOException {
        List<Stock> stocks = Market.parsePrices(fixture("prices.json"));
        assertEquals(List.of("EABL", "EQTY", "KCB", "SCOM"), stocks.stream().map(Stock::symbol).toList());
        assertEquals(new BigDecimal("18.2"), stocks.get(3).latestClose());
    }

    @Test
    void matchesWholeWordsWhateverTheCapitals() {
        Headline h = new Headline("Nairobi bourse closes mixed; KCB and Safaricom lead volumes", "x", Instant.EPOCH);
        assertTrue(h.mentions("kcb"));
        assertTrue(h.mentions("Safaricom"));
        assertFalse(new Headline("KCBX launches", "x", Instant.EPOCH).mentions("KCB"));
    }

    @Test
    void picksTheNewestHeadlinesAboutAStock() throws IOException {
        List<Headline> news = Market.parseNews(fixture("news.json"));
        List<Headline> about = PriceAlert.newsFor(stock("17.10", "18.20"), news, 2);
        assertEquals(List.of("Nairobi bourse closes mixed; KCB and Safaricom lead volumes",
                        "Safaricom shares jump after M-Pesa growth beats forecasts"),
                about.stream().map(Headline::title).toList());
    }

    @Test
    void alertsOnBigMovesBothWaysBiggestFirst() throws IOException {
        List<String> alerts = PriceAlert.alerts(Market.parsePrices(fixture("prices.json")),
                Market.parseNews(fixture("news.json")), new BigDecimal("5"));
        assertEquals(2, alerts.size());
        assertTrue(alerts.get(0).startsWith("SCOM ▲ 6.43% (KES 17.10 → 18.20) Safaricom"));
        assertTrue(alerts.get(1).startsWith("KCB ▼ 6.05% (KES 38.00 → 35.70) KCB Group"));
        assertTrue(alerts.get(1).contains("• KCB Group falls as investors weigh loan loss provisions (Soko Wire)"));
    }

    @Test
    void aSmallMoveRaisesNoAlert() {
        assertTrue(PriceAlert.alerts(List.of(stock("44.50", "44.95")), List.of(), new BigDecimal("5")).isEmpty());
    }
}
