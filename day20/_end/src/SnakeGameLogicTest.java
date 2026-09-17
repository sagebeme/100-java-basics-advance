import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SnakeGameLogicTest {

    @Test
    void positionsInsideTheBoardAreLeftAlone() {
        assertArrayEquals(new int[]{100, 100}, SnakeGameLogic.wrap(100, 100));
    }

    @Test
    void goingOffTheLeftEdgeWrapsToTheRight() {
        assertArrayEquals(new int[]{580, 100}, SnakeGameLogic.wrap(-20, 100));
    }

    @Test
    void goingOffTheRightEdgeWrapsToTheLeft() {
        assertArrayEquals(new int[]{0, 100}, SnakeGameLogic.wrap(600, 100));
    }

    @Test
    void goingOffTheTopWrapsToTheBottom() {
        assertArrayEquals(new int[]{100, 580}, SnakeGameLogic.wrap(100, -20));
    }

    @Test
    void goingOffTheBottomWrapsToTheTop() {
        assertArrayEquals(new int[]{100, 0}, SnakeGameLogic.wrap(100, 600));
    }
}
