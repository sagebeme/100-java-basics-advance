import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipePageTest {

    private Document page;

    @BeforeEach
    void readPage() throws IOException {
        try (InputStream in = RecipePageTest.class.getResourceAsStream("/recipe.html")) {
            page = Jsoup.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    @Test
    void hasTheBasics() {
        assertEquals("en", page.selectFirst("html").attr("lang"));
        assertFalse(page.title().isBlank(), "add a <title>");
        assertNotNull(page.selectFirst("meta[name=viewport]"), "add the viewport meta tag");
    }

    @Test
    void hasOneHeadingAboutChapati() {
        assertEquals(1, page.select("h1").size());
        assertTrue(page.selectFirst("h1").text().toLowerCase().contains("chapati"));
    }

    @Test
    void listsAtLeastFiveIngredientsAfterTheirHeading() {
        Element heading = page.select("h2").stream().filter(h -> h.text().equalsIgnoreCase("Ingredients")).findFirst().orElse(null);
        assertNotNull(heading, "add <h2>Ingredients</h2>");
        Element list = heading.nextElementSibling();
        assertEquals("ul", list.tagName(), "the ingredients should be a <ul> right after their heading");
        assertTrue(list.select("li").size() >= 5);
    }

    @Test
    void numbersAtLeastFiveSteps() {
        Element heading = page.select("h2").stream().filter(h -> h.text().equalsIgnoreCase("Method")).findFirst().orElse(null);
        assertNotNull(heading, "add <h2>Method</h2>");
        Element list = heading.nextElementSibling();
        assertEquals("ol", list.tagName(), "the steps should be an <ol> right after their heading");
        assertTrue(list.select("li").size() >= 5);
    }

    @Test
    void thePictureDescribesItself() {
        Element img = page.selectFirst("img[src=chapati.svg]");
        assertNotNull(img, "add <img src=\"chapati.svg\" alt=\"...\">");
        assertTrue(img.attr("alt").trim().length() >= 10, "write an alt text that describes the picture");
    }

    @Test
    void somethingIsImportant() {
        assertFalse(page.select("strong").isEmpty());
    }
}
