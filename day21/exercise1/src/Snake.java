import java.util.ArrayList;
import java.util.List;

public class Snake {

    // TODO: the squares, head first, as "col,row" strings; and whether the snake is growing

    public Snake(int col, int row, int length) {
        // TODO: refuse a length below 1; lay the snake flat, head at (col, row), body to the left
    }

    public List<String> body() {
        // TODO: a copy
        return new ArrayList<>();
    }

    public String head() {
        // TODO
        return "";
    }

    public void move(int dx, int dy) {
        // TODO: a new head at the front; the tail drops off unless growing
    }

    public void grow() {
        // TODO
    }

    public List<String> tail(int n) {
        // TODO: the last n squares, or the whole snake if it's shorter
        return new ArrayList<>();
    }

    public void biteAt(int index) {
        // TODO: keep squares 0 to index
    }

    public static void main(String[] args) {
        Snake snake = new Snake(5, 3, 3);
        snake.move(1, 0);
        snake.grow();
        snake.move(0, 1);
        System.out.println(snake.body());
    }
}
