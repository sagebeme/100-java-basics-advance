import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BallTest {

    @Test
    void movesByItsVelocityEachFrame() {
        Ball ball = new Ball(10, 20, 7, 5, 100, 60);
        ball.step();
        assertEquals(17, ball.getX());
        assertEquals(25, ball.getY());
        assertEquals(7, ball.getDx());
        assertEquals(5, ball.getDy());
    }

    @Test
    void bouncesOffTheRightEdge() {
        Ball ball = new Ball(96, 30, 10, 0, 100, 60);
        ball.step();
        assertEquals(94, ball.getX());
        assertEquals(-10, ball.getDx());
    }

    @Test
    void bouncesOffTheLeftEdge() {
        Ball ball = new Ball(3, 30, -10, 0, 100, 60);
        ball.step();
        assertEquals(7, ball.getX());
        assertEquals(10, ball.getDx());
    }

    @Test
    void bouncesOffTheTopAndBottom() {
        Ball ball = new Ball(50, 57, 0, 5, 100, 60);
        ball.step();
        assertEquals(58, ball.getY());
        assertEquals(-5, ball.getDy());

        Ball high = new Ball(50, 2, 0, -5, 100, 60);
        high.step();
        assertEquals(3, high.getY());
        assertEquals(5, high.getDy());
    }

    @Test
    void landingExactlyOnTheEdgeIsNotABounce() {
        Ball ball = new Ball(90, 30, 10, 0, 100, 60);
        ball.step();
        assertEquals(100, ball.getX());
        assertEquals(10, ball.getDx());
    }

    @Test
    void aCornerBouncesBothWays() {
        Ball ball = new Ball(98, 58, 4, 4, 100, 60);
        ball.step();
        assertEquals(98, ball.getX());
        assertEquals(58, ball.getY());
        assertEquals(-4, ball.getDx());
        assertEquals(-4, ball.getDy());
    }

    @Test
    void neverLeavesTheScreenOverManyFrames() {
        Ball ball = new Ball(10, 20, 7, 5, 100, 60);
        for (int i = 0; i < 1000; i++) {
            ball.step();
            assertTrue(ball.getX() >= 0 && ball.getX() <= 100, "x went to " + ball.getX());
            assertTrue(ball.getY() >= 0 && ball.getY() <= 60, "y went to " + ball.getY());
        }
    }

    @Test
    void stepManyIsTheSameAsSteppingOneAtATime() {
        Ball one = new Ball(10, 20, 7, 5, 100, 60);
        Ball many = new Ball(10, 20, 7, 5, 100, 60);
        for (int i = 0; i < 37; i++) {
            one.step();
        }
        many.step(37);
        assertEquals(one.getX(), many.getX());
        assertEquals(one.getY(), many.getY());
        assertEquals(one.getDx(), many.getDx());
        assertEquals(one.getDy(), many.getDy());
    }

    @Test
    void refusesABallOffTheScreen() {
        assertThrows(IllegalArgumentException.class, () -> new Ball(101, 20, 1, 1, 100, 60));
        assertThrows(IllegalArgumentException.class, () -> new Ball(10, -1, 1, 1, 100, 60));
        assertThrows(IllegalArgumentException.class, () -> new Ball(0, 0, 1, 1, 0, 60));
    }
}
