import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class CompleteSnakeGame extends Application {
    private static final int CELL_SIZE = 20;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private ArrayList<int[]> snake;
    private int[] food;
    private int directionX = 1;
    private int directionY = 0;
    private int score = 0;
    private boolean gameOver = false;
    private Random random = new Random();
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Initialize snake and food
        snake = new ArrayList<>();
        snake.add(new int[]{WIDTH/2, HEIGHT/2});
        spawnFood();
        
        // Handle keyboard input
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            if (gameOver) return;
            switch (e.getCode()) {
                case UP: if (directionY == 0) { directionX = 0; directionY = -1; } break;
                case DOWN: if (directionY == 0) { directionX = 0; directionY = 1; } break;
                case LEFT: if (directionX == 0) { directionX = -1; directionY = 0; } break;
                case RIGHT: if (directionX == 0) { directionX = 1; directionY = 0; } break;
            }
        });
        
        // Game loop
        new Thread(() -> {
            while (true) {
                if (!gameOver) {
                    update();
                }
                javafx.application.Platform.runLater(() -> draw(gc));
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Complete Snake Game - Score: " + score);
        stage.setScene(scene);
        stage.show();
    }
    
    private void spawnFood() {
        food = new int[]{
            (random.nextInt(WIDTH / CELL_SIZE)) * CELL_SIZE,
            (random.nextInt(HEIGHT / CELL_SIZE)) * CELL_SIZE
        };
    }
    
    private void update() {
        int[] head = snake.get(0);
        int newX = head[0] + directionX * CELL_SIZE;
        int newY = head[1] + directionY * CELL_SIZE;
        
        // Check wall collision
        if (newX < 0 || newX >= WIDTH || newY < 0 || newY >= HEIGHT) {
            gameOver = true;
            return;
        }
        
        // Check self collision
        for (int[] segment : snake) {
            if (segment[0] == newX && segment[1] == newY) {
                gameOver = true;
                return;
            }
        }
        
        snake.add(0, new int[]{newX, newY});
        
        // Check food collision
        if (newX == food[0] && newY == food[1]) {
            score++;
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }
    
    private void draw(GraphicsContext gc) {
        // A board of soft checkered tiles
        for (int x = 0; x < WIDTH; x += CELL_SIZE) {
            for (int y = 0; y < HEIGHT; y += CELL_SIZE) {
                gc.setFill(((x + y) / CELL_SIZE) % 2 == 0 ? Color.web("#1b2a1f") : Color.web("#1f3124"));
                gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }

        // Food: a mango, with a leaf
        gc.setFill(Color.web("#ffb703"));
        gc.fillOval(food[0] + 1, food[1] + 2, CELL_SIZE - 2, CELL_SIZE - 3);
        gc.setFill(Color.web("#52b788"));
        gc.fillOval(food[0] + CELL_SIZE * 0.55, food[1] - 2, CELL_SIZE * 0.4, CELL_SIZE * 0.3);

        for (int s = 0; s < snake.size(); s++) {
            int[] segment = snake.get(s);
            gc.setFill(s == 0 ? Color.web("#a7e05f") : Color.web("#6cc04a").interpolate(Color.web("#2e7d32"), (double) s / Math.max(1, snake.size())));
            gc.fillRoundRect(segment[0] + 1, segment[1] + 1, CELL_SIZE - 2, CELL_SIZE - 2, 8, 8);
            if (s == 0) {                     // eyes on the head
                gc.setFill(Color.web("#10170f"));
                gc.fillOval(segment[0] + CELL_SIZE * 0.25, segment[1] + CELL_SIZE * 0.25, CELL_SIZE * 0.18, CELL_SIZE * 0.18);
                gc.fillOval(segment[0] + CELL_SIZE * 0.6, segment[1] + CELL_SIZE * 0.25, CELL_SIZE * 0.18, CELL_SIZE * 0.18);
            }
        }
        
        // Draw game over
        if (gameOver) {
            gc.setFill(Color.web("#000000", 0.6));
            gc.fillRect(0, 0, WIDTH, HEIGHT);
            gc.setFill(Color.web("#ffb703"));
            gc.setFont(javafx.scene.text.Font.font("System", javafx.scene.text.FontWeight.BOLD, 28));
            gc.fillText("Game Over! Score: " + score, WIDTH/2 - 150, HEIGHT/2);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

