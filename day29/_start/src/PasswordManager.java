import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PasswordManager extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Create password manager GUI
        // - Fields: Website, Username, Password
        // - Generate password button
        // - Save button
        // - List of saved passwords
        // - File I/O to save/load passwords
        
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Password Manager");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

