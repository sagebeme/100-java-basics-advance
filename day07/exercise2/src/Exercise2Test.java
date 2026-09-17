import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void aFreshLetterIsValid() {
        assertTrue(Exercise2.isValidGuess("a", List.of('b', 'c')));
    }

    @Test
    void aLetterAlreadyGuessedIsInvalid() {
        assertFalse(Exercise2.isValidGuess("a", List.of('a')));
    }

    @Test
    void aDigitIsInvalid() {
        assertFalse(Exercise2.isValidGuess("5", List.of()));
    }

    @Test
    void moreThanOneCharacterIsInvalid() {
        assertFalse(Exercise2.isValidGuess("ab", List.of()));
    }

    @Test
    void emptyInputIsInvalid() {
        assertFalse(Exercise2.isValidGuess("", List.of()));
    }
}
