import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PomodoroTimer extends Application {
    private int timeLeft = 25 * 60; // 25 minutes in seconds
    private boolean isRunning = false;
    private Timeline timeline;
    private Label timeLabel;
    
    @Override
    public void start(Stage stage) {
        timeLabel = new Label(formatTime(timeLeft));
        timeLabel.setStyle("-fx-font-size: 48px;");
        
        Button startButton = new Button("Start");
        Button pauseButton = new Button("Pause");
        Button resetButton = new Button("Reset");
        
        startButton.setOnAction(e -> startTimer());
        pauseButton.setOnAction(e -> pauseTimer());
        resetButton.setOnAction(e -> resetTimer());
        
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        VBox root = new VBox(20);
        root.getChildren().addAll(timeLabel, startButton, pauseButton, resetButton);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Pomodoro Timer");
        stage.setScene(scene);
        stage.show();
    }
    
    private void startTimer() {
        if (!isRunning) {
            isRunning = true;
            timeline.play();
        }
    }
    
    private void pauseTimer() {
        isRunning = false;
        timeline.pause();
    }
    
    private void resetTimer() {
        isRunning = false;
        timeline.stop();
        timeLeft = 25 * 60;
        timeLabel.setText(formatTime(timeLeft));
    }
    
    private void updateTimer() {
        if (isRunning && timeLeft > 0) {
            timeLeft--;
            timeLabel.setText(formatTime(timeLeft));
            
            if (timeLeft == 0) {
                pauseTimer();
                timeLabel.setText("Time's up!");
            }
        }
    }
    
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

