# Day 23 - Turtle Crossing Capstone Project

## 📚 Learning Objectives
- Build a complete side-scrolling game
- Implement moving obstacles
- Create player movement
- Handle game progression
- Add difficulty scaling

## 🎯 Topics Covered
- Side-scrolling mechanics
- Obstacle generation
- Player controls
- Collision detection
- Level progression
- Game state management

## 📝 Step-by-Step Instructions

### Step 1: Game Design
Plan your game:
- Player (turtle) at bottom
- Cars moving across screen
- Player moves up to cross
- Avoid cars
- Increase difficulty

### Step 2: Player Class
Create player with movement:

```java
public class Player {
    private int x, y;
    private int speed;
    
    public void moveUp() {
        y -= speed;
    }
    
    public void moveDown() {
        y += speed;
    }
    
    public boolean reachedTop() {
        return y <= 0;
    }
}
```

### Step 3: Obstacle Management
Create and manage cars:

```java
public class CarManager {
    private ArrayList<Car> cars;
    private Random random;
    
    public void generateCar() {
        if (random.nextInt(100) < 5) { // 5% chance per frame
            cars.add(new Car(random.nextInt(WIDTH), random.nextInt(HEIGHT)));
        }
    }
    
    public void moveCars() {
        for (Car car : cars) {
            car.move();
            if (car.isOffScreen()) {
                cars.remove(car);
            }
        }
    }
}
```

### Step 4: Collision Detection
Check player-car collisions:

```java
private boolean checkCollision(Player player, ArrayList<Car> cars) {
    for (Car car : cars) {
        if (player.getBounds().intersects(car.getBounds())) {
            return true;
        }
    }
    return false;
}
```

## 💻 Exercises

### Exercise 1: Player Movement
Create player with:
- Smooth movement
- Boundary checking
- Multiple directions
- Speed control

### Exercise 2: Obstacle System
Create obstacle system with:
- Random generation
- Movement patterns
- Removal when off-screen
- Multiple types

### Exercise 3: Game Progression
Implement:
- Level system
- Difficulty increase
- Score tracking
- Win condition

## 🎮 Project: Turtle Crossing Game

### Requirements
Create a turtle crossing game:
1. Player turtle at bottom
2. Cars moving horizontally
3. Player moves up to cross
4. Avoid cars
5. Level up when reaching top
6. Increase difficulty each level
7. Game over on collision

### Example Output
```
Level: 3
Score: 250

[Game window with turtle and moving cars]
[Turtle moves up when arrow key pressed]
[Cars move left/right]
[Level increases when turtle reaches top]
```

### Starter Code Structure
```java
public class TurtleCrossing extends Application {
    private Player player;
    private CarManager carManager;
    private int level = 1;
    private int score = 0;
    
    // Game setup and loop
}
```

## 📚 Resources

### Official Documentation
- [JavaFX Animation](https://openjfx.io/javadoc/11/javafx.controls/javafx/animation/package-summary.html)

### Tutorials
- [Game Development Patterns](https://gameprogrammingpatterns.com/)
- [Side-Scrolling Games](https://www.gamedeveloper.com/design/side-scrolling-game-design)

### Video Resources
- [Turtle Crossing Game Tutorial](https://www.youtube.com/results?search_query=turtle+crossing+game+java)
- [Java Game Development](https://www.youtube.com/results?search_query=java+game+development)

### Practice Platforms
- [Game Projects](https://github.com/karan/Projects#games)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Side-Scrolling | Horizontal movement | Cars moving across |
| Obstacle Generation | Create enemies | Random car spawning |
| Level Progression | Increase difficulty | More cars, faster speed |
| Collision Detection | Check intersections | Player vs cars |
| Game State | Current game situation | Level, score, lives |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Player movement:** Re-read **Step 1** — move up/down (or jump); keep within road/safe zone — see **Step 2**
- **Obstacles:** Spawn cars at random lanes; move left (or right); remove when off-screen — see **Step 2**
- **Collision:** Check player bounds vs each car bounds (rectangle overlap) — see **Step 3**
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for level and collision logic

**Related days:** Day 20 (animation); Day 22 (collision). **JavaFX:** See How to Run / Troubleshooting for module path.

**Quick reference:** Rectangle overlap: `x1 < x2+w2 && x2 < x1+w1 && y1 < y2+h2 && y2 < y1+h1`

## ✅ Checklist
- [ ] Can create side-scrolling games
- [ ] Can manage obstacles
- [ ] Can implement collisions
- [ ] Can handle level progression
- [ ] Completed Turtle Crossing
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
   cd "path\to\100-java-basics-advance\day23\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac TurtleCrossing.java
   ```

4. **Run the compiled program**
   ```cmd
   java TurtleCrossing
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day23/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac TurtleCrossing.java
   ```

4. **Run the compiled program**
   ```bash
   java TurtleCrossing
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'TurtleCrossing.main()'"

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
After completing Day 23, you should be able to:
- Build complex games
- Manage multiple game objects
- Implement game progression
- Create engaging gameplay

**Ready for Day 24?** You'll learn about file I/O and paths!







