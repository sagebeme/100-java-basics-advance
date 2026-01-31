# Day 22 - Build Pong: The Famous Arcade Game

## 📚 Learning Objectives
- Build a complete Pong game
- Implement paddle movement
- Create ball physics
- Handle collisions
- Add scoring system

## 🎯 Topics Covered
- Game physics
- Paddle mechanics
- Ball movement and bouncing
- Collision detection
- Score tracking
- Two-player gameplay

## 📝 Step-by-Step Instructions

### Step 1: Create Game Components
Design your classes:

```java
public class Paddle {
    private int x, y;
    private int width, height;
    private int speed;
    
    public void moveUp() {
        y -= speed;
    }
    
    public void moveDown() {
        y += speed;
    }
}

public class Ball {
    private int x, y;
    private int velocityX, velocityY;
    private int radius;
    
    public void move() {
        x += velocityX;
        y += velocityY;
    }
    
    public void bounceX() {
        velocityX = -velocityX;
    }
    
    public void bounceY() {
        velocityY = -velocityY;
    }
}
```

### Step 2: Paddle Movement
Handle keyboard input for paddles:

```java
scene.setOnKeyPressed(e -> {
    if (e.getCode() == KeyCode.W) {
        leftPaddle.moveUp();
    } else if (e.getCode() == KeyCode.S) {
        leftPaddle.moveDown();
    } else if (e.getCode() == KeyCode.UP) {
        rightPaddle.moveUp();
    } else if (e.getCode() == KeyCode.DOWN) {
        rightPaddle.moveDown();
    }
});
```

### Step 3: Ball Physics
Implement ball movement and bouncing:

```java
private void updateBall() {
    ball.move();
    
    // Top and bottom walls
    if (ball.getY() <= 0 || ball.getY() >= HEIGHT) {
        ball.bounceY();
    }
    
    // Paddle collisions
    if (checkPaddleCollision(ball, leftPaddle) || 
        checkPaddleCollision(ball, rightPaddle)) {
        ball.bounceX();
    }
    
    // Score points
    if (ball.getX() < 0) {
        rightScore++;
        resetBall();
    } else if (ball.getX() > WIDTH) {
        leftScore++;
        resetBall();
    }
}
```

## 💻 Exercises

### Exercise 1: Paddle Class
Create a Paddle class with:
- Position and size
- Movement methods
- Boundary checking
- Drawing method

### Exercise 2: Ball Class
Create a Ball class with:
- Position and velocity
- Movement method
- Bounce methods
- Collision detection

### Exercise 3: Game Logic
Implement:
- Score tracking
- Ball reset
- Game over condition
- Win detection

## 🎮 Project: Pong Game

### Requirements
Create a complete Pong game:
1. Two paddles (left and right)
2. Ball that moves and bounces
3. Keyboard controls (W/S for left, Up/Down for right)
4. Score tracking
5. Ball resets after scoring
6. Game over at target score

### Example Output
```
Left Score: 3    Right Score: 5

[Game window with paddles and ball]
[Ball bounces off walls and paddles]
[Score updates when ball passes paddle]
```

### Starter Code
```java
public class PongGame extends Application {
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;
    private int leftScore = 0;
    private int rightScore = 0;
    
    // Game setup and loop
}
```

## 📚 Resources

### Official Documentation
- [JavaFX Application - Oracle Docs](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html)

### Tutorials
- [Pong Game Tutorial](https://www.geeksforgeeks.org/create-pong-game-using-java/)
- [Game Physics Basics](https://www.gamedeveloper.com/programming/game-physics)

### Video Resources
- [Pong Game Java Tutorial](https://www.youtube.com/results?search_query=pong+game+java+tutorial)
- [Java Game Physics](https://www.youtube.com/results?search_query=java+game+physics)

### Practice Platforms
- [Game Development Projects](https://github.com/karan/Projects#games)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Physics | Movement and collisions | Velocity, bouncing |
| Paddle | Player-controlled object | Moves up/down |
| Ball | Moving game object | Bounces off surfaces |
| Collision | Object interaction | Ball hits paddle |
| Score | Points tracking | Increment on goal |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Paddles:** Re-read **Step 1 & 2** — move up/down with key (W/S, Up/Down); clamp y to screen bounds — see **Step 2**
- **Ball:** Update x,y each frame; bounce: negate velocity on paddle/wall hit — see **Step 3**
- **Collision:** Ball vs paddle: check ball x,y against paddle rectangle — see **Step 3**
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for physics and scoring

**Related days:** Day 20 (keyboard, animation); Day 21 (collision). **JavaFX:** Compile/run needs JavaFX SDK (see Troubleshooting).

**Quick reference:** Bounce: `velocityX = -velocityX` · Clamp paddle: `y = Math.max(0, Math.min(HEIGHT - paddleH, y))`

## ✅ Checklist
- [ ] Can create game components
- [ ] Can implement physics
- [ ] Can handle collisions
- [ ] Can track scores
- [ ] Completed Pong game
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
   cd "path\to\100-java-basics-advance\day22\_end\src"
   ```

3. **Compile the Java file** (this project uses JavaFX — see note below)
   ```cmd
   javac PongGame.java
   ```
   *If you get "package javafx does not exist"*, JavaFX is not on the classpath. Use an IDE (recommended) or add JavaFX SDK and compile with: `javac --module-path "C:\path\to\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml PongGame.java`

4. **Run the compiled program**
   ```cmd
   java PongGame
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day22/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac PongGame.java
   ```

4. **Run the compiled program**
   ```bash
   java PongGame
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'PongGame.main()'"

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
- **"package javafx does not exist"** - This project uses JavaFX. On Java 11+, JavaFX is not included in the JDK. Either run from an IDE (IntelliJ/VS Code/Eclipse with JavaFX support) or [download JavaFX SDK](https://gluonhq.com/products/javafx/) and use `--module-path` and `--add-modules javafx.controls,javafx.fxml` for both `javac` and `java`

## 🚀 Next Steps
After completing Day 22, you should be able to:
- Build physics-based games
- Handle multiple game objects
- Implement scoring systems
- Create two-player games

**Ready for Day 23?** You'll build the Turtle Crossing capstone project!







