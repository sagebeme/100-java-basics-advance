# Day 11 - Blackjack Capstone Project

## 📚 Learning Objectives
- Build a complete card game
- Implement game rules and logic
- Handle complex game state
- Practice with arrays and random numbers
- Create a polished, playable game

## 🎯 Topics Covered
- Game design and architecture
- Card game logic
- Random card dealing
- Score calculation
- Game flow control
- User interaction

## 📝 Step-by-Step Instructions

### Step 1: Plan the Game
Break down Blackjack:
- Deck of cards (52 cards)
- Deal cards to player and dealer
- Calculate hand values
- Player decisions (hit/stand)
- Dealer rules (must hit until 17+)
- Win/lose conditions

### Step 2: Create Card System
Represent cards:

```java
// Option 1: Simple array
String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

// Option 2: Card class (more advanced)
class Card {
    String suit;
    String rank;
    int value;
}
```

### Step 3: Calculate Hand Value
Handle Ace as 1 or 11:

```java
public static int calculateHandValue(ArrayList<String> hand) {
    int value = 0;
    int aces = 0;
    
    for (String card : hand) {
        // Extract value from card
        // Handle Ace separately
        // Add to total
    }
    
    // Adjust for Aces
    while (value > 21 && aces > 0) {
        value -= 10;
        aces--;
    }
    
    return value;
}
```

### Step 4: Game Loop
Main game flow:

```java
// Deal initial cards
// Player turn loop
while (playerValue < 21) {
    // Ask hit or stand
    // Deal card if hit
    // Check for bust
}
// Dealer turn
// Compare scores
// Determine winner
```

## 💻 Exercises

### Exercise 1: Card Representation
Create a system to:
- Represent 52 cards
- Shuffle cards
- Deal random cards
- Track dealt cards

### Exercise 2: Score Calculation
Create methods to:
- Calculate hand value
- Handle Ace (1 or 11)
- Detect blackjack (21 with 2 cards)
- Detect bust (over 21)

### Exercise 3: Game Logic
Create methods for:
- Player turn
- Dealer turn
- Win/lose determination
- Display game state

## 🎮 Project: Blackjack Game

### Requirements
Create a complete Blackjack game:
1. Deal 2 cards to player and dealer
2. Show player cards and one dealer card
3. Ask player to hit or stand
4. Deal cards if player hits
5. Check for bust (over 21)
6. Dealer plays (hits until 17+)
7. Compare scores
8. Determine winner
9. Ask to play again

### Blackjack Rules
- Goal: Get as close to 21 as possible without going over
- Face cards (J, Q, K) = 10
- Ace = 1 or 11 (whichever is better)
- Dealer must hit until 17 or higher
- Blackjack = 21 with 2 cards (automatic win unless dealer also has blackjack)

### Example Output
```
Welcome to Blackjack!

Your cards: [King of Hearts, 5 of Diamonds]
Current score: 15
Computer's first card: 7 of Clubs

Type 'y' to get another card, type 'n' to pass: y
Your cards: [King of Hearts, 5 of Diamonds, 8 of Clubs]
Current score: 23
You went over. You lose 😭

Do you want to play a game of Blackjack? Type 'y' or 'n': y
```

### Starter Code
```java
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to Blackjack!");
        
        // Your code here
        
        scanner.close();
    }
    
    // Create helper methods
    // dealCard(), calculateScore(), etc.
}
```

### Solution Hints
- Use ArrayList to store hands
- Create deck as list of card strings
- Use Random to select cards
- Remove dealt cards from deck
- Create method to format card display
- Handle Ace value dynamically

### Bonus Features
- Betting system
- Multiple players
- Split pairs
- Double down option
- Insurance for dealer blackjack
- Statistics tracking
- Card counting detection (just for fun!)

## 📚 Resources

### Official Documentation
- [ArrayList - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [Random Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
- [Collections - Oracle Docs](https://docs.oracle.com/javase/tutorial/collections/)

### Tutorials
- [Blackjack Rules](https://bicyclecards.com/how-to-play/blackjack/)
- [Card Game Design](https://www.geeksforgeeks.org/designing-card-game/)
- [Java Game Development](https://www.javatpoint.com/java-game-development)

### Video Resources
- [Blackjack Game Tutorial](https://www.youtube.com/results?search_query=java+blackjack+game+tutorial)
- [Card Game Implementation](https://www.youtube.com/results?search_query=java+card+game+tutorial)

### Practice Platforms
- [Game Development Projects](https://github.com/karan/Projects#games)
- [Java Projects](https://www.geeksforgeeks.org/java-projects/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Game State | Current game situation | Player hand, dealer hand, scores |
| Random Selection | Pick random element | `cards.get(random.nextInt(cards.size()))` |
| Hand Value | Total card points | Ace + King = 11 or 21 |
| Game Loop | Main game execution | While game not over |
| Win Conditions | Rules for winning | Closer to 21, not bust |

## ✅ Checklist
- [ ] Understand Blackjack rules
- [ ] Can represent and shuffle cards
- [ ] Can calculate hand values
- [ ] Can handle Ace (1 or 11)
- [ ] Can implement game flow
- [ ] Can determine winner
- [ ] Completed Blackjack game
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 11, you should be able to:
- Design and build complete games
- Handle complex game logic
- Manage game state effectively
- Create engaging user experiences

**Congratulations!** You've completed your first major capstone project!

**Ready for Day 12?** You'll learn about scope and namespacing!


