import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BallTest {

    @Test
    void movingAdvancesPositionByVelocity() {
        Ball ball = new Ball(100, 100, 5, 3);
        ball.move();
        assertEquals(105, ball.getX());
        assertEquals(103, ball.getY());
    }

    @Test
    void bounceXReversesHorizontalVelocity() {
        Ball ball = new Ball(0, 0, 5, 3);
        ball.bounceX();
        assertEquals(-5, ball.getVelocityX());
    }

    @Test
    void bounceYReversesVerticalVelocity() {
        Ball ball = new Ball(0, 0, 5, 3);
        ball.bounceY();
        assertEquals(-3, ball.getVelocityY());
    }

    @Test
    void collidesWithAPaddleItIsLevelWith() {
        Ball ball = new Ball(20, 250, -5, 0);
        Paddle paddle = new Paddle(200, 100, 0, 600);
        assertTrue(ball.collidesWith(paddle, 20));
    }

    @Test
    void doesNotCollideWhenPastThePaddleHeight() {
        Ball ball = new Ball(20, 50, -5, 0);
        Paddle paddle = new Paddle(200, 100, 0, 600);
        assertFalse(ball.collidesWith(paddle, 20));
    }
}
