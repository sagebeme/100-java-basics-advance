import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HangmanTest {

    @Test
    void isNotCompleteWithNoGuesses() {
        assertFalse(Hangman.isWordComplete("java", new ArrayList<>()));
    }

    @Test
    void isCompleteWhenEveryLetterIsGuessed() {
        ArrayList<Character> guessed = new ArrayList<>(Arrays.asList('j', 'a', 'v'));
        assertTrue(Hangman.isWordComplete("java", guessed));
    }

    @Test
    void isNotCompleteWithSomeLettersMissing() {
        ArrayList<Character> guessed = new ArrayList<>(Arrays.asList('j', 'a'));
        assertFalse(Hangman.isWordComplete("java", guessed));
    }

    @Test
    void rendersUnguessedLettersAsUnderscores() {
        ArrayList<Character> guessed = new ArrayList<>(Arrays.asList('j', 'v'));
        assertEquals("j _ v _", Hangman.renderWord("java", guessed));
    }

    @Test
    void rendersAllUnderscoresWithNoGuesses() {
        assertEquals("_ _ _ _", Hangman.renderWord("java", new ArrayList<>()));
    }
}
