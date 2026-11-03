import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatternTest {

    @Test
    void theCheckerboardStartsDarkAndAlternates() {
        assertTrue(Pattern.isDark(0, 0));
        assertFalse(Pattern.isDark(0, 1));
        assertFalse(Pattern.isDark(1, 0));
        assertTrue(Pattern.isDark(1, 1));
        assertTrue(Pattern.isDark(7, 3));
        assertFalse(Pattern.isDark(7, 4));
    }

    @Test
    void stripesRepeatThePalette() {
        String[] palette = {"#c0392b", "#f1c40f", "#27ae60"};
        assertEquals("#c0392b", Pattern.stripe(0, palette));
        assertEquals("#f1c40f", Pattern.stripe(1, palette));
        assertEquals("#27ae60", Pattern.stripe(2, palette));
        assertEquals("#c0392b", Pattern.stripe(3, palette));
        assertEquals("#27ae60", Pattern.stripe(11, palette));
    }

    @Test
    void stripesNeedAtLeastOneColour() {
        assertThrows(IllegalArgumentException.class, () -> Pattern.stripe(0, new String[0]));
    }

    @Test
    void aOneSquareSpiral() {
        assertArrayEquals(new int[][]{{1}}, Pattern.spiral(1));
    }

    @Test
    void anOddSpiralEndsInTheMiddle() {
        assertArrayEquals(new int[][]{
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}}, Pattern.spiral(3));
    }

    @Test
    void anEvenSpiralCurlsInwards() {
        assertArrayEquals(new int[][]{
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}}, Pattern.spiral(4));
    }

    @Test
    void aBigSpiralUsesEveryNumberOnce() {
        int[][] grid = Pattern.spiral(9);
        boolean[] seen = new boolean[82];
        for (int[] row : grid) {
            for (int n : row) {
                assertFalse(seen[n], n + " appears twice");
                seen[n] = true;
            }
        }
        assertEquals(81, grid[4][4]);
    }

    @Test
    void refusesASpiralOfNothing() {
        assertThrows(IllegalArgumentException.class, () -> Pattern.spiral(0));
    }
}
