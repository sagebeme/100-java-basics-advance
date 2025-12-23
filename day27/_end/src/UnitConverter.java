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
        Label inputLabel = new Label("Miles:");
        TextField inputField = new TextField();
        Button convertButton = new Button("Convert");
        Label outputLabel = new Label("Kilometers: 0.0");
        
        convertButton.setOnAction(e -> {
            try {
                double miles = Double.parseDouble(inputField.getText());
                double kilometers = miles * 1.609;
                outputLabel.setText("Kilometers: " + String.format("%.2f", kilometers));
            } catch (NumberFormatException ex) {
                outputLabel.setText("Invalid input!");
            }
        });
        
        VBox root = new VBox(10);
        root.getChildren().addAll(inputLabel, inputField, convertButton, outputLabel);
        root.setStyle("-fx-padding: 20;");
        
        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Mile to Kilometer Converter");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

