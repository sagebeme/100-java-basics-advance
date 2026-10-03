import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealFinderTest {

    private static Holiday h(String date, String name) {
        return new Holiday(LocalDate.parse(date), name);
    }

    private static Fare fare(String to, int price) {
        return new Fare(to, "x", LocalDate.of(2026, 12, 25), LocalDate.of(2026, 12, 27), price, "Twiga Air", 0);
    }

    private static Path resource(String name) throws Exception {
        return Path.of(DealFinderTest.class.getResource("/" + name).toURI());
    }

    @Test
    void aFridayHolidayMakesFridayToSunday() {
        List<LongWeekend> w = Holidays.longWeekends(List.of(h("2026-12-25", "Christmas Day"), h("2026-12-26", "Boxing Day")));
        assertEquals(1, w.size());
        assertEquals(LocalDate.of(2026, 12, 25), w.get(0).start());
        assertEquals(LocalDate.of(2026, 12, 27), w.get(0).end());
        assertEquals(List.of("Christmas Day", "Boxing Day"), w.get(0).holidays());
    }

    @Test
    void midweekHolidaysDontMakeALongWeekend() {
        assertTrue(Holidays.longWeekends(List.of(h("2026-10-20", "Mashujaa Day"))).isEmpty()); // a Tuesday
    }

    @Test
    void easterBecomesOneFourDayWeekend() {
        List<LongWeekend> w = Holidays.longWeekends(List.of(h("2027-03-29", "Easter Monday"), h("2027-03-26", "Good Friday")));
        assertEquals(1, w.size());
        assertEquals(4, w.get(0).days());
        assertEquals(List.of("Good Friday", "Easter Monday"), w.get(0).holidays());
    }

    @Test
    void findsTheNextLongWeekendFromTheFile() throws Exception {
        LongWeekend next = Holidays.next(Holidays.read(resource("holidays.csv")), LocalDate.of(2026, 10, 3));
        assertEquals("Fri 25 Dec – Sun 27 Dec (Christmas Day, Boxing Day)", next.toString());
    }

    @Test
    void picksTheCheapestFareWithinTheLimit() {
        WishItem mombasa = new WishItem("Mombasa", "MBA", 7000);
        List<Fare> fares = List.of(fare("MBA", 7900), fare("MBA", 6200), fare("KIS", 3000));
        assertEquals(6200, DealFinder.cheapestWithin(mombasa, fares).orElseThrow().priceKes());
        assertTrue(DealFinder.cheapestWithin(new WishItem("Kisumu", "KIS", 2000), fares).isEmpty());
    }

    @Test
    void sortsDealsByTheBiggestSaving() {
        List<Deal> sorted = DealFinder.sortDeals(List.of(
                new Deal(new WishItem("Zanzibar", "ZNZ", 22000), fare("ZNZ", 21500)),
                new Deal(new WishItem("Mombasa", "MBA", 7000), fare("MBA", 6200))));
        assertEquals("MBA", sorted.get(0).fare().to());
        assertEquals(800, sorted.get(0).saving());
    }

    @Test
    void searchesThePracticeApiOverHttp() throws Exception {
        try (PracticeFareServer server = new PracticeFareServer(resource("fares.json"))) {
            FareClient client = new FareClient(server.baseUri().toString());
            List<Fare> fares = client.search("NBO", "ZNZ", LocalDate.of(2026, 12, 25), LocalDate.of(2026, 12, 27));
            assertEquals(3, fares.size());
            assertTrue(fares.stream().allMatch(f -> f.to().equals("ZNZ")));
            assertTrue(client.search("NBO", "ZNZ", LocalDate.of(2027, 1, 1), LocalDate.of(2027, 1, 3)).isEmpty());
        }
    }
}
