import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurtleRaceLogicTest {

    @Test
    void advanceMovesTheTurtleForwardByTheStep() {
        assertEquals(55.0, TurtleRaceLogic.advance(50.0, 5));
    }

    @Test
    void hasNotFinishedBeforeTheFinishLine() {
        assertFalse(TurtleRaceLogic.hasFinished(749));
    }

    @Test
    void hasFinishedAtOrPastTheFinishLine() {
        assertTrue(TurtleRaceLogic.hasFinished(750));
        assertTrue(TurtleRaceLogic.hasFinished(800));
    }
}
