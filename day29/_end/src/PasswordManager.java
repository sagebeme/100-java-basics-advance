import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        stage.setTitle("Password Manager");
        stage.setScene(scene);
        stage.show();
        
        loadPasswords();
    }
    
    private void generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < 16; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        passwordField.setText(password.toString());
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
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                entries.clear();
                
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 3) {
                        entries.add(new PasswordEntry(parts[0], parts[1], parts[2]));
                    }
                }
                reader.close();
                updateList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));
            for (PasswordEntry entry : entries) {
                writer.println(entry.website + "|" + entry.username + "|" + entry.password);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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

