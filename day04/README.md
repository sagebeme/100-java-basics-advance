# Day 4 - Arrays and Collections

## 📚 Learning Objectives
- Understand arrays in Java
- Learn about ArrayList and Collections
- Work with array indexing and manipulation
- Practice array operations (add, remove, search)
- Understand the difference between arrays and ArrayLists

## 🎯 Topics Covered
- Array declaration and initialization
- Array indexing and access
- ArrayList class
- Collections framework basics
- Array methods and operations
- Multidimensional arrays

## 📝 Step-by-Step Instructions

### Step 1: Arrays
Arrays are fixed-size collections of elements:

```java
// Array declaration
int[] numbers = new int[5];
String[] names = {"Alice", "Bob", "Charlie"};

// Accessing elements
int first = numbers[0];
names[1] = "Robert";
```

### Step 2: ArrayList
ArrayList is a dynamic array that can grow/shrink:

```java
import java.util.ArrayList;

ArrayList<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.remove(0);
names.get(0); // "Bob"
names.size(); // Returns size
```

### Step 3: Array Operations
Common operations on arrays:

```java
// Length
int length = array.length;

// Iteration
for (int i = 0; i < array.length; i++) {
    System.out.println(array[i]);
}

// Enhanced for loop
for (String name : names) {
    System.out.println(name);
}
```

### Step 4: Collections Methods
Useful ArrayList methods:

```java
list.add(element);        // Add element
list.remove(index);       // Remove by index
list.get(index);          // Get element
list.size();              // Get size
list.contains(element);   // Check if contains
list.indexOf(element);    // Find index
```

## 💻 Exercises

### Exercise 1: Array Basics
Create a program that:
- Creates an array of 5 numbers
- Fills it with user input
- Finds the maximum value
- Finds the minimum value
- Calculates the average

### Exercise 2: ArrayList Operations
Create a program that:
- Creates an ArrayList of names
- Adds names from user input
- Removes a name by index
- Searches for a name
- Displays all names

### Exercise 3: Array Manipulation
Create a program that:
- Reverses an array
- Finds duplicates
- Merges two arrays
- Sorts an array

## 🎮 Project: Rock Paper Scissors

### Requirements
Create a Rock Paper Scissors game:
1. Display game rules
2. Get user choice (rock, paper, or scissors)
3. Generate random computer choice
4. Compare choices and determine winner
5. Keep track of score
6. Ask if user wants to play again

### Example Output
```
Welcome to Rock Paper Scissors!
What do you choose? Type 0 for Rock, 1 for Paper, or 2 for Scissors
> 1
You chose: Paper
Computer chose: Rock
You win!
Your score: 1, Computer score: 0
Do you want to play again? (yes/no)
> yes
```

### Starter Code
```java
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Use `Random` class for computer choice
- Use arrays to store choices: `String[] choices = {"Rock", "Paper", "Scissors"};`
- Create a method to determine winner
- Use a loop for multiple rounds
- Track scores with variables

## 📚 Resources

### Official Documentation
- [Java Arrays - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [ArrayList Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [Collections Framework - Oracle Docs](https://docs.oracle.com/javase/tutorial/collections/)

### Tutorials
- [Java Arrays - W3Schools](https://www.w3schools.com/java/java_arrays.asp)
- [Java ArrayList - Programiz](https://www.programiz.com/java-programming/arraylist)
- [Collections in Java - GeeksforGeeks](https://www.geeksforgeeks.org/collections-in-java-2/)

### Video Resources
- [Java Arrays Tutorial](https://www.youtube.com/results?search_query=java+arrays+tutorial)
- [Java ArrayList Tutorial](https://www.youtube.com/results?search_query=java+arraylist+tutorial)

### Practice Platforms
- [Array Practice - CodingBat](https://codingbat.com/java/Array-1)
- [Java Array Exercises](https://www.w3resource.com/java-exercises/array/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Array | Fixed-size collection | `int[] arr = new int[5];` |
| ArrayList | Dynamic array | `ArrayList<String> list = new ArrayList<>();` |
| Index | Position in array | `arr[0]` (first element) |
| Length | Size of array | `arr.length` |
| Enhanced For | Iterate over elements | `for (int x : arr) { ... }` |

## ✅ Checklist
- [ ] Understand array declaration
- [ ] Know how to access array elements
- [ ] Can use ArrayList
- [ ] Understand Collections basics
- [ ] Can iterate over arrays
- [ ] Completed Rock Paper Scissors project
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
   cd "path\to\100-java-basics-advance\day04\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac RockPaperScissors.java
   ```

4. **Run the compiled program**
   ```cmd
   java RockPaperScissors
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day04/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac RockPaperScissors.java
   ```

4. **Run the compiled program**
   ```bash
   java RockPaperScissors
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
After completing Day 4, you should be able to:
- Work with arrays and ArrayLists
- Manipulate collections
- Build games with random elements
- Handle multiple data items

**Ready for Day 5?** You'll learn about loops in detail!







