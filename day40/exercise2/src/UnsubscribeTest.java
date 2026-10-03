import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnsubscribeTest {

    private static final String SECRET = "a-long-random-secret";

    @Test
    void tokensAreSixteenHexCharactersAndIgnoreCapitals() {
        String t = Unsubscribe.token("wanjiku@example.com", SECRET);
        assertTrue(t.matches("[0-9a-f]{16}"));
        assertEquals(t, Unsubscribe.token(" Wanjiku@Example.com ", SECRET));
    }

    @Test
    void differentEmailsOrSecretsGiveDifferentTokens() {
        assertNotEquals(Unsubscribe.token("wanjiku@example.com", SECRET), Unsubscribe.token("otieno@example.com", SECRET));
        assertNotEquals(Unsubscribe.token("wanjiku@example.com", SECRET), Unsubscribe.token("wanjiku@example.com", "other"));
    }

    @Test
    void matchesAKnownSha256() {
        // SHA-256("s:a@b.co") starts with these 16 hex characters (check: printf 's:a@b.co' | sha256sum)
        assertEquals("0375aac2f501aff7", Unsubscribe.token("a@b.co", "s"));
    }

    @Test
    void buildsAnEncodedLink() {
        String link = Unsubscribe.link("https://safari.example/leave", "Wanjiku@Example.com", SECRET);
        assertEquals("https://safari.example/leave?email=wanjiku%40example.com&token="
                + Unsubscribe.token("wanjiku@example.com", SECRET), link);
    }

    @Test
    void onlyTheRightTokenIsValid() {
        String t = Unsubscribe.token("wanjiku@example.com", SECRET);
        assertTrue(Unsubscribe.isValid("wanjiku@example.com", t, SECRET));
        assertFalse(Unsubscribe.isValid("otieno@example.com", t, SECRET));
        assertFalse(Unsubscribe.isValid("wanjiku@example.com", "0000000000000000", SECRET));
        assertFalse(Unsubscribe.isValid("wanjiku@example.com", null, SECRET));
    }
}
