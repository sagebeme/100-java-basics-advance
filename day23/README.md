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

## 🚀 Next Steps
After completing Day 23, you should be able to:
- Build complex games
- Manage multiple game objects
- Implement game progression
- Create engaging gameplay

**Ready for Day 24?** You'll learn about file I/O and paths!







