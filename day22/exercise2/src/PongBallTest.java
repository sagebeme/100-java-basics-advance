import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PongBallTest {

    // A 600×400 court. Both paddles are 80 high, from y = 160 to 240.
    private final Paddle left = new Paddle(20, 160, 80, 400);
    private final Paddle right = new Paddle(580, 160, 80, 400);

    @Test
    void movesAcrossTheCourt() {
        PongBall ball = new PongBall(300, 200, -6, 2, 600, 400);
        assertEquals("", ball.step(left, right));
        assertEquals(294, ball.getX());
        assertEquals(202, ball.getY());
    }

    @Test
    void bouncesOffTheTopAndBottomWalls() {
        PongBall ball = new PongBall(300, 398, -6, 5, 600, 400);
        ball.step(left, right);
        assertEquals(397, ball.getY());
        assertEquals(-5, ball.getDy());

        PongBall high = new PongBall(300, 3, 6, -5, 600, 400);
        high.step(left, right);
        assertEquals(2, high.getY());
        assertEquals(5, high.getDy());
    }

    @Test
    void theLeftPaddleSendsItBackAFrameFaster() {
        PongBall ball = new PongBall(24, 200, -6, 0, 600, 400);
        assertEquals("", ball.step(left, right));
        assertEquals(22, ball.getX());
        assertEquals(7, ball.getDx());
    }

    @Test
    void theRightPaddleSendsItBackAFrameFaster() {
        PongBall ball = new PongBall(576, 200, 6, 0, 600, 400);
        assertEquals("", ball.step(left, right));
        assertEquals(578, ball.getX());
        assertEquals(-7, ball.getDx());
    }

    @Test
    void aBallPastTheLeftPaddleIsAPointForTheRight() {
        PongBall ball = new PongBall(24, 100, -9, 3, 600, 400);
        assertEquals("RIGHT", ball.step(left, right));
        assertEquals(300, ball.getX());
        assertEquals(200, ball.getY());
        assertEquals(-9, ball.getDx(), "served at the starting speed, towards the player who lost the point");
    }

    @Test
    void aBallPastTheRightPaddleIsAPointForTheLeft() {
        PongBall ball = new PongBall(576, 300, 6, 0, 600, 400);
        assertEquals("LEFT", ball.step(left, right));
        assertEquals(300, ball.getX());
        assertEquals(6, ball.getDx());
    }

    @Test
    void aServeAfterARallyIsBackToTheStartingSpeed() {
        PongBall ball = new PongBall(24, 200, -6, 0, 600, 400);
        ball.step(left, right);
        assertEquals(7, ball.getDx());
        right.moveUp(1000);
        String point = "";
        for (int i = 0; i < 200 && point.isEmpty(); i++) {
            point = ball.step(left, right);
        }
        assertEquals("LEFT", point);
        assertEquals(6, ball.getDx());
    }

    @Test
    void refusesABallThatNeverGoesSideways() {
        assertThrows(IllegalArgumentException.class, () -> new PongBall(300, 200, 0, 5, 600, 400));
    }
}
