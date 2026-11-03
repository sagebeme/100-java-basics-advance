import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final List<String> squares = new ArrayList<>();
    private boolean growing;

    public Snake(int col, int row, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("A snake needs at least 1 square, not " + length);
        }
        for (int i = 0; i < length; i++) {
            squares.add((col - i) + "," + row);
        }
    }

    public List<String> body() {
        return new ArrayList<>(squares);
    }

    public String head() {
        return squares.get(0);
    }

    public void move(int dx, int dy) {
        String[] parts = head().split(",");
        int col = Integer.parseInt(parts[0]) + dx;
        int row = Integer.parseInt(parts[1]) + dy;
        squares.add(0, col + "," + row);
        if (growing) {
            growing = false;
        } else {
            squares.remove(squares.size() - 1);
        }
    }

    public void grow() {
        growing = true;
    }

    public List<String> tail(int n) {
        int from = Math.max(0, squares.size() - n);
        return new ArrayList<>(squares.subList(from, squares.size()));
    }

    public void biteAt(int index) {
        squares.subList(index + 1, squares.size()).clear();
    }

    public static void main(String[] args) {
        Snake snake = new Snake(5, 3, 3);
        snake.move(1, 0);
        snake.grow();
        snake.move(0, 1);
        System.out.println(snake.body());
    }
}
