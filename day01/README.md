# Day 1 - Working with Variables in Java to Manage Data

## 📚 Learning Objectives
- Understand Java variables and data types
- Learn how to declare and initialize variables
- Practice variable naming conventions
- Work with primitive data types (int, double, boolean, char)
- Understand type conversion and casting
- Use Scanner for user input

## 🎯 Topics Covered
- Variable declaration and initialization
- Primitive data types (int, double, boolean, char, byte, short, long, float)
- Reference types (String)
- Naming conventions
- Type conversion and casting
- Scanner class for input
- System.out.println() for output

## 📝 Step-by-Step Instructions

### Step 1: Set Up Your Development Environment
1. Create a new Java project in your IDE (IntelliJ IDEA, Eclipse, or VS Code)
2. Create a package: `com.learning.day01`
3. Create a class: `BandNameGenerator.java`

### Step 2: Understand Variables
Variables are containers that store data values. In Java, you must declare the type before using a variable.

```java
// Syntax: dataType variableName = value;
int age = 25;
String name = "John";
double price = 19.99;
```

### Step 3: Practice Variable Declarations
Try declaring different types of variables:
- Integer (int)
- Double (double)
- Boolean (boolean)
- Character (char)
- String (String)

### Step 4: Learn About Scanner
Scanner is used to read user input from the console.

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
String input = scanner.nextLine();
```

## 💻 Exercises

### Exercise 1: Variable Declaration
Create variables for:
- Your age (int)
- Your height in meters (double)
- Your name (String)
- Whether you like Java (boolean)

### Exercise 2: Type Conversion
Practice converting between types:
- Convert int to double
- Convert double to int (casting)
- Convert String to int (Integer.parseInt())

### Exercise 3: User Input
Create a program that asks for:
- Your name
- Your favorite color
- Your age
Then prints them back

## 🎮 Project: Band Name Generator

### Requirements
Create a program that generates a band name based on user input:
1. Ask user for their city name
2. Ask user for their pet's name
3. Combine them to create a band name
4. Display the result

### Example Output
```
Welcome to the Band Name Generator!
What city did you grow up in?
Boston
What's your pet's name?
Max
Your band name could be: Boston Max
```

### Starter Code
```java
import java.util.Scanner;

public class BandNameGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Band Name Generator!");
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Use `scanner.nextLine()` to read strings
- Use string concatenation with `+` operator
- Print the result with `System.out.println()`

## 📚 Resources

### Official Documentation
- [Java Variables - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html)
- [Java Data Types - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [Scanner Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html)

### Tutorials
- [Java Variables Tutorial - W3Schools](https://www.w3schools.com/java/java_variables.asp)
- [Java User Input - Programiz](https://www.programiz.com/java-programming/basic-input-output)
- [Java Data Types - GeeksforGeeks](https://www.geeksforgeeks.org/data-types-in-java/)

### Video Resources
- [Java Variables Explained - YouTube](https://www.youtube.com/results?search_query=java+variables+tutorial)
- [Java Scanner Tutorial - YouTube](https://www.youtube.com/results?search_query=java+scanner+tutorial)

### Practice Platforms
- [Java Exercises - W3Schools](https://www.w3schools.com/java/java_exercises.asp)
- [CodingBat Java](https://codingbat.com/java)
- [HackerRank Java](https://www.hackerrank.com/domains/java)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Variable | Container for storing data | `int age = 25;` |
| Data Type | Defines what kind of data | `int`, `double`, `String` |
| Declaration | Creating a variable | `String name;` |
| Initialization | Giving a variable a value | `name = "John";` |
| Scanner | Reads user input | `Scanner scanner = new Scanner(System.in);` |

## ✅ Checklist
- [ ] Understand what variables are
- [ ] Know the 8 primitive data types
- [ ] Can declare and initialize variables
- [ ] Can read user input with Scanner
- [ ] Can print output to console
- [ ] Completed Band Name Generator project
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
   cd "path\to\100-java-basics-advance\day01\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac BandNameGenerator.java
   ```
   *Note: This day has multiple files. You can compile all at once: `javac *.java`*

4. **Run the compiled program**
   ```cmd
   java BandNameGenerator
   ```

**Example for Day 1:**
```cmd
cd "C:\Users\YourName\projects\100-java-basics-advance\day01\_end\src"
javac BandNameGenerator.java
java BandNameGenerator
```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day01/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac BandNameGenerator.java
   ```
   *Note: This day has multiple files. You can compile all at once: `javac *.java`*

4. **Run the compiled program**
   ```bash
   java BandNameGenerator
   ```

**Example for Day 1:**
```bash
cd ~/projects/100-java-basics-advance/day01/_end/src
javac BandNameGenerator.java
java BandNameGenerator
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
After completing Day 1, you should be comfortable with:
- Declaring and initializing variables
- Reading user input
- Displaying output
- Basic data type operations

**Ready for Day 2?** You'll learn about data types manipulation and string methods!
