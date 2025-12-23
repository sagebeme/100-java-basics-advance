import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.Random;

public class HirstPainting extends Application {
    private static final int DOT_SIZE = 20;
    private static final int SPACING = 50;
    private static final Color[] COLORS = {
        Color.rgb(0, 0, 0),      // Black
        Color.rgb(255, 0, 0),    // Red
        Color.rgb(0, 255, 0),    // Green
        Color.rgb(0, 0, 255),    // Blue
        Color.rgb(255, 255, 0),  // Yellow
        Color.rgb(255, 0, 255),  // Magenta
        Color.rgb(0, 255, 255)   // Cyan
    };
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Random random = new Random();
        
        // Draw grid of colored circles
        for (int x = SPACING; x < 800 - SPACING; x += SPACING) {
            for (int y = SPACING; y < 800 - SPACING; y += SPACING) {
                Color color = COLORS[random.nextInt(COLORS.length)];
                gc.setFill(color);
                gc.fillOval(x - DOT_SIZE/2, y - DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
            }
        }
        
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Hirst Painting Generator");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

