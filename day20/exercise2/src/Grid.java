public class Grid {

    // TODO: private fields for the columns, the rows and the cell size in pixels

    public Grid(int cols, int rows, int cellSize) {
        // TODO: refuse 0 or less for any of them
    }

    public int getPixelWidth() {
        // TODO
        return 0;
    }

    public int getPixelHeight() {
        // TODO
        return 0;
    }

    public boolean contains(int col, int row) {
        // TODO
        return false;
    }

    public int[] topLeft(int col, int row) {
        // TODO
        return new int[]{0, 0};
    }

    public int[] centre(int col, int row) {
        // TODO
        return new int[]{0, 0};
    }

    public int[] cellAt(int x, int y) {
        // TODO: Math.floorDiv, then null if it's off the board
        return null;
    }

    public int[] wrap(int col, int row) {
        // TODO: Math.floorMod
        return new int[]{col, row};
    }
}
