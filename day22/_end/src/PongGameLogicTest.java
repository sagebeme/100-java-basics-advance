import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PongGameLogicTest {

    @Test
    void ballTouchingTheLeftPaddleIsADeflection() {
        assertTrue(PongGameLogic.hitsLeftPaddle(15, 250, 200));
    }

    @Test
    void ballPastTheLeftPaddleHeightIsNotADeflection() {
        assertFalse(PongGameLogic.hitsLeftPaddle(15, 500, 200));
    }

    @Test
    void ballTouchingTheRightPaddleIsADeflection() {
        assertTrue(PongGameLogic.hitsRightPaddle(770, 250, 200));
    }

    @Test
    void ballAtTheTopEdgeBouncesOffTheWall() {
        assertTrue(PongGameLogic.hitsTopOrBottomWall(0));
    }

    @Test
    void ballInTheMiddleDoesNotTouchAWall() {
        assertFalse(PongGameLogic.hitsTopOrBottomWall(300));
    }

    @Test
    void ballPastTheRightEdgeIsAPointForPlayerOne() {
        assertTrue(PongGameLogic.leftPlayerScores(801));
    }

    @Test
    void ballPastTheLeftEdgeIsAPointForPlayerTwo() {
        assertTrue(PongGameLogic.rightPlayerScores(-1));
    }
}
