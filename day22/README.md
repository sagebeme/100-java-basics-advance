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

## 🚀 Next Steps
After completing Day 22, you should be able to:
- Build physics-based games
- Handle multiple game objects
- Implement scoring systems
- Create two-player games

**Ready for Day 23?** You'll build the Turtle Crossing capstone project!







