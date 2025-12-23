import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class TurtleCrossing extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;
    private double playerX = WIDTH / 2;
    private double playerY = HEIGHT - 50;
    private ArrayList<double[]> cars;
    private int level = 1;
    private Random random = new Random();
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        cars = new ArrayList<>();
        spawnCars();
        
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP: if (playerY > 0) playerY -= 20; break;
                case DOWN: if (playerY < HEIGHT - 50) playerY += 20; break;
                case LEFT: if (playerX > 0) playerX -= 20; break;
                case RIGHT: if (playerX < WIDTH - 50) playerX += 20; break;
            }
        });
        
        new Thread(() -> {
            while (true) {
                update();
                javafx.application.Platform.runLater(() -> draw(gc));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Turtle Crossing - Level: " + level);
        stage.setScene(scene);
        stage.show();
    }
    
    private void spawnCars() {
        cars.clear();
        for (int i = 0; i < 5 + level; i++) {
            cars.add(new double[]{
                random.nextDouble() * WIDTH,
                (i + 1) * 100,
                random.nextDouble() * 3 + 1
            });
        }
    }
    
    private void update() {
        // Move cars
        for (double[] car : cars) {
            car[0] += car[2];
            if (car[0] > WIDTH) car[0] = -50;
            if (car[0] < -50) car[0] = WIDTH;
        }
        
        // Check collision
        for (double[] car : cars) {
            if (playerX < car[0] + 50 && playerX + 50 > car[0] &&
                playerY < car[1] + 30 && playerY + 30 > car[1]) {
                resetGame();
                return;
            }
        }
        
        // Level up
        if (playerY < 50) {
            level++;
            playerY = HEIGHT - 50;
            spawnCars();
        }
    }
    
    private void resetGame() {
        playerX = WIDTH / 2;
        playerY = HEIGHT - 50;
        level = 1;
        spawnCars();
    }
    
    private void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Draw road
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 100, WIDTH, HEIGHT - 200);
        
        // Draw cars
        gc.setFill(Color.RED);
        for (double[] car : cars) {
            gc.fillRect(car[0], car[1], 50, 30);
        }
        
        // Draw player
        gc.setFill(Color.BLUE);
        gc.fillOval(playerX, playerY, 30, 30);
        
        // Draw level
        gc.setFill(Color.WHITE);
        gc.fillText("Level: " + level, 10, 30);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

