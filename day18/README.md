# Day 18 - JavaFX Graphics and GUIs

## 📚 Learning Objectives
- Introduction to JavaFX
- Learn about graphics programming
- Work with Canvas and shapes
- Create visual applications
- Understand coordinate systems

## 🎯 Topics Covered
- JavaFX basics
- Canvas and GraphicsContext
- Drawing shapes (lines, circles, rectangles)
- Colors and styling
- Event handling
- Animation basics

## 📝 Step-by-Step Instructions

### Step 1: JavaFX Setup
Set up JavaFX project:

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraphicsApp extends Application {
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Draw shapes here
        
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

### Step 2: Drawing Shapes
Draw various shapes:

```java
GraphicsContext gc = canvas.getGraphicsContext2D();

// Draw line
gc.strokeLine(0, 0, 100, 100);

// Draw rectangle
gc.strokeRect(50, 50, 100, 80);
gc.fillRect(200, 50, 100, 80);

// Draw circle
gc.strokeOval(100, 100, 50, 50);
gc.fillOval(200, 100, 50, 50);

// Set colors
gc.setFill(Color.BLUE);
gc.setStroke(Color.RED);
```

### Step 3: Coordinate System
Understand JavaFX coordinates:
- Origin (0,0) at top-left
- X increases right
- Y increases down
- Canvas size determines bounds

## 💻 Exercises

### Exercise 1: Basic Shapes
Create programs that draw:
- Squares and rectangles
- Circles and ovals
- Lines and polygons
- Different colors

### Exercise 2: Patterns
Create patterns:
- Checkerboard
- Spiral
- Grid
- Random shapes

### Exercise 3: Interactive Drawing
Create programs with:
- Mouse click drawing
- Drag to draw
- Color selection
- Shape selection

## 🎮 Project: Hirst Painting Generator

### Requirements
Create a program that generates a Hirst-style dot painting:
1. Create canvas
2. Generate random colors
3. Draw dots in grid pattern
4. Use random colors for each dot
5. Create artistic pattern

### Example Output
A canvas with colorful dots arranged in a grid, similar to Damien Hirst's spot paintings.

### Starter Code
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;

public class HirstPainting extends Application {
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Your code here
        
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Hirst Painting");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

### Solution Hints
- Define color palette
- Create grid of positions
- Draw circles at each position
- Use random colors from palette
- Adjust dot size and spacing

## 📚 Resources

### Official Documentation
- [JavaFX Overview - Oracle Docs](https://docs.oracle.com/javase/8/javafx/get-started-tutorial/jfx-overview.htm)
- [Canvas API - JavaFX Docs](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/canvas/Canvas.html)
- [GraphicsContext - JavaFX Docs](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/canvas/GraphicsContext.html)

### Tutorials
- [JavaFX Tutorial - Code.makery](https://code.makery.ch/library/javafx-tutorial/)
- [JavaFX Canvas - Jenkov](https://jenkov.com/tutorials/javafx/canvas.html)
- [JavaFX Graphics - GeeksforGeeks](https://www.geeksforgeeks.org/javafx/)

### Video Resources
- [JavaFX Tutorial](https://www.youtube.com/results?search_query=javafx+tutorial)
- [JavaFX Canvas Drawing](https://www.youtube.com/results?search_query=javafx+canvas+drawing)

### Practice Platforms
- [JavaFX Projects](https://github.com/topics/javafx)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Canvas | Drawing surface | `new Canvas(800, 600)` |
| GraphicsContext | Drawing tool | `canvas.getGraphicsContext2D()` |
| Coordinate System | X, Y positioning | Origin at top-left |
| Stroke | Outline drawing | `gc.strokeRect()` |
| Fill | Solid drawing | `gc.fillRect()` |

## ✅ Checklist
- [ ] Understand JavaFX basics
- [ ] Can create canvas
- [ ] Can draw shapes
- [ ] Can use colors
- [ ] Understand coordinates
- [ ] Completed Hirst Painting
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
   cd "path\to\100-java-basics-advance\day18\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac HirstPainting.java
   ```

4. **Run the compiled program**
   ```cmd
   java HirstPainting
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day18/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac HirstPainting.java
   ```

4. **Run the compiled program**
   ```bash
   java HirstPainting
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'ClassName.main()'"

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
- **"package does not exist"** - Remove package declaration if running from command line, or use proper package structure


## 🚀 Next Steps
After completing Day 18, you should be able to:
- Create graphical applications
- Draw shapes and patterns
- Work with colors
- Build visual programs

**Ready for Day 19?** You'll learn about inheritance and polymorphism!







