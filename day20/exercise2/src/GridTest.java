import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GridTest {

    private final Grid grid = new Grid(20, 15, 24);

    @Test
    void knowsItsSizeInPixels() {
        assertEquals(480, grid.getPixelWidth());
        assertEquals(360, grid.getPixelHeight());
    }

    @Test
    void knowsWhichCellsAreOnTheBoard() {
        assertTrue(grid.contains(0, 0));
        assertTrue(grid.contains(19, 14));
        assertFalse(grid.contains(20, 0));
        assertFalse(grid.contains(0, 15));
        assertFalse(grid.contains(-1, 3));
    }

    @Test
    void findsWhereToDrawACell() {
        assertArrayEquals(new int[]{72, 48}, grid.topLeft(3, 2));
        assertArrayEquals(new int[]{84, 60}, grid.centre(3, 2));
    }

    @Test
    void findsTheCellUnderAPixel() {
        assertArrayEquals(new int[]{3, 2}, grid.cellAt(85, 49));
        assertArrayEquals(new int[]{3, 2}, grid.cellAt(72, 48));
        assertArrayEquals(new int[]{2, 1}, grid.cellAt(71, 47));
        assertArrayEquals(new int[]{19, 14}, grid.cellAt(479, 359));
    }

    @Test
    void aPixelOffTheBoardHasNoCell() {
        assertNull(grid.cellAt(480, 10));
        assertNull(grid.cellAt(10, 360));
        assertNull(grid.cellAt(-5, 10), "-5 / 24 is 0 in Java: use Math.floorDiv");
        assertNull(grid.cellAt(10, -1));
    }

    @Test
    void wrapsAroundEveryEdge() {
        assertArrayEquals(new int[]{0, 5}, grid.wrap(20, 5));
        assertArrayEquals(new int[]{19, 5}, grid.wrap(-1, 5), "-1 % 20 is -1 in Java: use Math.floorMod");
        assertArrayEquals(new int[]{3, 14}, grid.wrap(3, -1));
        assertArrayEquals(new int[]{3, 0}, grid.wrap(3, 15));
        assertArrayEquals(new int[]{7, 8}, grid.wrap(7, 8));
    }

    @Test
    void refusesAnEmptyGrid() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(0, 15, 24));
        assertThrows(IllegalArgumentException.class, () -> new Grid(20, -1, 24));
        assertThrows(IllegalArgumentException.class, () -> new Grid(20, 15, 0));
    }
}
