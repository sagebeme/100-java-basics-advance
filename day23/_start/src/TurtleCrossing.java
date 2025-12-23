import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TurtleCrossing extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Create turtle crossing game
        // - Player turtle
        // - Moving obstacles
        // - Level progression
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("Turtle Crossing Game");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

