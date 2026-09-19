package com.portfolio;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.Clock;

public class DisappearingTextApp extends Application {

    private static final java.time.Duration IDLE_TIMEOUT = java.time.Duration.ofSeconds(8);

    private final DisappearingTextEditor editor = new DisappearingTextEditor(Clock.systemUTC(), IDLE_TIMEOUT);

    @Override
    public void start(Stage stage) {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPromptText("Start typing. If you stop for too long, it disappears...");

        Label countdownLabel = new Label();
        Label savedCountLabel = new Label("Saved: 0");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                editor.save();
                savedCountLabel.setText("Saved: " + editor.getSavedEntries().size());
            } catch (IllegalStateException ex) {
                countdownLabel.setText("Nothing to save");
            }
        });

        textArea.textProperty().addListener((obs, oldText, newText) -> editor.setContent(newText));

        HBox statusBar = new HBox(20, countdownLabel, savedCountLabel, saveButton);
        statusBar.setAlignment(Pos.CENTER_LEFT);
        statusBar.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(textArea);
        root.setBottom(statusBar);

        stage.setScene(new Scene(root, 500, 400));
        stage.setTitle("Disappearing Text");
        stage.show();

        Timeline ticker = new Timeline(new KeyFrame(Duration.millis(200), e -> {
            boolean disappeared = editor.clearIfIdle();
            if (disappeared) {
                textArea.setText("");
            }
            countdownLabel.setText(String.format("Disappears in: %.1fs", editor.timeUntilDisappears().toMillis() / 1000.0));
        }));
        ticker.setCycleCount(Timeline.INDEFINITE);
        ticker.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
