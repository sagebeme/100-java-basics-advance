import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void theHigherValueWins() {
        assertEquals("Instagram", Exercise2.determineWinner("Instagram", 500, "TikTok", 450));
    }

    @Test
    void aOrBAreValidGuesses() {
        assertTrue(Exercise2.isValidGuess("A"));
        assertTrue(Exercise2.isValidGuess("b"));
    }

    @Test
    void anythingElseIsAnInvalidGuess() {
        assertFalse(Exercise2.isValidGuess("C"));
        assertFalse(Exercise2.isValidGuess(null));
    }

    @Test
    void formatsAComparisonLine() {
        assertEquals("Compare A: Instagram vs B: TikTok", Exercise2.formatComparison("Instagram", "TikTok"));
    }
}
