# Day 29 - Building Password Manager GUI App with JavaFX

## 📚 Learning Objectives
- Build a complete password manager
- Work with file I/O for data persistence
- Implement password generation
- Create secure storage
- Build user-friendly GUI

## 🎯 Topics Covered
- Password generation
- File I/O for passwords
- GUI form design
- Data encryption basics
- Search functionality
- Copy to clipboard

## 📝 Step-by-Step Instructions

### Step 1: Password Generation
Generate secure passwords:

```java
import java.util.Random;

public class PasswordGenerator {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    
    public static String generatePassword(int length, boolean includeNumbers, boolean includeSymbols) {
        String chars = LETTERS;
        if (includeNumbers) chars += NUMBERS;
        if (includeSymbols) chars += SYMBOLS;
        
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return password.toString();
    }
}
```

### Step 2: Data Storage
Store passwords in file:

```java
import java.io.FileWriter;
import java.io.IOException;

public void savePassword(String website, String email, String password) {
    try {
        FileWriter writer = new FileWriter("passwords.txt", true);
        writer.write(website + " | " + email + " | " + password + "\n");
        writer.close();
    } catch (IOException e) {
        System.out.println("Error saving password!");
    }
}
```

### Step 3: GUI Design
Create password manager interface:

```java
// Form fields
TextField websiteField = new TextField();
TextField emailField = new TextField();
TextField passwordField = new TextField();
Button generateButton = new Button("Generate Password");
Button addButton = new Button("Add");
Button searchButton = new Button("Search");

// Layout
VBox form = new VBox(10);
form.getChildren().addAll(
    new Label("Website:"), websiteField,
    new Label("Email/Username:"), emailField,
    new Label("Password:"), passwordField,
    generateButton, addButton, searchButton
);
```

### Step 4: Search Functionality
Search saved passwords:

```java
public String searchPassword(String website) {
    try {
        File file = new File("passwords.txt");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith(website + " |")) {
                return line.split(" \\| ")[2];
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        return "No passwords found!";
    }
    return "Website not found!";
}
```

## 💻 Exercises

### Exercise 1: Password Generation
Create password generator with:
- Configurable length
- Character set options
- Strength indicator
- Multiple generation methods

### Exercise 2: File Operations
Create systems for:
- Saving passwords
- Loading passwords
- Searching passwords
- Deleting passwords

### Exercise 3: GUI Features
Add features:
- Password visibility toggle
- Copy to clipboard
- Password strength meter
- Search functionality

## 🎮 Project: Password Manager

### Requirements
Create a password manager application:
1. GUI with form fields (website, email, password)
2. Generate password button
3. Add password button
4. Search functionality
5. Save to file
6. Load from file
7. Copy password to clipboard

### Example Output
```
🔒 Password Manager

Website: [_____________]
Email/Username: [_____________]
Password: [_____________] [👁️]

[Generate Password] [Add] [Search]

Saved Passwords:
- google.com | user@gmail.com | ********
- github.com | username | ********
```

### Starter Code
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PasswordManager extends Application {
    @Override
    public void start(Stage stage) {
        // Create form
        // Add event handlers
        // Set up file operations
    }
    
    private void generatePassword() {
        // Generate and set password
    }
    
    private void savePassword() {
        // Save to file
    }
    
    private void searchPassword() {
        // Search and display
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

## 📚 Resources

### Official Documentation
- [JavaFX Controls - JavaFX Docs](https://openjfx.io/javadoc/11/javafx.controls/)
- [File I/O - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/io/)

### Tutorials
- [Password Manager Tutorial](https://www.geeksforgeeks.org/password-manager-using-java/)
- [JavaFX Forms](https://code.makery.ch/library/javafx-tutorial/part2/)

### Video Resources
- [Password Manager Tutorial](https://www.youtube.com/results?search_query=java+password+manager+tutorial)
- [JavaFX Form Application](https://www.youtube.com/results?search_query=javafx+form+tutorial)

### Practice Platforms
- [Security Projects](https://github.com/karan/Projects#security)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Password Generation | Create random passwords | Random character selection |
| File Storage | Persist data | Save to text file |
| GUI Forms | User input interface | TextField, Button |
| Search | Find data | File scanning |
| Clipboard | Copy to system | `Clipboard.setContent()` |

## ✅ Checklist
- [ ] Can generate passwords
- [ ] Can save to files
- [ ] Can search passwords
- [ ] Can create GUI forms
- [ ] Can handle user input
- [ ] Completed Password Manager
- [ ] Committed code to Git

## 📂 Code Examples

This day includes starter and completed code examples:
- **`_start/`** - Starting code with TODO comments and learning notes
- **`_end/`** - Completed solutions with detailed explanations

Check these folders to see the progression from start to finish and learn from the learning curve notes!

## 🚀 Next Steps
After completing Day 29, you should be able to:
- Build data management apps
- Work with file I/O
- Create secure applications
- Build practical tools

**Ready for Day 30?** You'll improve the Password Manager with exceptions and JSON!







