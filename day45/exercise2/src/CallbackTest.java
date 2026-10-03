import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CallbackTest {

    @Test
    void returnsTheCodeWhenTheStateMatches() {
        assertEquals("AQB-x/y", SpotifyAuth.codeFromCallback("code=AQB-x%2Fy&state=s1", "s1"));
    }

    @Test
    void refusesAWrongOrMissingState() {
        assertThrows(IllegalStateException.class, () -> SpotifyAuth.codeFromCallback("code=abc&state=evil", "s1"));
        assertThrows(IllegalStateException.class, () -> SpotifyAuth.codeFromCallback("code=abc", "s1"));
    }

    @Test
    void reportsARefusedLogin() {
        assertThrows(IllegalStateException.class, () -> SpotifyAuth.codeFromCallback("error=access_denied&state=s1", "s1"));
        assertThrows(IllegalStateException.class, () -> SpotifyAuth.codeFromCallback(null, "s1"));
    }
}
