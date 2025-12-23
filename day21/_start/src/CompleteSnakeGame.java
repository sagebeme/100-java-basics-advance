import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CompleteSnakeGame extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Complete Snake game
        // - Add food
        // - Score tracking
        // - Collision detection
        // - Game over screen
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Complete Snake Game");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

