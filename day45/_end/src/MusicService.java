import java.util.List;
import java.util.Optional;

// What the time machine needs from a music service. Spotify is one; the practice catalogue is another.
public interface MusicService {
    Optional<String> findTrack(String title, String artist) throws Exception;
    String createPlaylist(String name, String description) throws Exception;
    void addTracks(String playlistId, List<String> trackIds) throws Exception;
}
