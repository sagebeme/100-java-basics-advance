// The rules behind each kitenge pattern, kept out of JavaFX so they can be tested.
public class Pattern {

    // A checkerboard: is the square at (row, col) dark? (0, 0) is.
    public static boolean isDark(int row, int col) {
        // TODO
        return false;
    }

    // Stripes: the palette's colours in turn, one per row, starting again after the last.
    public static String stripe(int row, String[] palette) {
        // TODO: refuse an empty palette
        return "";
    }

    // A size×size grid numbered 1, 2, 3... clockwise from the top-left, curling inwards.
    public static int[][] spiral(int size) {
        // TODO: refuse a size below 1; then fill the grid edge by edge
        return new int[0][0];
    }
}
