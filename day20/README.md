# Day 20 - Build Snake Game Part 1: Animation & Coordinates

## 📚 Learning Objectives
- Create game animation
- Understand game coordinates
- Work with game loops
- Handle keyboard input
- Build game foundation

## 🎯 Topics Covered
- Game animation
- Coordinate systems
- Game loop (update, render)
- Keyboard event handling
- Sprite movement
- Collision detection basics

## 📝 Step-by-Step Instructions

### Step 1: Game Window Setup
Create game window with JavaFX:

```java
public class SnakeGame extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Game loop setup
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            update();
            render(gc);
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        
        Scene scene = new Scene(new StackPane(canvas));
        stage.setScene(scene);
        stage.show();
    }
}
```

### Step 2: Coordinate System
Understand game coordinates:

```java
// Grid-based coordinates
private static final int GRID_SIZE = 20;
private int x = 0; // Grid position, not pixels
private int y = 0;

// Convert to pixel coordinates
private int pixelX = x * GRID_SIZE;
private int pixelY = y * GRID_SIZE;
```

### Step 3: Animation Loop
Create update and render methods:

```java
private void update() {
    // Update game state
    // Move snake
    // Check collisions
}

private void render(GraphicsContext gc) {
    // Clear canvas
    gc.clearRect(0, 0, WIDTH, HEIGHT);
    
    // Draw game elements
    drawSnake(gc);
    drawFood(gc);
}
```

### Step 4: Keyboard Input
Handle arrow keys:

```java
scene.setOnKeyPressed(event -> {
    switch (event.getCode()) {
        case UP:
            direction = "UP";
            break;
        case DOWN:
            direction = "DOWN";
            break;
        case LEFT:
            direction = "LEFT";
            break;
        case RIGHT:
            direction = "RIGHT";
            break;
    }
});
```

## 💻 Exercises

### Exercise 1: Basic Animation
Create programs that:
- Move a shape across screen
- Bounce shape off walls
- Animate multiple objects
- Control speed

### Exercise 2: Coordinate Systems
Practice with:
- Grid-based movement
- Pixel-based movement
- Coordinate conversion
- Boundary checking

### Exercise 3: Input Handling
Create programs with:
- Keyboard controls
- Direction changes
- Input validation
- Multiple key support

## 🎮 Project: Snake Game Part 1

### Requirements
Build foundation for Snake game:
1. Create game window
2. Set up game loop
3. Create snake (single segment first)
4. Add keyboard controls
5. Make snake move continuously
6. Add boundary detection

### Example Output
A window with a moving square (snake head) that responds to arrow keys.

### Starter Code
```java
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int GRID_SIZE = 20;
    
    private int snakeX = 0;
    private int snakeY = 0;
    private String direction = "RIGHT";
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Scene scene = new Scene(new StackPane(canvas));
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            update();
            render(gc);
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        
        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.show();
    }
    
    private void update() {
        // Move snake based on direction
    }
    
    private void render(GraphicsContext gc) {
        // Clear and draw
    }
    
    private void handleKeyPress(KeyCode code) {
        // Handle arrow keys
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

## 📚 Resources

### Official Documentation
- [JavaFX Animation - Oracle Docs](https://docs.oracle.com/javase/8/javafx/api/javafx/animation/package-summary.html)
- [Timeline - JavaFX Docs](https://openjfx.io/javadoc/11/javafx.controls/javafx/animation/Timeline.html)
- [KeyEvent - JavaFX Docs](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/input/KeyEvent.html)

### Tutorials
- [JavaFX Game Development](https://www.geeksforgeeks.org/javafx-game-development/)
- [Game Loop Pattern](https://gameprogrammingpatterns.com/game-loop.html)
- [JavaFX Animation](https://jenkov.com/tutorials/javafx/animation.html)

### Video Resources
- [JavaFX Game Tutorial](https://www.youtube.com/results?search_query=javafx+game+tutorial)
- [Snake Game Java](https://www.youtube.com/results?search_query=snake+game+java+tutorial)

### Practice Platforms
- [Game Development Projects](https://github.com/karan/Projects#games)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Game Loop | Continuous update/render | `Timeline` with KeyFrame |
| Animation | Moving objects over time | Update position each frame |
| Coordinates | Position in game space | Grid or pixel coordinates |
| Input Handling | Respond to user input | `setOnKeyPressed()` |
| Update Method | Change game state | Move objects, check collisions |

## ✅ Checklist
- [ ] Understand game loops
- [ ] Can create animations
- [ ] Can handle keyboard input
- [ ] Understand coordinate systems
- [ ] Can move objects
- [ ] Completed Snake Game Part 1
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 20, you should be able to:
- Create animated games
- Handle user input
- Manage game state
- Build game foundations

**Ready for Day 21?** You'll continue building Snake Game with inheritance!



