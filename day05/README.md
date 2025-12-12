# Day 5 - Loops (for, while, enhanced for)

## 📚 Learning Objectives
- Master for loops
- Understand while and do-while loops
- Learn enhanced for loops (for-each)
- Practice loop control (break, continue)
- Work with nested loops

## 🎯 Topics Covered
- For loops
- While loops
- Do-while loops
- Enhanced for loops
- Break and continue statements
- Nested loops
- Loop patterns

## 📝 Step-by-Step Instructions

### Step 1: For Loop
Use for loops when you know how many iterations:

```java
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}
```

### Step 2: While Loop
Use while loops when condition is checked first:

```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}
```

### Step 3: Do-While Loop
Use do-while when you need at least one execution:

```java
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 10);
```

### Step 4: Enhanced For Loop
Iterate over collections easily:

```java
int[] numbers = {1, 2, 3, 4, 5};
for (int num : numbers) {
    System.out.println(num);
}
```

### Step 5: Loop Control
Control loop execution:

```java
// Break - exit loop immediately
for (int i = 0; i < 10; i++) {
    if (i == 5) break;
    System.out.println(i);
}

// Continue - skip to next iteration
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) continue;
    System.out.println(i);
}
```

## 💻 Exercises

### Exercise 1: For Loop Practice
Create programs that:
- Print numbers 1 to 100
- Print even numbers from 2 to 50
- Print multiplication table for a number
- Calculate factorial of a number

### Exercise 2: While Loop Practice
Create programs that:
- Keep asking for input until valid
- Count down from 10 to 1
- Sum numbers until user enters 0
- Find first number divisible by 7 and 11

### Exercise 3: Nested Loops
Create programs that:
- Print a square pattern of asterisks
- Print a triangle pattern
- Print multiplication tables 1-10
- Find all prime numbers up to 100

## 🎮 Project: Password Generator

### Requirements
Create a password generator that:
1. Asks for number of letters
2. Asks for number of symbols
3. Asks for number of numbers
4. Generates a random password
5. Shuffles the password characters
6. Displays the password

### Example Output
```
Welcome to the Password Generator!
How many letters would you like in your password?
> 8
How many symbols would you like?
> 2
How many numbers would you like?
> 2
Your password is: aB3$kL9@mN2
```

### Starter Code
```java
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Create arrays/lists for letters, numbers, symbols
- Use loops to generate required characters
- Use Collections.shuffle() to randomize order
- Combine all characters into one list
- Convert list to string for output

## 📚 Resources

### Official Documentation
- [Java For Loop - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html)
- [Java While Loop - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/while.html)
- [Control Flow - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)

### Tutorials
- [Java Loops - W3Schools](https://www.w3schools.com/java/java_for_loop.asp)
- [Java While Loop - Programiz](https://www.programiz.com/java-programming/while-loop)
- [Nested Loops - GeeksforGeeks](https://www.geeksforgeeks.org/nested-loops-in-java/)

### Video Resources
- [Java Loops Tutorial](https://www.youtube.com/results?search_query=java+loops+tutorial)
- [Java Nested Loops](https://www.youtube.com/results?search_query=java+nested+loops)

### Practice Platforms
- [Loop Practice - CodingBat](https://codingbat.com/java/Logic-2)
- [Java Loop Exercises](https://www.w3resource.com/java-exercises/basic/index.php#forloops)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| For Loop | Iterate with counter | `for (int i = 0; i < 10; i++)` |
| While Loop | Iterate while condition true | `while (condition) { ... }` |
| Do-While | Execute at least once | `do { ... } while (condition);` |
| Enhanced For | Iterate over collections | `for (int x : array) { ... }` |
| Break | Exit loop | `break;` |
| Continue | Skip iteration | `continue;` |

## ✅ Checklist
- [ ] Understand for loops
- [ ] Know while and do-while loops
- [ ] Can use enhanced for loops
- [ ] Understand break and continue
- [ ] Can create nested loops
- [ ] Completed Password Generator project
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 5, you should be able to:
- Use all types of loops effectively
- Control loop execution
- Build programs with repetition
- Generate random data

**Ready for Day 6?** You'll learn about functions and methods!

