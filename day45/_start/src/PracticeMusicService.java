import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// A small pretend catalogue that lives in memory. Like a real service, it doesn't have every song.
public class PracticeMusicService implements MusicService {

    private final Map<String, String> catalogue = new LinkedHashMap<>(); // "title|artist" (normalised) → id
    private final Map<String, String> playlistNames = new LinkedHashMap<>();
    private final Map<String, List<String>> playlists = new LinkedHashMap<>();

    public PracticeMusicService() {
        add("Bahari Yangu", "Zawadi & The Tide");
        add("Nairobi Nights", "Amani Waweru");
        add("Matatu Ride", "Boda Boyz");
        add("Pole Pole", "Kalif");
        add("Rafiki Wangu", "The Ngoma Collective");
        add("Gengetone Anthem", "Mtaa Kings");
        add("Kesho", "Otieno Flow");
        add("Coast to Coast", "Zawadi & The Tide");
        // Not in the catalogue: "Sunsets in Lamu" and "Mvua"
    }

    private void add(String title, String artist) {
        catalogue.put(key(title, artist), "track:" + (catalogue.size() + 1));
    }

    private static String key(String title, String artist) {
        return ChartScraper.normalise(title) + "|" + ChartScraper.normalise(artist);
    }

    @Override
    public Optional<String> findTrack(String title, String artist) {
        return Optional.ofNullable(catalogue.get(key(title, artist)));
    }

    @Override
    public String createPlaylist(String name, String description) {
        String id = "playlist:" + (playlists.size() + 1);
        playlistNames.put(id, name);
        playlists.put(id, new ArrayList<>());
        return id;
    }

    @Override
    public void addTracks(String playlistId, List<String> trackIds) {
        playlists.get(playlistId).addAll(trackIds);
    }

    public List<String> tracksIn(String playlistId) {
        return List.copyOf(playlists.get(playlistId));
    }

    public String nameOf(String playlistId) {
        return playlistNames.get(playlistId);
    }
}
