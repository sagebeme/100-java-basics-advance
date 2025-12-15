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

## ✅ Checklist
- [ ] Can work with lists effectively
- [ ] Can implement snake body
- [ ] Can generate food
- [ ] Can detect collisions
- [ ] Can track score
- [ ] Completed Snake Game
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 21, you should be able to:
- Build complete games
- Work with dynamic lists
- Implement game mechanics
- Handle game state

**Ready for Day 22?** You'll build the classic Pong game!



