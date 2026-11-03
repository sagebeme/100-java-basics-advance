import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImprovedPasswordManager extends Application {
    private TextField websiteField = new TextField();
    private TextField usernameField = new TextField();
    private TextField passwordField = new TextField();
    private TextField searchField = new TextField();
    private ListView<String> passwordList = new ListView<>();
    private Label statusLabel = new Label();
    private List<PasswordEntry> entries = new ArrayList<>();
    private static final String FILE_NAME = "passwords.json";
    private boolean fileIsDamaged = false;
    
    @Override
    public void start(Stage stage) {
        Label websiteLabel = new Label("Website:");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label searchLabel = new Label("Search:");
        
        Button generateButton = new Button("Generate Password");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");
        Button searchButton = new Button("Search");
        
        generateButton.setOnAction(e -> generatePassword());
        saveButton.setOnAction(e -> savePassword());
        loadButton.setOnAction(e -> loadPasswords());
        searchButton.setOnAction(e -> searchPasswords());
        
        VBox inputBox = new VBox(10);
        inputBox.getChildren().addAll(
            websiteLabel, websiteField,
            usernameLabel, usernameField,
            passwordLabel, passwordField,
            generateButton, saveButton, loadButton,
            searchLabel, searchField, searchButton,
            statusLabel
        );
        inputBox.setStyle("-fx-padding: 10;");
        
        VBox root = new VBox(10);
        root.getChildren().addAll(inputBox, passwordList);
        
        Scene scene = new Scene(root, 600, 500);
        
        java.net.URL css = getClass().getResource("/safe.css"); // the look lives in safe.css, next to this file
        
        if (css != null) scene.getStylesheets().add(css.toExternalForm());
        stage.setTitle("Improved Password Manager");
        stage.setScene(scene);
        stage.show();
        
        loadPasswords();
    }
    
    private void generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";
        // SecureRandom, not Random: Random's next numbers can be worked out from earlier ones.
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < 16; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        passwordField.setText(password.toString());
        statusLabel.setText("Password generated");
    }
    
    private void savePassword() {
        String website = websiteField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (website.isEmpty() || username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Error: All fields required!");
            return;
        }

        entries.add(new PasswordEntry(website, username, password));
        updateList();
        if (saveToFile()) {
            clearFields();
            statusLabel.setText("Password saved successfully!");
        }
    }
    
    // Reads passwords.json: a JSON array of {"website", "username", "password"} objects.
    // Each thing that can go wrong gets its own message, instead of one "something failed".
    private void loadPasswords() {
        try {
            JSONArray saved = new JSONArray(Files.readString(Path.of(FILE_NAME)));
            entries.clear();
            for (int i = 0; i < saved.length(); i++) {
                JSONObject login = saved.getJSONObject(i);
                entries.add(new PasswordEntry(login.getString("website"), login.getString("username"),
                        login.getString("password")));
            }
            updateList();
            statusLabel.setText("Loaded " + entries.size() + " passwords");
        } catch (NoSuchFileException e) {
            statusLabel.setText("No saved passwords yet");
        } catch (JSONException e) {
            // Don't overwrite a damaged file: the user may be able to repair it by hand.
            statusLabel.setText(FILE_NAME + " is damaged (" + e.getMessage() + "). Fix or move it, then restart.");
            fileIsDamaged = true;
        } catch (IOException e) {
            statusLabel.setText("Couldn't read " + FILE_NAME + ": " + e.getMessage());
        }
    }
    
    private void searchPasswords() {
        String searchTerm = searchField.getText().trim();
        passwordList.getItems().clear();
        
        for (PasswordEntry entry : entries) {
            if (ImprovedPasswordManagerLogic.matches(entry.website, entry.username, searchTerm)) {
                passwordList.getItems().add(entry.website + " - " + entry.username);
            }
        }
        
        statusLabel.setText("Found " + passwordList.getItems().size() + " matches");
    }
    
    // True if the file was written. On failure the status label says why.
    private boolean saveToFile() {
        if (fileIsDamaged) {
            statusLabel.setText("Not saved: " + FILE_NAME + " is damaged, and saving would replace it.");
            return false;
        }
        JSONArray all = new JSONArray();
        for (PasswordEntry entry : entries) {
            all.put(new JSONObject()
                    .put("website", entry.website)
                    .put("username", entry.username)
                    .put("password", entry.password));
        }
        try {
            Files.writeString(Path.of(FILE_NAME), all.toString(2));
            return true;
        } catch (IOException e) {
            statusLabel.setText("Error saving file: " + e.getMessage());
            return false;
        }
    }
    
    private void updateList() {
        passwordList.getItems().clear();
        for (PasswordEntry entry : entries) {
            passwordList.getItems().add(entry.website + " - " + entry.username);
        }
    }
    
    private void clearFields() {
        websiteField.clear();
        usernameField.clear();
        passwordField.clear();
    }
    
    private static class PasswordEntry {
        String website;
        String username;
        String password;
        
        PasswordEntry(String website, String username, String password) {
            this.website = website;
            this.username = username;
            this.password = password;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

