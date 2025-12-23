import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TurtleRace extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Create turtle race game
        // - Multiple turtles racing
        // - Random movement
        // - Winner determination
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Turtle Race");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

