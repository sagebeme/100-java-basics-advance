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
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    
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
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, 800, 600);
        
        // Draw finish line
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(5);
        gc.strokeLine(FINISH_LINE, 0, FINISH_LINE, 600);
        
        // Draw lanes
        for (int i = 0; i < 4; i++) {
            gc.setFill(colors[i]);
            gc.fillOval(turtleX[i], 100 + i * 120, 30, 30);
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

