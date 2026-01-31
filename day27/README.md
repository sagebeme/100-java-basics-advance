# Day 27 - JavaFX GUI Programs

## 📚 Learning Objectives
- Build GUI applications with JavaFX
- Work with FXML and Scene Builder
- Understand layouts and controls
- Handle events in GUI
- Create user-friendly interfaces

## 🎯 Topics Covered
- JavaFX application structure
- FXML files
- Scene Builder
- Layouts (VBox, HBox, GridPane)
- Controls (Button, Label, TextField)
- Event handling
- CSS styling

## 📝 Step-by-Step Instructions

### Step 1: Basic JavaFX Application
Create simple GUI:

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIApp extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, JavaFX!");
        Button button = new Button("Click Me");
        
        button.setOnAction(e -> label.setText("Button clicked!"));
        
        VBox root = new VBox(10);
        root.getChildren().addAll(label, button);
        
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("My GUI App");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

### Step 2: Using FXML
Separate UI from logic:

**sample.fxml:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<VBox xmlns="http://javafx.com/javafx">
    <Label text="Hello, JavaFX!" />
    <Button text="Click Me" onAction="#handleButtonClick" />
</VBox>
```

**Controller.java:**
```java
public class Controller {
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonClick() {
        label.setText("Button clicked!");
    }
}
```

### Step 3: Common Controls
Use various controls:

```java
TextField textField = new TextField();
Button button = new Button("Submit");
Label label = new Label();
CheckBox checkBox = new CheckBox("Option");
RadioButton radioButton = new RadioButton("Choice");
```

### Step 4: Layouts
Organize UI elements:

```java
// VBox - vertical layout
VBox vbox = new VBox(10); // 10px spacing

// HBox - horizontal layout
HBox hbox = new HBox(10);

// GridPane - grid layout
GridPane grid = new GridPane();
grid.add(label, 0, 0);
grid.add(button, 1, 0);
```

## 💻 Exercises

### Exercise 1: Basic GUI
Create GUIs with:
- Labels and buttons
- Text fields
- Event handlers
- Multiple layouts

### Exercise 2: Form GUI
Create forms with:
- Input fields
- Submit buttons
- Validation
- Result display

### Exercise 3: Interactive GUI
Create interactive apps with:
- Multiple controls
- Event handling
- State management
- User feedback

## 🎮 Project: Mile to Kilometer Converter

### Requirements
Create a unit converter GUI:
1. Input field for miles
2. Convert button
3. Display kilometers
4. Real-time conversion (optional)
5. Clear button
6. Nice styling

### Example Output
```
[GUI Window]
Miles to Kilometers Converter

Enter miles: [____]
[Convert Button]

Result: 0.0 km
```

### Starter Code
```java
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Converter extends Application {
    @Override
    public void start(Stage stage) {
        // Create UI components
        // Set up layout
        // Add event handlers
        // Show window
    }
    
    private double milesToKm(double miles) {
        return miles * 1.60934;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

## 📚 Resources

### Official Documentation
- [JavaFX Getting Started - Oracle Docs](https://docs.oracle.com/javase/8/javafx/get-started-tutorial/jfx-overview.htm)
- [JavaFX Controls - JavaFX Docs](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/package-summary.html)
- [FXML Introduction - Oracle Docs](https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/doc-files/introduction_to_fxml.html)

### Tutorials
- [JavaFX Tutorial - Code.makery](https://code.makery.ch/library/javafx-tutorial/)
- [JavaFX GUI - Jenkov](https://jenkov.com/tutorials/javafx/index.html)
- [JavaFX Basics - GeeksforGeeks](https://www.geeksforgeeks.org/javafx/)

### Video Resources
- [JavaFX Tutorial](https://www.youtube.com/results?search_query=javafx+tutorial)
- [JavaFX GUI Development](https://www.youtube.com/results?search_query=javafx+gui+development)

### Practice Platforms
- [JavaFX Projects](https://github.com/topics/javafx)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Application | JavaFX app entry | `extends Application` |
| Scene | Container for UI | `new Scene(root)` |
| Stage | Window | `stage.setScene(scene)` |
| Control | UI component | `Button`, `Label`, `TextField` |
| Layout | Container | `VBox`, `HBox`, `GridPane` |
| Event Handler | Respond to actions | `button.setOnAction()` |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **JavaFX structure:** Re-read **Step 1** — class extends `Application`, override `start(Stage)` — see **Step 2**
- **Controls:** Re-read **Step 2** — `Button`, `Label`, `TextField`; add to `VBox`/`HBox` — see **Step 3**
- **Events:** `button.setOnAction(e -> { ... });` — get text: `textField.getText()` — see **Step 3**
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for Converter logic

**Related days:** Day 18 (canvas); Day 28 (Pomodoro GUI). **JavaFX:** See How to Run / Troubleshooting for module path.

**Quick reference:** `new VBox(10, node1, node2)` · `scene = new Scene(root, w, h)` · `stage.show()` at end

## ✅ Checklist
- [ ] Understand JavaFX structure
- [ ] Can create GUI applications
- [ ] Know common controls
- [ ] Can use layouts
- [ ] Can handle events
- [ ] Completed Converter project
- [ ] Committed code to Git

## 📂 Code Examples

This day includes starter and completed code examples:
- **`_start/`** - Starting code with TODO comments and learning notes
- **`_end/`** - Completed solutions with detailed explanations

Check these folders to see the progression from start to finish and learn from the learning curve notes!

## 💻 How to Run Java Files

### Prerequisites
Make sure you have Java installed on your system:
- **Check installation**: Open terminal/command prompt and run `java -version`
- **If not installed**: Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

### Running Java Files

#### **Windows**

1. **Open Command Prompt or PowerShell**
   - Press `Win + R`, type `cmd` or `powershell`, press Enter

2. **Navigate to the project directory**
   ```cmd
   cd "path\to\100-java-basics-advance\day27\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac Converter.java
   ```

4. **Run the compiled program**
   ```cmd
   java Converter
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day27/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac Converter.java
   ```

4. **Run the compiled program**
   ```bash
   java Converter
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'Converter.main()'"

**VS Code:**
1. Install "Extension Pack for Java"
2. Open the Java file
3. Click "Run" button above `main` method or press `F5`

**Eclipse:**
1. Import project into Eclipse
2. Right-click on the Java file
3. Select "Run As" → "Java Application"

### Troubleshooting

- **"javac: command not found"** - Java is not installed or not in PATH
- **"Error: Could not find or load main class"** - Make sure you're in the correct directory and class name matches filename
- **JavaFX apps**: If using Java 11+, add VM options: `--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml`

## 🚀 Next Steps
After completing Day 27, you should be able to:
- Build GUI applications
- Use JavaFX controls
- Handle user events
- Create user-friendly interfaces

**Ready for Day 28?** You'll build a Pomodoro Timer application!







