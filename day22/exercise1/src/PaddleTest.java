import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaddleTest {

    @Test
    void movingUpDecreasesY() {
        Paddle paddle = new Paddle(250, 100, 0, 600);
        paddle.moveUp(20);
        assertEquals(230, paddle.getY());
    }

    @Test
    void movingDownIncreasesY() {
        Paddle paddle = new Paddle(250, 100, 0, 600);
        paddle.moveDown(20);
        assertEquals(270, paddle.getY());
    }

    @Test
    void cannotMoveUpPastTheTopEdge() {
        Paddle paddle = new Paddle(10, 100, 0, 600);
        paddle.moveUp(50);
        assertEquals(0, paddle.getY());
    }

    @Test
    void cannotMoveDownPastTheBottomEdge() {
        Paddle paddle = new Paddle(550, 100, 0, 600);
        paddle.moveDown(50);
        assertEquals(500, paddle.getY());
    }
}
