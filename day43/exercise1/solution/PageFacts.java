import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.TreeSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PageFacts {

    public static String title(Document page) {
        return page.title();
    }

    public static List<String> headings(Document page) {
        return page.select("h2").eachText();
    }

    public static List<String> links(Document page) {
        TreeSet<String> urls = new TreeSet<>();
        for (Element a : page.select("a[href]")) {
            urls.add(a.absUrl("href"));
        }
        return List.copyOf(urls);
    }

    public static int imagesWithoutAlt(Document page) {
        return page.select("img:not([alt])").size();
    }

    public static void main(String[] args) throws IOException {
        Document page = Jsoup.parse(Files.readString(Path.of("stall.html")), "https://baraka.example/");
        System.out.println(title(page));
        System.out.println(headings(page));
        System.out.println(links(page));
        System.out.println(imagesWithoutAlt(page) + " images without alt text");
    }
}
