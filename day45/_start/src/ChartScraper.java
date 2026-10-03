import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ChartScraper {

    // The chart in order, without sponsored entries: ol.chart li.entry, with .pos, .song and .artist.
    public static List<Song> read(Document page) {
        List<Song> songs = new ArrayList<>();
        // TODO
        return songs;
    }

    // For matching across services: "Nairobi Nights (feat. DJ Kito)" and "nairobi nights" are the same song.
    // Lower-case, drop a "(feat. ...)" or "[ft. ...]" part, drop punctuation, collapse spaces.
    public static String normalise(String title) {
        // TODO
        return title;
    }
}
