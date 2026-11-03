public class Grid {

    private final int cols;
    private final int rows;
    private final int cellSize;

    public Grid(int cols, int rows, int cellSize) {
        if (cols <= 0 || rows <= 0 || cellSize <= 0) {
            throw new IllegalArgumentException("A grid needs at least 1 column, 1 row and 1 pixel per cell");
        }
        this.cols = cols;
        this.rows = rows;
        this.cellSize = cellSize;
    }

    public int getPixelWidth() {
        return cols * cellSize;
    }

    public int getPixelHeight() {
        return rows * cellSize;
    }

    public boolean contains(int col, int row) {
        return col >= 0 && col < cols && row >= 0 && row < rows;
    }

    public int[] topLeft(int col, int row) {
        return new int[]{col * cellSize, row * cellSize};
    }

    public int[] centre(int col, int row) {
        return new int[]{col * cellSize + cellSize / 2, row * cellSize + cellSize / 2};
    }

    public int[] cellAt(int x, int y) {
        int col = Math.floorDiv(x, cellSize);
        int row = Math.floorDiv(y, cellSize);
        return contains(col, row) ? new int[]{col, row} : null;
    }

    public int[] wrap(int col, int row) {
        return new int[]{Math.floorMod(col, cols), Math.floorMod(row, rows)};
    }
}
