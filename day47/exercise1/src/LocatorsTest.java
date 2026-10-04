import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LocatorsTest {

    private Document page;

    @BeforeEach
    void read() throws IOException {
        try (InputStream in = LocatorsTest.class.getResourceAsStream("/practice.html")) {
            page = Jsoup.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    private List<String> ids(String selector) {
        assertFalse(selector.isBlank(), "fill in the selector");
        return page.select(selector).eachAttr("data-id");
    }

    @Test
    void findsTheSearchBox() {
        assertFalse(Locators.SEARCH_BOX.isBlank());
        assertEquals("q", page.selectFirst(Locators.SEARCH_BOX).id());
        assertEquals(1, page.select(Locators.SEARCH_BOX).size());
    }

    @Test
    void findsAllEventsAndOnlyBookableOnes() {
        assertEquals(List.of("101", "102", "104"), ids(Locators.ALL_EVENTS));
        assertEquals(List.of("101", "104"), ids(Locators.BOOKABLE_EVENTS));
    }

    @Test
    void findsOneSpecificLink() {
        assertFalse(Locators.EVENT_104_REGISTER.isBlank());
        assertEquals(1, page.select(Locators.EVENT_104_REGISTER).size());
        assertEquals("/register?id=104", page.selectFirst(Locators.EVENT_104_REGISTER).attr("href"));
    }

    @Test
    void findsEveryPrice() {
        assertFalse(Locators.PRICES_IN_KES.isBlank());
        assertEquals(List.of("1800", "1200", "800"), page.select(Locators.PRICES_IN_KES).eachAttr("data-kes"));
    }
}
