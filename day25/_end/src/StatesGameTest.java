import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatesGameTest {

    private final List<String> states = Arrays.asList("California", "Texas", "Kenya Village");

    @Test
    void aStateInTheListIsCorrect() {
        assertEquals(StatesGame.GuessResult.CORRECT,
                StatesGame.evaluateGuess("Texas", states, Arrays.asList()));
    }

    @Test
    void aStateAlreadyGuessedIsFlaggedEvenThoughValid() {
        assertEquals(StatesGame.GuessResult.ALREADY_GUESSED,
                StatesGame.evaluateGuess("Texas", states, Arrays.asList("Texas")));
    }

    @Test
    void aNameNotInTheListIsInvalid() {
        assertEquals(StatesGame.GuessResult.INVALID,
                StatesGame.evaluateGuess("Narnia", states, Arrays.asList()));
    }
}
