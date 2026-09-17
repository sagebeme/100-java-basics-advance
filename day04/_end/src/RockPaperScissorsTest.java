import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RockPaperScissorsTest {

    @Test
    void sameChoiceIsADraw() {
        assertEquals("draw", RockPaperScissors.determineOutcome(0, 0));
        assertEquals("draw", RockPaperScissors.determineOutcome(1, 1));
        assertEquals("draw", RockPaperScissors.determineOutcome(2, 2));
    }

    @Test
    void rockBeatsScissors() {
        assertEquals("win", RockPaperScissors.determineOutcome(0, 2));
    }

    @Test
    void paperBeatsRock() {
        assertEquals("win", RockPaperScissors.determineOutcome(1, 0));
    }

    @Test
    void scissorsBeatsPaper() {
        assertEquals("win", RockPaperScissors.determineOutcome(2, 1));
    }

    @Test
    void losingCombosReportLose() {
        assertEquals("lose", RockPaperScissors.determineOutcome(0, 1)); // rock vs paper
        assertEquals("lose", RockPaperScissors.determineOutcome(1, 2)); // paper vs scissors
        assertEquals("lose", RockPaperScissors.determineOutcome(2, 0)); // scissors vs rock
    }
}
