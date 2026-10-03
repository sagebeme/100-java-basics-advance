import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnvReaderTest {

    private final Map<String, String> env = Map.of("PORT", "8080", "DEBUG", "Yes", "NAME", "  ", "SHOP", " Duka la Mama ");

    @Test
    void textFallsBackForMissingOrBlankValues() {
        assertEquals("Duka", EnvReader.text(env, "NAME", "Duka"));
        assertEquals("Duka", EnvReader.text(env, "NOPE", "Duka"));
        assertEquals("Duka la Mama", EnvReader.text(env, "SHOP", "x"));
    }

    @Test
    void numbersFallBackWhenTheyArentNumbers() {
        assertEquals(8080, EnvReader.number(env, "PORT", 3000));
        assertEquals(3000, EnvReader.number(env, "SHOP", 3000));
        assertEquals(3000, EnvReader.number(env, "NOPE", 3000));
    }

    @Test
    void flagsUnderstandTheUsualWaysOfSayingYes() {
        assertTrue(EnvReader.flag(env, "DEBUG"));
        assertTrue(EnvReader.flag(Map.of("X", "ON"), "X"));
        assertTrue(EnvReader.flag(Map.of("X", "1"), "X"));
        assertFalse(EnvReader.flag(Map.of("X", "no"), "X"));
        assertFalse(EnvReader.flag(env, "NOPE"));
    }
}
