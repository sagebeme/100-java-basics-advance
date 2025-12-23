import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SnakeGame extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Create Snake game foundation
        // - Game board/canvas
        // - Snake representation
        // - Basic movement
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Snake Game Part 1");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

