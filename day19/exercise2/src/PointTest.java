import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PointTest {

    @Test
    void toStringShowsBothCoordinates() {
        assertEquals("(1, 2)", new Point(1, 2).toString());
    }

    @Test
    void twoPointsWithTheSameCoordinatesAreEqual() {
        assertEquals(new Point(1, 2), new Point(1, 2));
    }

    @Test
    void pointsWithDifferentCoordinatesAreNotEqual() {
        assertNotEquals(new Point(1, 2), new Point(3, 4));
    }

    @Test
    void equalPointsHaveTheSameHashCode() {
        assertEquals(new Point(1, 2).hashCode(), new Point(1, 2).hashCode());
    }
}
