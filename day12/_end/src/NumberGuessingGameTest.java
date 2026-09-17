import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberGuessingGameTest {

    @Test
    void easyModeGivesTenAttempts() {
        assertEquals(10, NumberGuessingGame.attemptsForDifficulty("easy"));
    }

    @Test
    void hardModeGivesFiveAttempts() {
        assertEquals(5, NumberGuessingGame.attemptsForDifficulty("hard"));
    }

    @Test
    void anythingOtherThanEasyCountsAsHard() {
        assertEquals(5, NumberGuessingGame.attemptsForDifficulty("medium"));
    }
}
