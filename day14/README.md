# Day 14 - Higher Lower Game Project

## 📚 Learning Objectives
- Build a complete guessing game
- Work with data structures
- Implement game logic
- Practice with loops and conditionals
- Create engaging user experience

## 🎯 Topics Covered
- Game design
- Data organization
- Comparison logic
- Score tracking
- User feedback
- Game flow

## 📝 Step-by-Step Instructions

### Step 1: Game Concept
Higher Lower game:
- Compare two items (people, brands, etc.)
- User guesses which has higher value
- Track score
- Continue until wrong guess

### Step 2: Data Structure
Store game data:

```java
// Option 1: Map
Map<String, Integer> data = new HashMap<>();
data.put("Instagram", 346);
data.put("Cristiano Ronaldo", 215);
data.put("Ariana Grande", 183);

// Option 2: Class
class Person {
    String name;
    int followers;
}
```

### Step 3: Game Logic
Compare values:

```java
public static boolean compare(String optionA, String optionB, String guess) {
    int valueA = data.get(optionA);
    int valueB = data.get(optionB);
    
    if (guess.equals("A") && valueA > valueB) {
        return true;
    } else if (guess.equals("B") && valueB > valueA) {
        return true;
    }
    return false;
}
```

### Step 4: Game Flow
Main game loop:

```java
int score = 0;
boolean gameContinue = true;

while (gameContinue) {
    // Get two random items
    // Display comparison
    // Get user guess
    // Check if correct
    // Update score
    // Check if game should continue
}
```

## 💻 Exercises

### Exercise 1: Data Organization
Create data structures for:
- Celebrity followers
- Brand values
- Country populations
- Game scores

### Exercise 2: Comparison Logic
Create methods to:
- Compare two values
- Determine winner
- Format comparison display
- Validate user input

### Exercise 3: Game State
Create systems for:
- Score tracking
- Game continuation
- Random selection
- Result display

## 🎮 Project: Higher Lower Game

### Requirements
Create a Higher Lower game:
1. Display game logo
2. Show two options to compare
3. Ask user which has higher value
4. Check if guess is correct
5. Display result and current score
6. Continue with new comparison
7. End game when wrong guess
8. Display final score

### Example Output
```
    __  ___       __             
   / / / (_)___ _/ /_  ___  _____
  / /_/ / / __ `/ __ \/ _ \/ ___/
 / __  / / /_/ / / / /  __/ /    
/_/ /_/_/\__, /_/ /_/\___/_/     
   / /  /____/_      _____  _____
  / /   / __ \ | /| / / _ \/ ___/
 / /___/ /_/ / |/ |/ /  __/ /    
/_____/\____/|__/|__/\___/_/     

Compare A: Cristiano Ronaldo, a Footballer, from Portugal.
Against B: Ariana Grande, a Musician, from United States.

Who has more followers? Type 'A' or 'B': A
You're right! Current score: 1.

Compare A: Ariana Grande, a Musician, from United States.
Against B: Instagram, a Social media platform, from United States.

Who has more followers? Type 'A' or 'B': B
Sorry, that's wrong. Final score: 1
```

### Starter Code
```java
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class HigherLower {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Create your data structure here
        
        System.out.println("Welcome to Higher Lower Game!");
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Use Map to store name-value pairs
- Create method to get random entry
- Track current option for next round
- Use loop for game continuation
- Format output nicely
- Handle edge cases

### Sample Data
```java
Map<String, Integer> data = new HashMap<>();
data.put("Instagram", 346);
data.put("Cristiano Ronaldo", 215);
data.put("Ariana Grande", 183);
data.put("Dwayne Johnson", 181);
data.put("Selena Gomez", 174);
// Add more entries
```

### Bonus Features
- Multiple categories (followers, net worth, etc.)
- Difficulty levels
- Hints system
- Statistics tracking
- Leaderboard
- Custom data entry

## 📚 Resources

### Official Documentation
- [HashMap - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
- [Random Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
- [Collections - Oracle Docs](https://docs.oracle.com/javase/tutorial/collections/)

### Tutorials
- [Java HashMap Tutorial](https://www.w3schools.com/java/java_hashmap.asp)
- [Game Development Basics](https://www.geeksforgeeks.org/java-game-development/)

### Video Resources
- [Higher Lower Game Tutorial](https://www.youtube.com/results?search_query=higher+lower+game+java)
- [Java Game Development](https://www.youtube.com/results?search_query=java+console+game)

### Practice Platforms
- [Game Projects](https://github.com/karan/Projects#games)
- [Java Exercises](https://www.w3resource.com/java-exercises/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Data Structure | Organize game data | `Map<String, Integer>` |
| Random Selection | Pick random items | `random.nextInt(list.size())` |
| Comparison Logic | Compare values | `valueA > valueB` |
| Game State | Track progress | `score`, `gameContinue` |
| User Feedback | Show results | Win/lose messages |

## ✅ Checklist
- [ ] Understand game concept
- [ ] Can organize data effectively
- [ ] Can compare values
- [ ] Can track game state
- [ ] Can create engaging gameplay
- [ ] Completed Higher Lower game
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
   cd "path\to\100-java-basics-advance\day14\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac HigherLowerGame.java
   ```

4. **Run the compiled program**
   ```cmd
   java HigherLowerGame
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day14/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac HigherLowerGame.java
   ```

4. **Run the compiled program**
   ```bash
   java HigherLowerGame
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
After completing Day 14, you should be able to:
- Design and build interactive games
- Organize data effectively
- Create engaging user experiences
- Implement game logic

**Congratulations!** You've completed the Beginner section (Days 1-14)!

**Ready for Day 15?** You'll set up your development environment and start learning Object-Oriented Programming!







