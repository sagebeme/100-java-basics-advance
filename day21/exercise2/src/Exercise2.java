import java.util.List;

public class Exercise2 {

    public static boolean hitsWall(int x, int y, int width, int height) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    public static boolean hitsObject(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    public static boolean hitsSelf(int[] head, List<int[]> body) {
        for (int[] segment : body) {
            if (hitsObject(head[0], head[1], segment[0], segment[1])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hitsWall(-1, 5, 10, 10));
        System.out.println(hitsObject(3, 3, 3, 3));
    }
}
