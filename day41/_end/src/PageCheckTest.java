import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageCheckTest {

    private static String file(String name) throws IOException {
        try (InputStream in = PageCheckTest.class.getResourceAsStream("/" + name)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    @Test
    void theKibandaPagePassesEveryCheck() throws IOException {
        assertEquals(List.of(), PageCheck.problems(file("index.html")));
    }

    @Test
    void theKibandaPageHasTheSectionsTheNavPromises() throws IOException {
        String html = file("index.html");
        for (String id : List.of("menu", "hours", "find-us", "order")) {
            assertTrue(html.contains("id=\"" + id + "\""), "missing section #" + id);
        }
    }

    @Test
    void theStylesheetUsesVariablesAndAMediaQuery() throws IOException {
        String css = file("style.css");
        assertTrue(css.contains(":root"), "define colours once as custom properties in :root");
        assertTrue(css.contains("var(--"), "use the custom properties");
        assertTrue(css.contains("@media"), "add at least one media query");
    }

    @Test
    void catchesTheCommonMistakes() {
        String bad = """
                <html><head></head><body>
                <h1>One</h1><h1>Two</h1>
                <img src="chapati.jpg">
                <input id="phone">
                <a href="#prices">Prices</a>
                <p style="color:red">Hot!</p>
                </body></html>""";
        List<String> problems = PageCheck.problems(bad);
        assertEquals(8, problems.size(), String.join("\n", problems));
        assertTrue(problems.contains("Image chapati.jpg has no alt text."));
        assertTrue(problems.contains("Form field #phone has no <label for=...>."));
        assertTrue(problems.contains("Link to #prices goes nowhere: no element has that id."));
    }
}
