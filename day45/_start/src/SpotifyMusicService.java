import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;

// The real thing: Spotify's Web API, with an access token from the PKCE login.
public class SpotifyMusicService implements MusicService {

    private static final String API = "https://api.spotify.com/v1";
    private final HttpClient http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    private final String token;

    public SpotifyMusicService(String accessToken) {
        this.token = accessToken;
    }

    private JSONObject call(HttpRequest.Builder request) throws Exception {
        HttpResponse<String> response = http.send(request
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(15)).build(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 300) {
            throw new IllegalStateException("Spotify answered " + response.statusCode() + ": " + response.body());
        }
        return response.body().isBlank() ? new JSONObject() : new JSONObject(response.body());
    }

    @Override
    public Optional<String> findTrack(String title, String artist) throws Exception {
        String q = URLEncoder.encode("track:" + title + " artist:" + artist, StandardCharsets.UTF_8);
        JSONArray items = call(HttpRequest.newBuilder(URI.create(API + "/search?type=track&limit=1&q=" + q)).GET())
                .getJSONObject("tracks").getJSONArray("items");
        return items.isEmpty() ? Optional.empty() : Optional.of(items.getJSONObject(0).getString("uri"));
    }

    @Override
    public String createPlaylist(String name, String description) throws Exception {
        String userId = call(HttpRequest.newBuilder(URI.create(API + "/me")).GET()).getString("id");
        JSONObject body = new JSONObject().put("name", name).put("description", description).put("public", false);
        return call(HttpRequest.newBuilder(URI.create(API + "/users/" + userId + "/playlists"))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))).getString("id");
    }

    @Override
    public void addTracks(String playlistId, List<String> trackIds) throws Exception {
        JSONObject body = new JSONObject().put("uris", new JSONArray(trackIds));
        call(HttpRequest.newBuilder(URI.create(API + "/playlists/" + playlistId + "/tracks"))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString())));
    }
}
