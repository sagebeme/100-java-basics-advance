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
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Draw food
        gc.setFill(Color.RED);
        gc.fillOval(food[0], food[1], CELL_SIZE, CELL_SIZE);
        
        // Draw snake
        gc.setFill(Color.GREEN);
        for (int[] segment : snake) {
            gc.fillRect(segment[0], segment[1], CELL_SIZE, CELL_SIZE);
        }
        
        // Draw game over
        if (gameOver) {
            gc.setFill(Color.WHITE);
            gc.fillText("Game Over! Score: " + score, WIDTH/2 - 100, HEIGHT/2);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

