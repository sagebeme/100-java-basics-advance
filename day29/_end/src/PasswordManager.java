import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;

public class PasswordManager extends Application {
    private TextField websiteField = new TextField();
    private TextField usernameField = new TextField();
    private TextField passwordField = new TextField();
    private ListView<String> passwordList = new ListView<>();
    private List<PasswordEntry> entries = new ArrayList<>();
    private static final String FILE_NAME = "passwords.txt";
    
    @Override
    public void start(Stage stage) {
        Label websiteLabel = new Label("Website:");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        
        Button generateButton = new Button("Generate Password");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");
        
        generateButton.setOnAction(e -> generatePassword());
        saveButton.setOnAction(e -> savePassword());
        loadButton.setOnAction(e -> loadPasswords());
        
        VBox inputBox = new VBox(10);
        inputBox.getChildren().addAll(
            websiteLabel, websiteField,
            usernameLabel, usernameField,
            passwordLabel, passwordField,
            generateButton, saveButton, loadButton
        );
        inputBox.setStyle("-fx-padding: 10;");
        
        VBox root = new VBox(10);
        root.getChildren().addAll(inputBox, passwordList);
        
        Scene scene = new Scene(root, 600, 400);
        
        java.net.URL css = getClass().getResource("/vault.css"); // the look lives in vault.css, next to this file
        
        if (css != null) scene.getStylesheets().add(css.toExternalForm());
        stage.setTitle("Password Manager");
        stage.setScene(scene);
        stage.show();
        
        loadPasswords();
    }
    
    private void generatePassword() {
        // SecureRandom, not Random: Random's next numbers can be worked out from earlier ones,
        // which is fine for a game and never for a password.
        passwordField.setText(PasswordManagerLogic.generatePassword(16, new SecureRandom()));
    }
    
    private void savePassword() {
        String website = websiteField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (!website.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
            entries.add(new PasswordEntry(website, username, password));
            updateList();
            saveToFile();
            clearFields();
        }
    }
    
    private void loadPasswords() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // first run: nothing saved yet
        
        // try-with-resources closes the reader even when reading fails half way
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            entries.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = PasswordManagerLogic.parseEntry(line);
                if (parts != null) {
                    entries.add(new PasswordEntry(parts[0], parts[1], parts[2]));
                }
            }
            updateList();
        } catch (IOException e) {
            showError("Could not load " + FILE_NAME + ": " + e.getMessage());
        }
    }
    
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (PasswordEntry entry : entries) {
                writer.println(PasswordManagerLogic.formatEntry(entry.website, entry.username, entry.password));
            }
            // PrintWriter never throws while writing; it only remembers that something went wrong
            if (writer.checkError()) throw new IOException("the disk refused the write");
        } catch (IOException e) {
            showError("Could not save " + FILE_NAME + ": " + e.getMessage());
        }
    }
    
    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
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

