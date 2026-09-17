import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurtleCrossingLogicTest {

    @Test
    void carsPastTheRightEdgeWrapToTheLeft() {
        assertEquals(-50, TurtleCrossingLogic.wrapCarX(601));
    }

    @Test
    void carsPastTheLeftEdgeWrapToTheRight() {
        assertEquals(TurtleCrossingLogic.WIDTH, TurtleCrossingLogic.wrapCarX(-51), 0.001);
    }

    @Test
    void carsOnScreenAreLeftAlone() {
        assertEquals(300, TurtleCrossingLogic.wrapCarX(300));
    }

    @Test
    void overlappingRectanglesCollide() {
        assertTrue(TurtleCrossingLogic.collides(100, 100, 90, 90));
    }

    @Test
    void farApartRectanglesDoNotCollide() {
        assertFalse(TurtleCrossingLogic.collides(100, 100, 400, 400));
    }

    @Test
    void reachingTheTopOfTheScreenLevelsUp() {
        assertTrue(TurtleCrossingLogic.hasReachedTop(40));
        assertFalse(TurtleCrossingLogic.hasReachedTop(60));
    }
}
