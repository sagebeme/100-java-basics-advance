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
                (i % 7 + 1) * 100,              // seven lanes, from y = 100 to 700: extra cars share them
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
        // Verges and tarmac
        gc.setFill(Color.web("#7cb342"));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.web("#3a3d42"));
        gc.fillRect(0, 100, WIDTH, HEIGHT - 200);
        gc.setFill(Color.web("#f5f5f5"));
        gc.fillRect(0, 96, WIDTH, 4);
        gc.fillRect(0, HEIGHT - 100, WIDTH, 4);
        gc.setStroke(Color.web("#f4d35e"));              // dashed lane markings
        gc.setLineWidth(3);
        gc.setLineDashes(26, 22);
        for (double y = 160; y < HEIGHT - 110; y += 60) gc.strokeLine(0, y, WIDTH, y);
        gc.setLineDashes();

        // Cars in matatu colours, with windows and wheels
        Color[] paint = {Color.web("#ef476f"), Color.web("#ffd166"), Color.web("#06d6a0"), Color.web("#118ab2"), Color.web("#f78c6b")};
        int n = 0;
        for (double[] car : cars) {
            gc.setFill(paint[n++ % paint.length]);
            gc.fillRoundRect(car[0], car[1], 50, 30, 10, 10);
            gc.setFill(Color.web("#cfe8ff", 0.85));
            gc.fillRoundRect(car[0] + 30, car[1] + 5, 14, 20, 4, 4);
            gc.setFill(Color.web("#111111"));
            gc.fillOval(car[0] + 6, car[1] - 3, 10, 6);
            gc.fillOval(car[0] + 34, car[1] - 3, 10, 6);
            gc.fillOval(car[0] + 6, car[1] + 27, 10, 6);
            gc.fillOval(car[0] + 34, car[1] + 27, 10, 6);
        }

        // The turtle crossing: a shell and a head pointing the way it walks
        gc.setFill(Color.web("#6b8f3a"));
        gc.fillOval(playerX + 10, playerY - 8, 10, 12);
        gc.setFill(Color.web("#2d6a4f"));
        gc.fillOval(playerX, playerY, 30, 30);
        gc.setStroke(Color.web("#95d5b2"));
        gc.strokeOval(playerX + 6, playerY + 6, 18, 18);

        // Level badge
        gc.setFill(Color.web("#1b1b1b", 0.7));
        gc.fillRoundRect(10, 12, 110, 32, 16, 16);
        gc.setFill(Color.web("#f4d35e"));
        gc.setFont(javafx.scene.text.Font.font("System", javafx.scene.text.FontWeight.BOLD, 16));
        gc.fillText("Level " + level, 30, 34);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

