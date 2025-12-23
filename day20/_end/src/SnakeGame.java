import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class SnakeGame extends Application {
    private static final int CELL_SIZE = 20;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private ArrayList<int[]> snake;
    private int directionX = 1;
    private int directionY = 0;
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Initialize snake
        snake = new ArrayList<>();
        snake.add(new int[]{WIDTH/2, HEIGHT/2});
        
        // Handle keyboard input
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP: directionX = 0; directionY = -1; break;
                case DOWN: directionX = 0; directionY = 1; break;
                case LEFT: directionX = -1; directionY = 0; break;
                case RIGHT: directionX = 1; directionY = 0; break;
            }
        });
        
        // Game loop
        new Thread(() -> {
            while (true) {
                update();
                javafx.application.Platform.runLater(() -> draw(gc));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Snake Game Part 1");
        stage.setScene(scene);
        stage.show();
    }
    
    private void update() {
        int[] head = snake.get(0);
        int newX = head[0] + directionX * CELL_SIZE;
        int newY = head[1] + directionY * CELL_SIZE;
        
        // Wrap around
        if (newX < 0) newX = WIDTH - CELL_SIZE;
        if (newX >= WIDTH) newX = 0;
        if (newY < 0) newY = HEIGHT - CELL_SIZE;
        if (newY >= HEIGHT) newY = 0;
        
        snake.add(0, new int[]{newX, newY});
        if (snake.size() > 3) {
            snake.remove(snake.size() - 1);
        }
    }
    
    private void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        
        gc.setFill(Color.GREEN);
        for (int[] segment : snake) {
            gc.fillRect(segment[0], segment[1], CELL_SIZE, CELL_SIZE);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

