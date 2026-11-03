import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordGenTest {

    private static boolean onlyFrom(String password, String allowed) {
        for (char c : password.toCharArray()) {
            if (allowed.indexOf(c) < 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean anyFrom(String password, String set) {
        for (char c : password.toCharArray()) {
            if (set.indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
    }

    @Test
    void everyChosenKindAppearsEveryTime() {
        String all = PasswordGen.LOWER + PasswordGen.UPPER + PasswordGen.DIGITS + PasswordGen.SYMBOLS;
        for (int seed = 0; seed < 200; seed++) {
            String password = PasswordGen.generate(4, true, true, true, new Random(seed));
            assertEquals(4, password.length());
            assertTrue(onlyFrom(password, all), password);
            assertTrue(anyFrom(password, PasswordGen.LOWER), password);
            assertTrue(anyFrom(password, PasswordGen.UPPER), password);
            assertTrue(anyFrom(password, PasswordGen.DIGITS), password);
            assertTrue(anyFrom(password, PasswordGen.SYMBOLS), password);
        }
    }

    @Test
    void leavesOutTheKindsNotChosen() {
        for (int seed = 0; seed < 50; seed++) {
            String password = PasswordGen.generate(20, false, true, false, new Random(seed));
            assertEquals(20, password.length());
            assertTrue(onlyFrom(password, PasswordGen.LOWER + PasswordGen.DIGITS), password);
            assertTrue(anyFrom(password, PasswordGen.DIGITS), password);
        }
    }

    @Test
    void theGuaranteedCharactersAreShuffledIn() {
        boolean firstWasNotLower = false;
        for (int seed = 0; seed < 50; seed++) {
            String password = PasswordGen.generate(12, true, true, true, new Random(seed));
            if (PasswordGen.LOWER.indexOf(password.charAt(0)) < 0) {
                firstWasNotLower = true;
            }
        }
        assertTrue(firstWasNotLower, "the lower-case letter always came first: shuffle the result");
    }

    @Test
    void theSameSeedGivesTheSamePasswordAndDifferentSeedsDiffer() {
        assertEquals(PasswordGen.generate(16, true, true, true, new Random(7)),
                PasswordGen.generate(16, true, true, true, new Random(7)));
        assertNotEquals(PasswordGen.generate(16, true, true, true, new Random(7)),
                PasswordGen.generate(16, true, true, true, new Random(8)));
    }

    @Test
    void refusesALengthTooShortForTheKinds() {
        assertThrows(IllegalArgumentException.class,
                () -> PasswordGen.generate(3, true, true, true, new Random(1)));
        assertEquals(1, PasswordGen.generate(1, false, false, false, new Random(1)).length());
    }

    @Test
    void countsKinds() {
        assertEquals(1, PasswordGen.kinds("kenya"));
        assertEquals(2, PasswordGen.kinds("Kenya"));
        assertEquals(3, PasswordGen.kinds("Kenya254"));
        assertEquals(4, PasswordGen.kinds("Kenya254!"));
        assertEquals(0, PasswordGen.kinds(""));
    }

    @Test
    void judgesStrength() {
        assertEquals("weak", PasswordGen.strength("Ke254!"));
        assertEquals("weak", PasswordGen.strength("nairobicity"));
        assertEquals("weak", PasswordGen.strength("0722000000"));
        assertEquals("medium", PasswordGen.strength("Nairobi2024"));
        assertEquals("medium", PasswordGen.strength("nairobicity2024"));
        assertEquals("strong", PasswordGen.strength("Nairobi-City24"));
        assertEquals("strong", PasswordGen.strength("Nairobicity24"));
    }
}
