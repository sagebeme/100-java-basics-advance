import java.util.LinkedHashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// The sign-up window for the boda boda riders' insurance scheme. Every rule lives in SignupForm;
// this class only lays out the fields and shows what validate() says next to each one.
public class SignupApp extends Application {

    @Override
    public void start(Stage stage) {
        Map<String, TextField> fields = new LinkedHashMap<>();
        Map<String, Label> errors = new LinkedHashMap<>();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(4);

        String[][] rows = {
                {"name", "Full name"},
                {"phone", "Phone"},
                {"idNumber", "National ID number"},
                {"age", "Age"}};
        for (int i = 0; i < rows.length; i++) {
            TextField field = new TextField();
            Label error = new Label();
            error.setTextFill(Color.web("#b03a2e"));
            grid.add(new Label(rows[i][1]), 0, i * 2);
            grid.add(field, 1, i * 2);
            grid.add(error, 1, i * 2 + 1);
            fields.put(rows[i][0], field);
            errors.put(rows[i][0], error);
        }

        Label result = new Label();
        Button signUp = new Button("Sign up");
        signUp.setDefaultButton(true);
        signUp.setOnAction(e -> {
            Map<String, String> problems = SignupForm.validate(
                    fields.get("name").getText(), fields.get("phone").getText(),
                    fields.get("idNumber").getText(), fields.get("age").getText());
            for (String key : errors.keySet()) {
                errors.get(key).setText(problems.getOrDefault(key, ""));
            }
            result.setText(problems.isEmpty()
                    ? "Karibu! We'll send the policy to " + SignupForm.normalisePhone(fields.get("phone").getText()) + "."
                    : "");
        });

        VBox page = new VBox(12, new Label("Boda boda insurance sign-up"), grid, signUp, result);
        page.setPadding(new Insets(20));
        stage.setTitle("Sign up");
        stage.setScene(new Scene(page, 520, 380));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
