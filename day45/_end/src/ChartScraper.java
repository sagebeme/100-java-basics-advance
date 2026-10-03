import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ChartScraper {

    // The chart in order, without sponsored entries.
    public static List<Song> read(Document page) {
        List<Song> songs = new ArrayList<>();
        for (Element entry : page.select("ol.chart li.entry:not(.sponsored)")) {
            songs.add(new Song(Integer.parseInt(entry.select(".pos").text().trim()),
                    entry.select(".song").text().trim(),
                    entry.select(".artist").text().trim()));
        }
        return songs;
    }

    // For matching across services: "Nairobi Nights (feat. DJ Kito)" and "nairobi nights" are the same song.
    public static String normalise(String title) {
        return title.toLowerCase()
                .replaceAll("\\s*[(\\[]\\s*(feat|ft)\\.?[^)\\]]*[)\\]]", "") // drop "(feat. ...)"
                .replaceAll("[^\\p{L}\\p{N} ]", "")                        // drop punctuation
                .replaceAll("\\s+", " ")
                .trim();
    }
}
