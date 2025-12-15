# Day 12 - Scope and Namespacing

## 📚 Learning Objectives
- Understand variable scope
- Learn about local vs global scope
- Master block scope
- Understand namespacing
- Practice with access modifiers

## 🎯 Topics Covered
- Variable scope (local, instance, class)
- Block scope
- Namespace and naming
- Access modifiers (public, private, protected)
- Variable shadowing
- Best practices

## 📝 Step-by-Step Instructions

### Step 1: Understanding Scope
Scope determines where a variable is accessible:

```java
public class ScopeExample {
    // Class/static variable (class scope)
    static int classVariable = 10;
    
    // Instance variable (instance scope)
    int instanceVariable = 20;
    
    public void method() {
        // Local variable (method scope)
        int localVariable = 30;
        
        if (true) {
            // Block scope
            int blockVariable = 40;
            // blockVariable only accessible here
        }
        // blockVariable not accessible here
    }
}
```

### Step 2: Local Scope
Variables declared in methods:

```java
public void example() {
    int x = 5; // Local to this method
    if (true) {
        int y = 10; // Local to this block
        System.out.println(x); // Can access x
    }
    // System.out.println(y); // Error: y not in scope
}
```

### Step 3: Instance vs Class Variables
- Instance: Each object has its own copy
- Class/Static: Shared across all objects

```java
class Counter {
    static int count = 0; // Class variable
    int instanceCount = 0; // Instance variable
    
    void increment() {
        count++; // Affects all objects
        instanceCount++; // Affects only this object
    }
}
```

### Step 4: Access Modifiers
Control visibility:

```java
public int publicVar;      // Accessible everywhere
private int privateVar;    // Only in this class
protected int protectedVar; // In this class and subclasses
int packageVar;            // Package-private (default)
```

## 💻 Exercises

### Exercise 1: Scope Practice
Create programs that demonstrate:
- Local variable scope
- Block scope
- Instance variable scope
- Class variable scope
- Variable shadowing

### Exercise 2: Access Modifiers
Create classes with:
- Public methods and variables
- Private methods and variables
- Protected members
- Package-private members

### Exercise 3: Namespace
Practice with:
- Avoiding name conflicts
- Using meaningful names
- Following naming conventions
- Organizing code with packages

## 🎮 Project: Number Guessing Game

### Requirements
Create a number guessing game:
1. Choose difficulty (easy/hard)
2. Generate random number based on difficulty
3. Give appropriate number of attempts
4. Get user's guess
5. Provide feedback (too high/too low)
6. Track remaining attempts
7. Win if correct, lose if out of attempts
8. Ask to play again

### Example Output
```
Welcome to the Number Guessing Game!
I'm thinking of a number between 1 and 100.
Choose a difficulty. Type 'easy' or 'hard': hard
You have 5 attempts remaining to guess the number.
Make a guess: 50
Too high.
Guess again.
You have 4 attempts remaining to guess the number.
Make a guess: 25
Too low.
Guess again.
You have 3 attempts remaining to guess the number.
Make a guess: 37
You got it! The answer was 37.
```

### Starter Code
```java
import java.util.Random;
import java.util.Scanner;

public class NumberGuessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Use constants for difficulty levels
- Create method to generate random number
- Create method to check guess
- Use loop for attempts
- Track attempts with variable
- Use scope appropriately for variables

### Bonus Features
- Score tracking
- High score system
- Hints system
- Different number ranges
- Statistics (average guesses, win rate)

## 📚 Resources

### Official Documentation
- [Java Variables - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html)
- [Access Control - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)
- [Packages - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/package/index.html)

### Tutorials
- [Java Variable Scope - Programiz](https://www.programiz.com/java-programming/variables-primitive-data-types)
- [Access Modifiers - GeeksforGeeks](https://www.geeksforgeeks.org/access-modifiers-java/)
- [Java Naming Conventions](https://www.geeksforgeeks.org/java-naming-conventions/)

### Video Resources
- [Java Scope Tutorial](https://www.youtube.com/results?search_query=java+variable+scope)
- [Access Modifiers Explained](https://www.youtube.com/results?search_query=java+access+modifiers)

### Practice Platforms
- [Scope Exercises](https://codingbat.com/java)
- [Java Best Practices](https://google.github.io/styleguide/javaguide.html)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Local Scope | Method-level access | Variable in method |
| Block Scope | Block-level access | Variable in if/for block |
| Instance Scope | Object-level access | Non-static variable |
| Class Scope | Class-level access | Static variable |
| Access Modifier | Visibility control | `public`, `private`, `protected` |

## ✅ Checklist
- [ ] Understand variable scope
- [ ] Know local vs instance vs class scope
- [ ] Understand block scope
- [ ] Know access modifiers
- [ ] Can avoid scope issues
- [ ] Completed Number Guessing Game
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 12, you should be able to:
- Manage variable scope properly
- Use access modifiers effectively
- Avoid naming conflicts
- Write well-organized code

**Ready for Day 13?** You'll learn about debugging and exception handling!



