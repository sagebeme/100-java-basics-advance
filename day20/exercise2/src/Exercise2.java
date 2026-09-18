public class Exercise2 {

    public static int gridToPixel(int gridPosition, int cellSize) {
        return gridPosition * cellSize;
    }

    public static int pixelToGrid(int pixelPosition, int cellSize) {
        return pixelPosition / cellSize;
    }

    public static boolean isWithinBounds(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public static void main(String[] args) {
        System.out.println(gridToPixel(3, 20));
        System.out.println(pixelToGrid(65, 20));
        System.out.println(isWithinBounds(5, 5, 10, 10));
    }
}
