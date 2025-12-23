# Day 3 - Control Flow and Logical Operators

## 📚 Learning Objectives
- Understand conditional statements (if, else, else if)
- Learn about logical operators (&&, ||, !)
- Master comparison operators (==, !=, <, >, <=, >=)
- Work with switch statements
- Practice nested conditionals

## 🎯 Topics Covered
- If-else statements
- Logical operators (AND, OR, NOT)
- Comparison operators
- Switch statements
- Ternary operator
- Nested conditionals

## 📝 Step-by-Step Instructions

### Step 1: If-Else Statements
Control the flow of your program based on conditions:

```java
if (condition) {
    // code to execute if condition is true
} else {
    // code to execute if condition is false
}
```

### Step 2: Logical Operators
Combine multiple conditions:

```java
// AND operator (&&)
if (age >= 18 && hasLicense) {
    // Both conditions must be true
}

// OR operator (||)
if (isStudent || isSenior) {
    // At least one condition must be true
}

// NOT operator (!)
if (!isRaining) {
    // Condition must be false
}
```

### Step 3: Comparison Operators
Compare values:

```java
==  // Equal to
!=  // Not equal to
<   // Less than
>   // Greater than
<=  // Less than or equal to
>=  // Greater than or equal to
```

### Step 4: Switch Statements
Use switch for multiple conditions:

```java
switch (variable) {
    case value1:
        // code
        break;
    case value2:
        // code
        break;
    default:
        // code
}
```

## 💻 Exercises

### Exercise 1: Basic Conditionals
Create a program that:
- Asks for a number
- Checks if it's positive, negative, or zero
- Checks if it's even or odd

### Exercise 2: Logical Operators
Create a program that:
- Checks if a person can vote (age >= 18)
- Checks if a person can drive (age >= 16 && hasLicense)
- Checks if a person gets a discount (isStudent || isSenior)

### Exercise 3: Switch Statement
Create a program that:
- Asks for a day of the week
- Uses switch to determine if it's a weekday or weekend

## 🎮 Project: Treasure Island

### Requirements
Create an interactive text-based adventure game:
1. Display a welcome message
2. Ask user to make choices (left/right, swim/wait, door selection)
3. Use conditionals to determine outcomes
4. Display different endings based on choices

### Example Output
```
Welcome to Treasure Island.
Your mission is to find the treasure.

You're at a cross road. Where do you want to go? Type "left" or "right"
> left
You come to a lake. There is an island in the middle of the lake. Type "wait" to wait for a boat. Type "swim" to swim across.
> wait
You arrive at the island unharmed. There is a house with 3 doors. One red, one yellow and one blue. Which colour do you choose?
> yellow
You found the treasure! You Win!
```

### Starter Code
```java
import java.util.Scanner;

public class TreasureIsland {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Treasure Island.");
        System.out.println("Your mission is to find the treasure.");
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Use `scanner.nextLine()` to read user input
- Use `equals()` or `equalsIgnoreCase()` to compare strings
- Use nested if-else statements for multiple choices
- Check for valid input and handle invalid cases

## 📚 Resources

### Official Documentation
- [Java Control Flow - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)
- [Java Operators - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)
- [Switch Statement - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html)

### Tutorials
- [Java If-Else - W3Schools](https://www.w3schools.com/java/java_conditions.asp)
- [Java Switch - Programiz](https://www.programiz.com/java-programming/switch-statement)
- [Logical Operators - GeeksforGeeks](https://www.geeksforgeeks.org/java-logical-operators-with-examples/)

### Video Resources
- [Java Control Flow Tutorial](https://www.youtube.com/results?search_query=java+if+else+tutorial)
- [Java Switch Statement](https://www.youtube.com/results?search_query=java+switch+statement)

### Practice Platforms
- [Conditional Logic - CodingBat](https://codingbat.com/java/Logic-1)
- [Java Control Flow Exercises](https://www.w3resource.com/java-exercises/conditional-statement/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| If-Else | Conditional execution | `if (x > 0) { ... }` |
| Logical AND | Both conditions true | `if (a && b) { ... }` |
| Logical OR | At least one true | `if (a \|\| b) { ... }` |
| Logical NOT | Inverts condition | `if (!isFalse) { ... }` |
| Switch | Multiple value matching | `switch (value) { case 1: ... }` |

## ✅ Checklist
- [ ] Understand if-else statements
- [ ] Know all logical operators
- [ ] Can use comparison operators
- [ ] Understand switch statements
- [ ] Can create nested conditionals
- [ ] Completed Treasure Island project
- [ ] Committed code to Git

## 📂 Code Examples

This day includes starter and completed code examples:
- **`_start/`** - Starting code with TODO comments and learning notes
- **`_end/`** - Completed solutions with detailed explanations

Check these folders to see the progression from start to finish and learn from the learning curve notes!

## 🚀 Next Steps
After completing Day 3, you should be able to:
- Control program flow with conditionals
- Use logical operators effectively
- Build decision-making programs
- Create interactive games

**Ready for Day 4?** You'll learn about arrays and collections!







