import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordGenTest {

    @Test
    void generatesAPasswordOfTheRequestedLength() {
        assertEquals(12, PasswordGen.generate(12, true, true, true, new Random()).length());
    }

    @Test
    void refusesToGenerateWithNoCharacterSetsEnabled() {
        assertThrows(IllegalArgumentException.class,
                () -> PasswordGen.generate(10, false, false, false, new Random()));
    }

    @Test
    void aShortPasswordIsWeak() {
        assertEquals("weak", PasswordGen.strength("ab1"));
    }

    @Test
    void aLongPasswordWithOneCharacterTypeIsMedium() {
        assertEquals("medium", PasswordGen.strength("abcdefghij"));
    }

    @Test
    void aLongVariedPasswordIsStrong() {
        assertEquals("strong", PasswordGen.strength("abcdefgh1234!@"));
    }
}
