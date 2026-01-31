# Day 21 - Build Snake Game Part 2: Inheritance & List Slicing

## 📚 Learning Objectives
- Complete Snake game using inheritance
- Work with lists for snake body
- Implement food generation
- Add score tracking
- Handle game over conditions

## 🎯 Topics Covered
- List manipulation
- Snake body segments
- Food spawning
- Collision detection
- Score system
- Game over logic

## 📝 Step-by-Step Instructions

### Step 1: Snake Body with Lists
Use ArrayList for snake segments:

```java
import java.util.ArrayList;

public class Snake {
    private ArrayList<Segment> body;
    private String direction;
    
    public Snake() {
        body = new ArrayList<>();
        body.add(new Segment(0, 0)); // Head
        direction = "RIGHT";
    }
    
    public void move() {
        // Add new head based on direction
        Segment head = body.get(0);
        Segment newHead = new Segment(head.getX() + dx, head.getY() + dy);
        body.add(0, newHead);
        
        // Remove tail (unless eating food)
        if (!eatingFood) {
            body.remove(body.size() - 1);
        }
    }
}
```

### Step 2: Food Generation
Generate food at random positions:

```java
private void generateFood() {
    Random random = new Random();
    int foodX = random.nextInt(WIDTH / GRID_SIZE) * GRID_SIZE;
    int foodY = random.nextInt(HEIGHT / GRID_SIZE) * GRID_SIZE;
    
    // Make sure food doesn't spawn on snake
    while (isOnSnake(foodX, foodY)) {
        foodX = random.nextInt(WIDTH / GRID_SIZE) * GRID_SIZE;
        foodY = random.nextInt(HEIGHT / GRID_SIZE) * GRID_SIZE;
    }
}
```

### Step 3: Collision Detection
Check for collisions:

```java
private boolean checkCollision() {
    Segment head = snake.getHead();
    
    // Wall collision
    if (head.getX() < 0 || head.getX() >= WIDTH ||
        head.getY() < 0 || head.getY() >= HEIGHT) {
        return true;
    }
    
    // Self collision
    for (int i = 1; i < snake.getBody().size(); i++) {
        if (head.equals(snake.getBody().get(i))) {
            return true;
        }
    }
    
    return false;
}
```

### Step 4: Score System
Track and display score:

```java
private int score = 0;

private void eatFood() {
    score += 10;
    snake.grow();
    generateFood();
}
```

## 💻 Exercises

### Exercise 1: List Operations
Practice with:
- Adding/removing from lists
- Accessing list elements
- Iterating over lists
- List slicing (subList)

### Exercise 2: Collision Detection
Create methods for:
- Wall collision
- Object collision
- Self collision
- Food collision

### Exercise 3: Game State
Implement:
- Score tracking
- Game over detection
- Restart functionality
- High score tracking

## 🎮 Project: Complete Snake Game

### Requirements
Complete the Snake game:
1. Snake with multiple segments
2. Food that spawns randomly
3. Snake grows when eating food
4. Collision detection (walls, self)
5. Score tracking
6. Game over screen
7. Restart functionality

### Example Output
```
Score: 50

[Game window with snake moving, food displayed]
[Snake grows when eating food]
[Game over when hitting wall or self]
```

### Starter Code Structure
```java
public class Snake {
    private ArrayList<Segment> body;
    private String direction;
    
    public void move() { }
    public void grow() { }
    public boolean checkSelfCollision() { }
}

public class Segment {
    private int x, y;
    // Constructor, getters, equals
}

public class Food {
    private int x, y;
    public void generateNew() { }
}
```

## 📚 Resources

### Official Documentation
- [ArrayList - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [List Interface - Oracle Docs](https://docs.oracle.com/javase/tutorial/collections/interfaces/list.html)

### Tutorials
- [Java Lists - W3Schools](https://www.w3schools.com/java/java_arraylist.asp)
- [Game Collision Detection](https://www.geeksforgeeks.org/collision-detection-in-games/)

### Video Resources
- [Complete Snake Game Tutorial](https://www.youtube.com/results?search_query=complete+snake+game+java)
- [Java Game Development](https://www.youtube.com/results?search_query=java+game+development)

### Practice Platforms
- [Game Development Projects](https://github.com/karan/Projects#games)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| List Slicing | Get sublist | `list.subList(0, 5)` |
| Snake Body | List of segments | `ArrayList<Segment>` |
| Food Spawning | Random generation | `random.nextInt()` |
| Collision | Object intersection | Check coordinates |
| Score | Game points | Increment on food eaten |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Snake body:** Re-read **Step 1** — use `ArrayList<Segment>`; add new head, remove tail (unless eating) — see **Step 2**
- **Food:** Random position; ensure not on snake — see **Step 2**
- **Collision:** Wall: check x/y bounds; self: head equals any body segment — see **Step 3**
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for full game logic

**Related days:** Day 20 (animation loop); Day 19 (inheritance for segments); Day 4 (ArrayList). **JavaFX:** See Troubleshooting.

**Quick reference:** `list.add(0, newHead); list.remove(list.size()-1)` · Collision: compare coordinates · `subList(from, to)` for slicing

## ✅ Checklist
- [ ] Can work with lists effectively
- [ ] Can implement snake body
- [ ] Can generate food
- [ ] Can detect collisions
- [ ] Can track score
- [ ] Completed Snake Game
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
   cd "path\to\100-java-basics-advance\day21\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac CompleteSnakeGame.java
   ```

4. **Run the compiled program**
   ```cmd
   java CompleteSnakeGame
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day21/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac CompleteSnakeGame.java
   ```

4. **Run the compiled program**
   ```bash
   java CompleteSnakeGame
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
After completing Day 21, you should be able to:
- Build complete games
- Work with dynamic lists
- Implement game mechanics
- Handle game state

**Ready for Day 22?** You'll build the classic Pong game!







