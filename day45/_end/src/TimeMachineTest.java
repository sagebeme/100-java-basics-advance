import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeMachineTest {

    private static List<Song> chart() throws IOException {
        try (InputStream in = TimeMachineTest.class.getResourceAsStream("/chart-2016-12-03.html")) {
            return ChartScraper.read(Jsoup.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8)));
        }
    }

    @Test
    void readsTheChartInOrderWithoutTheAdvert() throws IOException {
        List<Song> songs = chart();
        assertEquals(10, songs.size());
        assertEquals(new Song(1, "Bahari Yangu", "Zawadi & The Tide"), songs.get(0));
        assertEquals(10, songs.get(9).position());
    }

    @Test
    void normalisesTitlesForMatching() {
        assertEquals("nairobi nights", ChartScraper.normalise("Nairobi Nights (feat. DJ Kito)"));
        assertEquals("gengetone anthem", ChartScraper.normalise("Gengetone  Anthem!"));
        assertEquals("pole pole", ChartScraper.normalise("Pole Pole [ft. Someone]"));
    }

    @Test
    void buildsAPlaylistAndReportsWhatsMissing() throws Exception {
        PracticeMusicService music = new PracticeMusicService();
        TimeMachine.Result result = TimeMachine.build(chart(), "2016-12-03", music);
        assertEquals(8, result.added().size());
        assertEquals(List.of("Sunsets in Lamu", "Mvua"), result.missing().stream().map(Song::title).toList());
        assertEquals(result.added(), music.tracksIn(result.playlistId()));
        assertEquals("Time Machine: 2016-12-03", music.nameOf(result.playlistId()));
    }

    @Test
    void pkceMatchesTheExampleInTheStandard() {
        // RFC 7636, Appendix B
        assertEquals("E9Melhoa2OwvFrEMTJguCHaoeK1t8URWbuGJSstw-cM", Pkce.challenge("dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk"));
        String verifier = Pkce.newVerifier();
        assertTrue(verifier.length() >= 43 && verifier.length() <= 128);
        assertTrue(verifier.matches("[A-Za-z0-9_-]+"));
    }

    @Test
    void buildsTheLoginLink() {
        String url = SpotifyAuth.authorizeUrl("abc123", "http://127.0.0.1:8888/callback", "playlist-modify-private", "s1", "ch").toString();
        assertTrue(url.startsWith("https://accounts.spotify.com/authorize?response_type=code&client_id=abc123"));
        assertTrue(url.contains("&redirect_uri=http%3A%2F%2F127.0.0.1%3A8888%2Fcallback"));
        assertTrue(url.contains("&code_challenge_method=S256&code_challenge=ch"));
    }

    @Test
    void acceptsOnlyACallbackWithOurState() {
        assertEquals("AQB-xyz", SpotifyAuth.codeFromCallback("code=AQB-xyz&state=s1", "s1"));
        assertThrows(IllegalStateException.class, () -> SpotifyAuth.codeFromCallback("code=AQB-xyz&state=evil", "s1"));
        assertThrows(IllegalStateException.class, () -> SpotifyAuth.codeFromCallback("error=access_denied&state=s1", "s1"));
    }

    @Test
    void theTokenRequestCarriesTheVerifier() {
        assertEquals("grant_type=authorization_code&code=c%2F1&redirect_uri=http%3A%2F%2F127.0.0.1%3A8888%2Fcallback&client_id=abc&code_verifier=v",
                SpotifyAuth.tokenRequestBody("abc", "c/1", "http://127.0.0.1:8888/callback", "v"));
    }
}
