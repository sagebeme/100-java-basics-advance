import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HigherLowerGameTest {

    @Test
    void guessingACorrectlyWhenAHasMore() {
        assertTrue(HigherLowerGame.isCorrectGuess("A", 500, 300));
    }

    @Test
    void guessingACorrectlyWhenBHasMore() {
        assertFalse(HigherLowerGame.isCorrectGuess("A", 300, 500));
    }

    @Test
    void guessingBCorrectlyWhenBHasMore() {
        assertTrue(HigherLowerGame.isCorrectGuess("B", 300, 500));
    }

    @Test
    void guessingBIncorrectlyWhenAHasMore() {
        assertFalse(HigherLowerGame.isCorrectGuess("B", 500, 300));
    }
}
