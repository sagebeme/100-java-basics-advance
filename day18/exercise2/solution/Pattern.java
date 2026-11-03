// The rules behind each kitenge pattern, kept out of JavaFX so they can be tested.
public class Pattern {

    // A checkerboard: is the square at (row, col) dark? (0, 0) is.
    public static boolean isDark(int row, int col) {
        return (row + col) % 2 == 0;
    }

    // Stripes: the palette's colours in turn, one per row, starting again after the last.
    public static String stripe(int row, String[] palette) {
        if (palette.length == 0) {
            throw new IllegalArgumentException("A palette needs at least one colour");
        }
        return palette[row % palette.length];
    }

    // A size×size grid numbered 1, 2, 3... clockwise from the top-left, curling inwards.
    public static int[][] spiral(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("A spiral needs a size of at least 1, not " + size);
        }
        int[][] grid = new int[size][size];
        int top = 0, bottom = size - 1, left = 0, right = size - 1;
        int n = 1;
        while (top <= bottom && left <= right) {
            for (int col = left; col <= right; col++) grid[top][col] = n++;
            top++;
            for (int row = top; row <= bottom; row++) grid[row][right] = n++;
            right--;
            if (top <= bottom) {
                for (int col = right; col >= left; col--) grid[bottom][col] = n++;
                bottom--;
            }
            if (left <= right) {
                for (int row = bottom; row >= top; row--) grid[row][left] = n++;
                left++;
            }
        }
        return grid;
    }
}
