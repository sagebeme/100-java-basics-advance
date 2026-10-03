import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// Checks a web page for the mistakes that make pages hard to use: no title, no language, images
// without alt text, form fields without labels, and links to sections that don't exist.
// Run: java PageCheck index.html
public class PageCheck {

    public static List<String> problems(String html) {
        Document page = Jsoup.parse(html);
        List<String> problems = new ArrayList<>();

        if (page.title().isBlank()) problems.add("The page has no <title>.");
        if (page.selectFirst("html").attr("lang").isBlank()) problems.add("<html> needs a lang attribute, like lang=\"en\".");
        if (page.selectFirst("meta[name=viewport]") == null) problems.add("Add <meta name=\"viewport\" ...> so phones show the page at the right size.");

        int h1s = page.select("h1").size();
        if (h1s != 1) problems.add("A page should have exactly one <h1>; this one has " + h1s + ".");

        for (Element img : page.select("img")) {
            if (!img.hasAttr("alt")) problems.add("Image " + img.attr("src") + " has no alt text.");
        }
        for (Element field : page.select("input, select, textarea")) {
            if (field.attr("type").equals("hidden") || field.attr("type").equals("submit")) continue;
            String id = field.id();
            if (id.isBlank() || page.selectFirst("label[for=" + id + "]") == null) {
                problems.add("Form field " + (id.isBlank() ? field.tagName() : "#" + id) + " has no <label for=...>.");
            }
        }
        for (Element link : page.select("a[href^=#]")) {
            String target = link.attr("href").substring(1);
            if (!target.isEmpty() && page.getElementById(target) == null) {
                problems.add("Link to #" + target + " goes nowhere: no element has that id.");
            }
        }
        for (Element styled : page.select("[style]")) {
            problems.add("<" + styled.tagName() + "> has a style attribute. Put styles in the CSS file instead.");
        }
        return problems;
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : "index.html");
        List<String> problems = problems(Files.readString(file));
        if (problems.isEmpty()) {
            System.out.println("✔ " + file + " passed every check.");
        } else {
            problems.forEach(p -> System.out.println("✘ " + p));
        }
    }
}
