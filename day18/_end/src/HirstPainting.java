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
        // Soft, chalky colours like Damien Hirst's real spot paintings, not pure screen primaries
        Color.web("#e8b04b"), Color.web("#d8584a"), Color.web("#4a8fbf"), Color.web("#7bb46a"),
        Color.web("#c0729c"), Color.web("#f2d45c"), Color.web("#3c6e71"), Color.web("#e98a5b"),
        Color.web("#8d6cab"), Color.web("#9fc8d6"), Color.web("#2d3047"), Color.web("#b5c96a")
    };
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Random random = new Random();
        gc.setFill(Color.web("#f6f1e7"));   // a primed canvas, not screen white
        gc.fillRect(0, 0, 800, 800);
        
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

