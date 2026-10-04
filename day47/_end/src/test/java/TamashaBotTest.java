import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TamashaBotTest extends BrowserTest {

    @Test
    void waitsForSlowResultsAndReadsThem() {
        driver.get(site.url("/").toString());
        List<EventCard> events = new SearchPage(driver).search("jazz", "Nairobi");
        assertEquals(List.of(101, 102), events.stream().map(EventCard::id).toList());
        assertEquals(new EventCard(102, "Sunday Jazz Brunch", "Nairobi", LocalDate.of(2026, 12, 6), 1200), events.get(1));
    }

    @Test
    void registersAndReadsTheConfirmationCode() {
        driver.get(site.url("/").toString());
        SearchPage search = new SearchPage(driver);
        search.search("jazz", "Nairobi");
        String code = search.openRegistration(102).register(new Attendee("Wanjiru Kamau", "wanjiru@example.com", 2));
        assertEquals("TAM-102-2-WK", code);
    }

    @Test
    void reportsTheFormsOwnErrors() {
        driver.get(site.url("/register.html?id=101").toString());
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new RegisterPage(driver).register(new Attendee("Wanjiru", "not-an-email", 2)));
        assertTrue(e.getMessage().contains("Please enter a valid email address."));
    }

    @Test
    void anEmptySearchSaysSo() {
        driver.get(site.url("/").toString());
        assertTrue(new SearchPage(driver).search("opera", "").isEmpty());
    }
}
