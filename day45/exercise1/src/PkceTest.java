import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PkceTest {

    @Test
    void matchesTheExampleInRfc7636() {
        assertEquals("E9Melhoa2OwvFrEMTJguCHaoeK1t8URWbuGJSstw-cM", Pkce.challenge("dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk"));
    }

    @Test
    void verifiersAreLongUrlSafeAndDifferentEveryTime() {
        String a = Pkce.newVerifier();
        assertTrue(a.length() >= 43 && a.length() <= 128);
        assertTrue(a.matches("[A-Za-z0-9_-]+"));
        assertNotEquals(a, Pkce.newVerifier());
    }
}
