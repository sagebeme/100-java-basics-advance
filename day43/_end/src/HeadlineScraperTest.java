import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeadlineScraperTest {

    private List<Story> stories;

    @BeforeEach
    void scrapeTheSavedPage() throws IOException {
        try (InputStream in = HeadlineScraperTest.class.getResourceAsStream("/mwangaza-front-page.html")) {
            Document page = Jsoup.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8), "https://mwangaza.example/");
            stories = HeadlineScraper.scrape(page);
        }
    }

    @Test
    void findsEveryStoryButSkipsTheAdvert() {
        assertEquals(7, stories.size());
        assertTrue(stories.stream().noneMatch(s -> s.headline().contains("loan")));
    }

    @Test
    void readsTheFirstStoryCompletely() {
        Story top = stories.get(0);
        assertEquals("1001", top.id());
        assertEquals("News", top.section());
        assertEquals("Expressway to cut night-time fares for matatus", top.headline());
        assertEquals("https://mwangaza.example/news/2026/10/03/expressway-night-fares", top.url());
        assertEquals(OffsetDateTime.parse("2026-10-03T06:10:00+03:00"), top.published());
    }

    @Test
    void missingPartsDontBreakTheScraper() {
        Story last = stories.get(6);
        assertEquals("", last.summary());
        assertEquals("\"Run like Kipchoge\": 2,000 pupils race in Eldoret", last.headline());
    }

    @Test
    void ignoresTheMostReadSidebar() {
        assertEquals(1, stories.stream().filter(s -> s.headline().startsWith("Mobile money")).count());
    }

    @Test
    void countsStoriesPerSection() {
        assertEquals(Map.of("Business", 2, "Culture", 1, "News", 2, "Sport", 2), HeadlineScraper.countBySection(stories));
    }

    @Test
    void writesSafeCsv() {
        String csv = HeadlineScraper.toCsv(stories);
        assertTrue(csv.startsWith("id,section,headline,url,published\n"));
        assertTrue(csv.contains("1007,Sport,\"\"\"Run like Kipchoge\"\": 2,000 pupils race in Eldoret\",https://mwangaza.example/sport/"));
    }

    @Test
    void aStoryWithNoTimeHasNoDate() {
        Document page = Jsoup.parse("<article class=story data-id=1><h2><a href=/x>Hi</a></h2></article>", "https://a.example/");
        assertNull(HeadlineScraper.scrape(page).get(0).published());
    }
}
