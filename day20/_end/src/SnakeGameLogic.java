public class SnakeGameLogic {

    public static final int CELL_SIZE = 20;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    /**
     * Wraps a coordinate that has gone off one edge of the board back onto the opposite edge.
     */
    public static int[] wrap(int x, int y) {
        int wrappedX = x;
        int wrappedY = y;
        if (wrappedX < 0) wrappedX = WIDTH - CELL_SIZE;
        if (wrappedX >= WIDTH) wrappedX = 0;
        if (wrappedY < 0) wrappedY = HEIGHT - CELL_SIZE;
        if (wrappedY >= HEIGHT) wrappedY = 0;
        return new int[]{wrappedX, wrappedY};
    }
}
