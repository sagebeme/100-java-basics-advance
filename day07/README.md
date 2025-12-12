# Day 7 - Hangman Game

## 📚 Learning Objectives
- Build a complete game from scratch
- Practice using arrays/lists for word management
- Implement game logic with loops and conditionals
- Handle user input and validation
- Create an interactive console game

## 🎯 Topics Covered
- Game design and planning
- Random word selection
- Character matching
- Game state management
- User input validation
- Display formatting

## 📝 Step-by-Step Instructions

### Step 1: Plan Your Game
Break down Hangman into components:
- Word list
- Random word selection
- Display word with blanks
- Guess tracking
- Lives/attempts counter
- Win/lose conditions

### Step 2: Create Word List
Store words in an array or list:

```java
String[] wordList = {
    "java", "python", "computer", "programming",
    "algorithm", "software", "developer"
};
```

### Step 3: Random Word Selection
Pick a random word:

```java
Random random = new Random();
String word = wordList[random.nextInt(wordList.length)];
```

### Step 4: Display Logic
Show word with blanks for unguessed letters:

```java
String display = "";
for (char letter : word.toCharArray()) {
    if (guessedLetters.contains(letter)) {
        display += letter + " ";
    } else {
        display += "_ ";
    }
}
```

### Step 5: Game Loop
Create main game loop:

```java
while (lives > 0 && !isWordComplete) {
    // Display current state
    // Get user guess
    // Check guess
    // Update game state
    // Check win/lose conditions
}
```

## 💻 Exercises

### Exercise 1: Word Display
Create a method that:
- Takes a word and list of guessed letters
- Returns string with blanks and revealed letters
- Example: "JAVA" with guesses ['A', 'V'] → "_ A V A"

### Exercise 2: Guess Validation
Create a method that:
- Checks if guess is valid (single letter, not guessed before)
- Returns true/false
- Handles invalid input gracefully

### Exercise 3: Game State
Create methods for:
- Checking if word is complete
- Counting remaining lives
- Determining win/lose status

## 🎮 Project: Hangman Game

### Requirements
Create a complete Hangman game:
1. Choose a random word from a list
2. Display word as blanks (e.g., "_ _ _ _")
3. Ask user to guess a letter
4. Reveal letter if correct, reduce lives if wrong
5. Track guessed letters
6. Display hangman ASCII art (optional)
7. Win if word completed, lose if lives reach 0

### Example Output
```
Welcome to Hangman!
_ _ _ _ _ _ _ 
You have 6 lives remaining.
Guess a letter: a
Good guess!
_ A _ _ _ _ A 
You have 6 lives remaining.
Guess a letter: e
Sorry, that letter is not in the word.
_ A _ _ _ _ A 
You have 5 lives remaining.
...
You win! The word was: PROGRAMMING
```

### Starter Code
```java
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        String[] wordList = {
            "java", "python", "computer", "programming",
            "algorithm", "software", "developer"
        };
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Use ArrayList to track guessed letters
- Create method to display word with blanks
- Use contains() to check if letter guessed
- Use indexOf() to find letter positions
- Track lives as integer
- Use loop until win or lose

### Bonus Features
- Add ASCII art for hangman stages
- Add difficulty levels (easy/medium/hard word lists)
- Add hints system
- Save high scores
- Add word categories

## 📚 Resources

### Official Documentation
- [Java Random Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
- [String Methods - Oracle Docs](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)
- [ArrayList - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)

### Tutorials
- [Java Game Development Basics](https://www.geeksforgeeks.org/java-game-development/)
- [Building Console Games in Java](https://www.javatpoint.com/java-programs)

### Video Resources
- [Hangman Game Tutorial](https://www.youtube.com/results?search_query=java+hangman+game+tutorial)
- [Java Game Development](https://www.youtube.com/results?search_query=java+console+game+tutorial)

### Practice Platforms
- [Game Development Exercises](https://www.w3resource.com/java-exercises/)
- [Project Ideas](https://github.com/karan/Projects)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Game Loop | Main game execution | `while (!gameOver) { ... }` |
| State Management | Track game progress | `lives`, `guessedLetters` |
| Random Selection | Pick random element | `random.nextInt(array.length)` |
| String Manipulation | Work with strings | `word.indexOf(letter)` |
| User Validation | Check input validity | `if (input.length() == 1) { ... }` |

## ✅ Checklist
- [ ] Understand game design principles
- [ ] Can select random words
- [ ] Can display word with blanks
- [ ] Can handle user guesses
- [ ] Can track game state
- [ ] Can determine win/lose
- [ ] Completed Hangman game
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 7, you should be able to:
- Design and build complete games
- Manage game state
- Handle user interaction
- Implement game logic

**Ready for Day 8?** You'll learn about function parameters and build a Caesar Cipher!


