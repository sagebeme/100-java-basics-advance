import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlashCardApp extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Create Flash Card application
        // - Display question/answer cards
        // - Flip card functionality
        // - Mark as known/unknown
        // - Save/load from JSON file
        // - Add/remove cards
        
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Flash Card App");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

