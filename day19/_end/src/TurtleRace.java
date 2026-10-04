import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;

public class TurtleRace extends Application {
    private static final int FINISH_LINE = 750;
    private Random random = new Random();
    private double[] turtleX = {50, 50, 50, 50};
    private Color[] colors = {Color.web("#e76f51"), Color.web("#2a9d8f"), Color.web("#e9c46a"), Color.web("#8d6cab")};
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Draw race track
        drawTrack(gc);
        
        // Animate race
        animateRace(gc, stage);
        
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Turtle Race");
        stage.setScene(scene);
        stage.show();
    }
    
    private void drawTrack(GraphicsContext gc) {
        // Grass in mown stripes
        for (int x = 0; x < 800; x += 80) {
            gc.setFill((x / 80) % 2 == 0 ? Color.web("#5a9e4b") : Color.web("#539345"));
            gc.fillRect(x, 0, 80, 600);
        }
        // Lane lines
        gc.setStroke(Color.web("#f6f1e7", 0.6));
        gc.setLineWidth(2);
        gc.setLineDashes(14, 10);
        for (int i = 1; i < 4; i++) gc.strokeLine(0, 55 + i * 120, 800, 55 + i * 120);
        gc.setLineDashes();
        // A checkered finish line
        for (int y = 0; y < 600; y += 12) {
            for (int c = 0; c < 2; c++) {
                gc.setFill(((y / 12) + c) % 2 == 0 ? Color.WHITE : Color.web("#1d1d1d"));
                gc.fillRect(FINISH_LINE + c * 12, y, 12, 12);
            }
        }
        // Turtles: a shell, a head and a number
        for (int i = 0; i < 4; i++) {
            double x = turtleX[i], y = 100 + i * 120;
            gc.setFill(Color.web("#6b8f3a"));
            gc.fillOval(x + 26, y + 8, 14, 14);              // head
            gc.setFill(colors[i]);
            gc.fillOval(x, y, 34, 30);                       // shell
            gc.setStroke(Color.web("#1d1d1d", 0.35));
            gc.strokeOval(x + 7, y + 6, 20, 18);
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font("System", javafx.scene.text.FontWeight.BOLD, 12));
            gc.fillText(String.valueOf(i + 1), x + 13, y + 20);
        }
    }
    
    private void animateRace(GraphicsContext gc, Stage stage) {
        new Thread(() -> {
            while (true) {
                boolean raceOver = false;
                for (int i = 0; i < 4; i++) {
                    if (turtleX[i] < FINISH_LINE) {
                        turtleX[i] += random.nextInt(10);
                        if (turtleX[i] >= FINISH_LINE) {
                            System.out.println("Turtle " + (i + 1) + " wins!");
                            raceOver = true;
                        }
                    }
                }
                
                javafx.application.Platform.runLater(() -> {
                    drawTrack(gc);
                });
                
                if (raceOver) break;
                
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

