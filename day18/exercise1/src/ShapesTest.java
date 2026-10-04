import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShapesTest {

    @Test
    void aSquareOnItsPointHasCornersUpRightDownLeft() {
        double[][] p = Shapes.regularPolygon(100, 100, 50, 4);
        assertArrayEquals(new double[]{100, 150, 100, 50}, p[0], 1e-9);
        assertArrayEquals(new double[]{50, 100, 150, 100}, p[1], 1e-9);
    }

    @Test
    void everyCornerIsTheRadiusAway() {
        double[][] p = Shapes.regularPolygon(0, 0, 30, 7);
        for (int i = 0; i < 7; i++) assertEquals(30, Math.hypot(p[0][i], p[1][i]), 1e-9);
    }

    @Test
    void refusesTwoSidedShapes() {
        assertThrows(IllegalArgumentException.class, () -> Shapes.regularPolygon(0, 0, 10, 2));
    }

    @Test
    void centresARectangle() {
        assertArrayEquals(new double[]{190, 60}, Shapes.centredRectangle(280, 95, 180, 70), 1e-9);
    }

    @Test
    void knowsWhenACircleFits() {
        assertTrue(Shapes.circleFits(50, 50, 50, 560, 360));
        assertFalse(Shapes.circleFits(30, 50, 50, 560, 360));
    }
}
