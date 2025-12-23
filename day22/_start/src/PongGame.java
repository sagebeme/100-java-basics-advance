import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PongGame extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Create Pong game
        // - Paddles
        // - Ball physics
        // - Scoring
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Pong Game");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

