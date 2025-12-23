import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PomodoroTimer extends Application {
    @Override
    public void start(Stage stage) {
        // TODO: Create Pomodoro timer
        // - 25 minute countdown
        // - Start/Pause/Reset buttons
        // - Display time remaining
        // - Use Timeline for updates
        
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Pomodoro Timer");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

