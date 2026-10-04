import java.math.BigDecimal;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Labels, a text field, a button, an event handler, and two layouts (VBox inside, HBox for the row).
public class ConverterApp extends Application {

    static final BigDecimal RATE = new BigDecimal("129.50"); // a made-up rate for practice

    @Override
    public void start(Stage stage) {
        Label title = new Label("Shillings to dollars");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #0b3d2e;");
        Label rate = new Label("Practice rate: KES " + RATE + " = USD 1");
        rate.setStyle("-fx-text-fill: #5b7a6e;");

        TextField amount = new TextField();
        amount.setPromptText("Amount in KES, e.g. 1500");
        amount.setStyle("-fx-font-size: 16px; -fx-background-radius: 10; -fx-padding: 10;");
        Button convert = new Button("Convert");
        convert.setDefaultButton(true); // Enter presses it
        convert.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #1b998b; -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 10 18;");
        HBox row = new HBox(10, amount, convert);
        row.setAlignment(Pos.CENTER_LEFT);

        Label result = new Label(" ");
        result.setStyle("-fx-font-size: 18px; -fx-text-fill: #0b3d2e; -fx-font-weight: bold;");

        convert.setOnAction(e -> result.setText(ConverterLogic.kesToUsd(amount.getText(), RATE)));

        VBox root = new VBox(14, title, rate, row, result);
        root.setPadding(new Insets(28));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e9f7ef, #fdf6e3);");
        stage.setTitle("KES → USD");
        stage.setScene(new Scene(root, 440, 240));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
