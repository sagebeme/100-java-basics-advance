import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaddleTest {

    // A court 400 high, with an 80-high paddle starting at 160.
    private Paddle paddle() {
        return new Paddle(20, 160, 80, 400);
    }

    @Test
    void knowsWhereItIs() {
        Paddle paddle = paddle();
        assertEquals(20, paddle.getX());
        assertEquals(160, paddle.getTop());
        assertEquals(240, paddle.getBottom());
    }

    @Test
    void movesUpAndDown() {
        Paddle paddle = paddle();
        paddle.moveUp(30);
        assertEquals(130, paddle.getTop());
        paddle.moveDown(50);
        assertEquals(180, paddle.getTop());
    }

    @Test
    void stopsAtTheTopOfTheCourt() {
        Paddle paddle = paddle();
        paddle.moveUp(1000);
        assertEquals(0, paddle.getTop());
    }

    @Test
    void stopsWithItsBottomAtTheBottomOfTheCourt() {
        Paddle paddle = paddle();
        paddle.moveDown(1000);
        assertEquals(320, paddle.getTop());
        assertEquals(400, paddle.getBottom());
    }

    @Test
    void startsInsideTheCourtEvenIfToldOtherwise() {
        assertEquals(320, new Paddle(20, 390, 80, 400).getTop());
        assertEquals(0, new Paddle(20, -10, 80, 400).getTop());
    }

    @Test
    void coversItsWholeLengthEdgesIncluded() {
        Paddle paddle = paddle();
        assertTrue(paddle.covers(160));
        assertTrue(paddle.covers(200));
        assertTrue(paddle.covers(240));
        assertFalse(paddle.covers(159));
        assertFalse(paddle.covers(241));
    }

    @Test
    void saysWhereOnThePaddleTheBallHit() {
        Paddle paddle = paddle();
        assertEquals(-1.0, paddle.hitOffset(160), 1e-9);
        assertEquals(0.0, paddle.hitOffset(200), 1e-9);
        assertEquals(0.5, paddle.hitOffset(220), 1e-9);
        assertEquals(1.0, paddle.hitOffset(240), 1e-9);
    }

    @Test
    void aMissHasNoOffset() {
        assertThrows(IllegalArgumentException.class, () -> paddle().hitOffset(300));
    }

    @Test
    void refusesAPaddleThatCannotFit() {
        assertThrows(IllegalArgumentException.class, () -> new Paddle(20, 0, 0, 400));
        assertThrows(IllegalArgumentException.class, () -> new Paddle(20, 0, 500, 400));
    }
}
