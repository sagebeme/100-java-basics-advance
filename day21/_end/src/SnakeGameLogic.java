import java.util.ArrayList;

public class SnakeGameLogic {

    public static final int CELL_SIZE = 20;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    public static boolean hitsWall(int x, int y) {
        return x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT;
    }

    public static boolean hitsSelf(int x, int y, ArrayList<int[]> snake) {
        for (int[] segment : snake) {
            if (segment[0] == x && segment[1] == y) {
                return true;
            }
        }
        return false;
    }

    public static boolean eatsFood(int x, int y, int[] food) {
        return x == food[0] && y == food[1];
    }

    public static int[] nextHead(int[] head, int directionX, int directionY) {
        return new int[]{head[0] + directionX * CELL_SIZE, head[1] + directionY * CELL_SIZE};
    }
}
