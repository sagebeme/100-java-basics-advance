import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeadlineSearchTest {

    private final List<String> titles = List.of(
            "KCB Group falls as investors weigh provisions",
            "KCBX launches new fund",
            "Safaricom and kcb lead volumes",
            "Safaricom names new CTO");

    @Test
    void matchesWholeWordsOnly() {
        assertTrue(HeadlineSearch.mentions("KCB Group falls", "kcb"));
        assertFalse(HeadlineSearch.mentions("KCBX launches", "KCB"));
        assertTrue(HeadlineSearch.mentions("Shares in E.A. Breweries rise", "E.A."));
    }

    @Test
    void findsTitlesAboutAnyOfTheWordsInOrder() {
        assertEquals(List.of(titles.get(0), titles.get(2)), HeadlineSearch.about(titles, List.of("KCB"), 5));
    }

    @Test
    void neverAddsATitleTwice() {
        assertEquals(List.of(titles.get(0), titles.get(2), titles.get(3)),
                HeadlineSearch.about(titles, List.of("KCB", "Safaricom"), 5));
    }

    @Test
    void stopsAtTheLimit() {
        assertEquals(List.of(titles.get(0)), HeadlineSearch.about(titles, List.of("KCB", "Safaricom"), 1));
    }
}
