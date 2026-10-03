import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HeadlineScraper {

    // Every real story on the page, in page order. Skip article.story elements that are also .sponsored.
    // For each: data-id, .section text (or "Other"), the h2 link's text and absolute URL,
    // time[datetime] parsed with OffsetDateTime.parse (or null), and .summary text (or "").
    public static List<Story> scrape(Document page) {
        List<Story> stories = new ArrayList<>();
        // TODO
        return stories;
    }

    public static Map<String, Integer> countBySection(List<Story> stories) {
        // TODO: section -> number of stories, sorted by section name
        return new TreeMap<>();
    }

    // One row per story. Fields with commas or quotes are wrapped in quotes, and quotes inside are doubled.
    public static String toCsv(List<Story> stories) {
        StringBuilder csv = new StringBuilder("id,section,headline,url,published\n");
        for (Story s : stories) {
            csv.append(String.join(",", s.id(), csvField(s.section()), csvField(s.headline()), s.url(),
                    s.published() == null ? "" : s.published().toString())).append('\n');
        }
        return csv.toString();
    }

    static String csvField(String value) {
        // TODO: wrap in quotes if it contains a comma, a quote or a newline, doubling any quotes inside
        return value;
    }

    // From a saved file, or from a live URL (be polite: one request, a clear user agent, a timeout).
    public static Document load(String source) throws IOException {
        if (source.startsWith("http://") || source.startsWith("https://")) {
            return Jsoup.connect(source)
                    .userAgent("HeadlineDigest/1.0 (learning project)")
                    .timeout(10_000)
                    .get();
        }
        return Jsoup.parse(Files.readString(Path.of(source)), "https://mwangaza.example/");
    }

    public static void main(String[] args) throws IOException {
        Document page = load(args.length > 0 ? args[0] : "mwangaza-front-page.html");
        List<Story> stories = scrape(page);

        System.out.println(page.title());
        System.out.println(stories.size() + " stories: " + countBySection(stories));
        for (Story s : stories) {
            System.out.printf("  [%-8s] %s%n             %s%n", s.section(), s.headline(), s.url());
        }
        Files.writeString(Path.of("headlines.csv"), toCsv(stories));
        System.out.println("Saved headlines.csv");
    }
}
