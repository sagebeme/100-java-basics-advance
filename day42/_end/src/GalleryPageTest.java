import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GalleryPageTest {

    private static String file(String name) throws IOException {
        try (InputStream in = GalleryPageTest.class.getResourceAsStream("/" + name)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    @Test
    void thePagePassesEveryCheck() throws IOException {
        assertEquals(List.of(), PageCheck.problems(file("index.html")));
    }

    @Test
    void theGalleryHasSixDescribedKangas() throws IOException {
        Document page = Jsoup.parse(file("index.html"));
        assertEquals(6, page.select(".gallery > li").size());
        assertTrue(page.select(".gallery .kanga").stream().allMatch(k -> !k.attr("aria-label").isBlank()),
                "a picture drawn in CSS still needs a description: role=\"img\" and aria-label");
    }

    @Test
    void usesFlexboxGridAndResponsiveRules() throws IOException {
        String css = file("style.css");
        assertTrue(css.contains("display: flex"), "use flexbox for rows");
        assertTrue(css.contains("display: grid"), "use grid for the gallery");
        assertTrue(css.contains("repeat(auto-fit, minmax("), "let the gallery choose its own number of columns");
        assertTrue(css.contains("grid-template-areas"), "name the areas of one grid");
        assertTrue(css.contains("@media (min-width:"), "change the layout on wide screens");
        assertTrue(css.contains("prefers-color-scheme: dark"), "offer a dark theme");
    }
}
