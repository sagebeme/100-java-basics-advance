import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void convertsAGridCellToItsPixelPosition() {
        assertEquals(60, Exercise2.gridToPixel(3, 20));
    }

    @Test
    void convertsAPixelPositionToItsGridCell() {
        assertEquals(3, Exercise2.pixelToGrid(65, 20));
    }

    @Test
    void insidePositionsAreWithinBounds() {
        assertTrue(Exercise2.isWithinBounds(5, 5, 10, 10));
    }

    @Test
    void positionsOnOrPastTheEdgeAreOutOfBounds() {
        assertFalse(Exercise2.isWithinBounds(10, 5, 10, 10));
        assertFalse(Exercise2.isWithinBounds(-1, 5, 10, 10));
    }
}
