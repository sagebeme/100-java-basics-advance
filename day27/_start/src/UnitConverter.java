import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UnitConverter extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Create GUI for unit converter
        // - TextField for input
        // - Label for output
        // - Button to convert
        // - Convert miles to kilometers (1 mile = 1.609 km)
        
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Mile to Kilometer Converter");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

