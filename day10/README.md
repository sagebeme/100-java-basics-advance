# Day 10 - Function Return Values and Calculator Project

## 📚 Learning Objectives
- Master return statements
- Understand return types
- Learn about multiple return values
- Build a calculator with functions
- Practice method organization

## 🎯 Topics Covered
- Return types (int, double, String, void)
- Return statements
- Early returns
- Method chaining
- Organizing code with methods

## 📝 Step-by-Step Instructions

### Step 1: Return Types
Methods can return values:

```java
// Return int
public static int add(int a, int b) {
    return a + b;
}

// Return double
public static double divide(double a, double b) {
    return a / b;
}

// Return String
public static String greet(String name) {
    return "Hello, " + name;
}

// Return boolean
public static boolean isEven(int number) {
    return number % 2 == 0;
}

// No return (void)
public static void printMessage(String msg) {
    System.out.println(msg);
}
```

### Step 2: Early Returns
Return early to exit method:

```java
public static int divide(int a, int b) {
    if (b == 0) {
        System.out.println("Cannot divide by zero!");
        return 0; // Early return
    }
    return a / b;
}
```

### Step 3: Method Chaining
Use return values in other methods:

```java
int result = add(5, multiply(3, 4)); // 5 + 12 = 17
```

### Step 4: Organizing Calculator
Break calculator into methods:

```java
public static double add(double a, double b) { return a + b; }
public static double subtract(double a, double b) { return a - b; }
public static double multiply(double a, double b) { return a * b; }
public static double divide(double a, double b) { return a / b; }
```

## 💻 Exercises

### Exercise 1: Return Values
Create methods that return:
- Sum of two numbers
- Product of three numbers
- Largest of two numbers
- Formatted string
- Boolean check (is prime, is even, etc.)

### Exercise 2: Calculator Functions
Create methods for:
- Addition
- Subtraction
- Multiplication
- Division
- Modulo
- Power
- Square root

### Exercise 3: Complex Returns
Create methods that:
- Return multiple values (using array or class)
- Return formatted results
- Return error codes
- Return null for invalid input

## 🎮 Project: Calculator

### Requirements
Create a calculator program:
1. Display calculator logo
2. Get first number
3. Get operation (+, -, *, /)
4. Get second number
5. Perform calculation
6. Display result
7. Ask if user wants to continue
8. Clear previous calculation if continuing

### Example Output
```
 _____________________
|  _________________  |
| | Java Calculator | |
| |_________________| |
|  ___ ___ ___   ___  |
| | 7 | 8 | 9 | | + | |
| |___|___|___| |___| |
| | 4 | 5 | 6 | | - | |
| |___|___|___| |___| |
| | 1 | 2 | 3 | | * | |
| |___|___|___| |___| |
| | . | 0 | = | | / | |
| |___|___|___| |___| |

What's the first number?: 10
+
-
*
/
Pick an operation: +
What's the second number?: 5
10.0 + 5.0 = 15.0

Type 'y' to continue calculating with 15.0, or type 'n' to start a new calculation: y
Pick an operation: *
What's the next number?: 3
15.0 * 3.0 = 45.0
```

### Starter Code
```java
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Your code here
        
        scanner.close();
    }
    
    // Create calculation methods here
    // add, subtract, multiply, divide
}
```

### Solution Hints
- Create separate methods for each operation
- Use loop to continue calculations
- Store result for next calculation
- Handle division by zero
- Format output nicely

### Bonus Features
- Support for more operations (%, ^, sqrt)
- History of calculations
- Memory functions (M+, M-, MR, MC)
- Scientific calculator mode
- GUI version with JavaFX

## 📚 Resources

### Official Documentation
- [Java Methods - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Return Statement - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/branch.html)

### Tutorials
- [Java Return Values - Programiz](https://www.programiz.com/java-programming/methods)
- [Method Return Types - GeeksforGeeks](https://www.geeksforgeeks.org/methods-in-java/)

### Video Resources
- [Java Return Statements](https://www.youtube.com/results?search_query=java+return+statement)
- [Building a Calculator in Java](https://www.youtube.com/results?search_query=java+calculator+tutorial)

### Practice Platforms
- [Method Exercises](https://codingbat.com/java)
- [Calculator Projects](https://github.com/karan/Projects#calculator)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Return Type | Type of value returned | `int`, `double`, `String`, `void` |
| Return Statement | Exits method with value | `return result;` |
| Early Return | Exit before end | `if (error) return;` |
| Method Chaining | Use return in call | `add(5, multiply(2, 3))` |
| Code Organization | Break into methods | Separate add, subtract, etc. |

## ✅ Checklist
- [ ] Understand return types
- [ ] Can write methods that return values
- [ ] Know when to use void
- [ ] Can organize code with methods
- [ ] Can chain method calls
- [ ] Completed Calculator project
- [ ] Committed code to Git

## 📂 Code Examples

This day includes starter and completed code examples:
- **`_start/`** - Starting code with TODO comments and learning notes
- **`_end/`** - Completed solutions with detailed explanations

Check these folders to see the progression from start to finish and learn from the learning curve notes!

## 🚀 Next Steps
After completing Day 10, you should be able to:
- Create methods with return values
- Organize code into reusable functions
- Build complex programs with methods
- Design clean, modular code

**Ready for Day 11?** You'll build a Blackjack game - your first major capstone project!







