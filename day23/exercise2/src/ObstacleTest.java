import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObstacleTest {

    @Test
    void movingAdvancesByItsSpeed() {
        Obstacle car = new Obstacle(0, 100, 5, "car");
        car.move();
        assertEquals(5, car.getX());
    }

    @Test
    void isNotOffScreenWhileWithinBounds() {
        Obstacle car = new Obstacle(300, 100, 5, "car");
        assertFalse(car.isOffScreen(600));
    }

    @Test
    void isOffScreenFarPastTheRightEdge() {
        Obstacle car = new Obstacle(700, 100, 5, "car");
        assertTrue(car.isOffScreen(600));
    }

    @Test
    void isOffScreenFarPastTheLeftEdge() {
        Obstacle car = new Obstacle(-100, 100, 5, "car");
        assertTrue(car.isOffScreen(600));
    }
}
