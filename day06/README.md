# Day 6 - Methods and Method Overloading

## 📚 Learning Objectives
- Understand methods (functions) in Java
- Learn method declaration and calling
- Master method parameters and return types
- Practice method overloading
- Understand scope and access modifiers

## 🎯 Topics Covered
- Method declaration
- Parameters and arguments
- Return types
- Method overloading
- Void methods
- Static methods
- Method scope

## 📝 Step-by-Step Instructions

### Step 1: Method Declaration
Methods are blocks of code that perform specific tasks:

```java
// Method syntax
accessModifier returnType methodName(parameters) {
    // method body
    return value; // if not void
}

// Example
public static int add(int a, int b) {
    return a + b;
}
```

### Step 2: Calling Methods
Invoke methods by their name:

```java
int result = add(5, 3); // result = 8
printMessage("Hello");  // void method call
```

### Step 3: Method Overloading
Multiple methods with same name but different parameters:

```java
public static int add(int a, int b) {
    return a + b;
}

public static double add(double a, double b) {
    return a + b;
}

public static int add(int a, int b, int c) {
    return a + b + c;
}
```

### Step 4: Void Methods
Methods that don't return a value:

```java
public static void printMessage(String message) {
    System.out.println(message);
}
```

### Step 5: Static vs Instance Methods
- Static: Belongs to class, called without object
- Instance: Belongs to object, called on object

```java
// Static method
Math.max(5, 10);

// Instance method (we'll learn more in OOP)
String name = "John";
name.length();
```

## 💻 Exercises

### Exercise 1: Basic Methods
Create methods for:
- Adding two numbers
- Subtracting two numbers
- Multiplying two numbers
- Dividing two numbers
- Finding maximum of two numbers

### Exercise 2: String Methods
Create methods for:
- Reversing a string
- Checking if string is palindrome
- Counting vowels in a string
- Converting string to title case

### Exercise 3: Method Overloading
Create overloaded methods:
- `calculateArea(int side)` - square
- `calculateArea(int length, int width)` - rectangle
- `calculateArea(double radius)` - circle

## 🎮 Project: Escaping the Maze (Karel-style)

### Requirements
Create a program that navigates through a maze:
1. Use methods to represent movements (move, turnLeft, turnRight)
2. Use methods to check conditions (isPathClear, isAtGoal)
3. Create algorithm to solve maze
4. Display progress

### Example Output
```
Starting maze navigation...
Moving forward...
Turning right...
Checking path...
Path clear, moving forward...
Reached the goal!
```

### Starter Code
```java
public class MazeSolver {
    
    public static void main(String[] args) {
        System.out.println("Starting maze navigation...");
        // Your code here
    }
    
    // Create your methods here
    // move(), turnLeft(), turnRight(), etc.
}
```

### Solution Hints
- Break down problem into smaller methods
- Use methods for each action (move, turn, check)
- Create a main navigation algorithm
- Use conditionals to make decisions
- Print status at each step

## 📚 Resources

### Official Documentation
- [Java Methods - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Method Overloading - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html#overloadingMethods)
- [Static Methods - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/classvars.html)

### Tutorials
- [Java Methods - W3Schools](https://www.w3schools.com/java/java_methods.asp)
- [Method Overloading - Programiz](https://www.programiz.com/java-programming/method-overloading)
- [Java Methods - GeeksforGeeks](https://www.geeksforgeeks.org/methods-in-java/)

### Video Resources
- [Java Methods Tutorial](https://www.youtube.com/results?search_query=java+methods+tutorial)
- [Method Overloading Explained](https://www.youtube.com/results?search_query=java+method+overloading)

### Practice Platforms
- [Method Practice - CodingBat](https://codingbat.com/java)
- [Java Method Exercises](https://www.w3resource.com/java-exercises/method/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Method | Block of reusable code | `public static int add(int a, int b)` |
| Parameter | Input to method | `(int a, int b)` |
| Return Type | Type of value returned | `int`, `String`, `void` |
| Method Call | Invoking a method | `add(5, 3)` |
| Overloading | Same name, different params | `add(int, int)` and `add(double, double)` |

## ✅ Checklist
- [ ] Understand method declaration
- [ ] Know how to call methods
- [ ] Understand parameters and return types
- [ ] Can create method overloads
- [ ] Understand void vs return methods
- [ ] Completed Maze Solver project
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
   cd "path\to\100-java-basics-advance\day06\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac Maze.java
   ```

4. **Run the compiled program**
   ```cmd
   java Maze
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day06/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac Maze.java
   ```

4. **Run the compiled program**
   ```bash
   java Maze
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
After completing Day 6, you should be able to:
- Create and use methods
- Organize code into reusable functions
- Use method overloading
- Build modular programs

**Ready for Day 7?** You'll build your first major game - Hangman!







