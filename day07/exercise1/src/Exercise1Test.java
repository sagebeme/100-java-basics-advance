import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void showsGuessedLettersAndBlanksTheRest() {
        assertEquals("_ A V A", Exercise1.displayWord("JAVA", List.of('A', 'V')));
    }

    @Test
    void showsAllBlanksWithNoGuesses() {
        assertEquals("_ _ _ _", Exercise1.displayWord("JAVA", List.of()));
    }

    @Test
    void showsTheWholeWordOnceEveryLetterIsGuessed() {
        assertEquals("J A V A", Exercise1.displayWord("JAVA", List.of('J', 'A', 'V')));
    }
}
