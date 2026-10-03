import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class TimeMachine {

    public record Result(String playlistId, List<String> added, List<Song> missing) {}

    // Find every song, make the playlist, add what was found, and report what wasn't.
    public static Result build(List<Song> chart, String date, MusicService music) throws Exception {
        List<String> found = new ArrayList<>();
        List<Song> missing = new ArrayList<>();
        for (Song song : chart) {
            Optional<String> id = music.findTrack(song.title(), song.artist());
            if (id.isPresent()) found.add(id.get()); else missing.add(song);
        }
        String playlist = music.createPlaylist("Time Machine: " + date, "The Pwani FM Top 10 for the week of " + date);
        if (!found.isEmpty()) music.addTracks(playlist, found);
        return new Result(playlist, found, missing);
    }

    public static void main(String[] args) throws Exception {
        String date = args.length > 0 ? args[0] : "2016-12-03";
        Path chartFile = Path.of("chart-" + date + ".html");
        if (!Files.exists(chartFile)) {
            System.out.println("No saved chart for " + date + ". Try 2016-12-03.");
            return;
        }
        List<Song> chart = ChartScraper.read(Jsoup.parse(Files.readString(chartFile)));

        String clientId = System.getenv("SPOTIFY_CLIENT_ID");
        MusicService music = clientId == null ? new PracticeMusicService() : new SpotifyMusicService(login(clientId));
        System.out.println("Using " + (clientId == null ? "the practice catalogue (set SPOTIFY_CLIENT_ID to use Spotify)" : "Spotify"));

        Result result = build(chart, date, music);
        System.out.println("Created playlist \"Time Machine: " + date + "\" with " + result.added().size() + " of " + chart.size() + " songs.");
        for (Song s : result.missing()) {
            System.out.println("  not found: #" + s.position() + " " + s.title() + " by " + s.artist());
        }
    }

    // The full PKCE login: open the browser, catch the redirect on localhost, swap the code for a token.
    static String login(String clientId) throws Exception {
        String redirect = "http://127.0.0.1:8888/callback";
        String verifier = Pkce.newVerifier();
        String state = UUID.randomUUID().toString();

        CompletableFuture<String> code = new CompletableFuture<>();
        HttpServer callback = HttpServer.create(new InetSocketAddress("127.0.0.1", 8888), 0);
        callback.createContext("/callback", exchange -> {
            String message;
            try {
                code.complete(SpotifyAuth.codeFromCallback(exchange.getRequestURI().getRawQuery(), state));
                message = "Logged in. You can close this tab and go back to the terminal.";
            } catch (IllegalStateException e) {
                code.completeExceptionally(e);
                message = e.getMessage();
            }
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.close();
        });
        callback.start();
        try {
            URI url = SpotifyAuth.authorizeUrl(clientId, redirect, "playlist-modify-private", state, Pkce.challenge(verifier));
            System.out.println("Open this link to log in to Spotify:\n" + url);
            String authCode = code.get(5, TimeUnit.MINUTES);

            HttpResponse<String> token = HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create(SpotifyAuth.TOKEN))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(SpotifyAuth.tokenRequestBody(clientId, authCode, redirect, verifier)))
                    .build(), HttpResponse.BodyHandlers.ofString());
            if (token.statusCode() != 200) throw new IllegalStateException("Token request failed: " + token.body());
            return new JSONObject(token.body()).getString("access_token");
        } finally {
            callback.stop(0);
        }
    }
}
