import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageFactsTest {

    private Document page;

    @BeforeEach
    void read() throws IOException {
        try (InputStream in = PageFactsTest.class.getResourceAsStream("/stall.html")) {
            page = Jsoup.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8), "https://baraka.example/");
        }
    }

    @Test
    void readsTheTitle() {
        assertEquals("Baraka's Stall · Gikomba Market", PageFacts.title(page));
    }

    @Test
    void listsTheSectionHeadings() {
        assertEquals(List.of("Jackets", "Sneakers", "Find us"), PageFacts.headings(page));
    }

    @Test
    void listsEachLinkOnceAbsoluteAndSorted() {
        assertEquals(List.of("https://baraka.example/jackets", "https://baraka.example/sneakers", "https://wa.me/254700000000"),
                PageFacts.links(page));
    }

    @Test
    void countsOnlyImagesWithNoAltAtAll() {
        assertEquals(1, PageFacts.imagesWithoutAlt(page));
    }
}
