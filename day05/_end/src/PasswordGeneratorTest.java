import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordGeneratorTest {

    @Test
    void producesExactlyAsManyCharactersAsRequested() {
        ArrayList<Character> password = PasswordGenerator.buildPasswordChars(4, 2, 3, new Random());
        assertEquals(9, password.size());
    }

    @Test
    void everyCharacterComesFromOneOfTheThreePools() {
        ArrayList<Character> password = PasswordGenerator.buildPasswordChars(5, 5, 5, new Random());
        for (char c : password) {
            boolean fromAPool = PasswordGenerator.LETTERS.indexOf(c) >= 0
                    || PasswordGenerator.SYMBOLS.indexOf(c) >= 0
                    || PasswordGenerator.NUMBERS.indexOf(c) >= 0;
            assertTrue(fromAPool, "'" + c + "' should belong to letters, symbols or numbers");
        }
    }

    @Test
    void zeroOfEveryKindMeansAnEmptyPassword() {
        ArrayList<Character> password = PasswordGenerator.buildPasswordChars(0, 0, 0, new Random());
        assertEquals(0, password.size());
    }
}
