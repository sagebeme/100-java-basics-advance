# Day 26 - Streams and Lambda Expressions

## 📚 Learning Objectives
- Understand Java Streams API
- Learn lambda expressions
- Master functional programming concepts
- Work with method references
- Practice stream operations

## 🎯 Topics Covered
- Lambda expressions syntax
- Stream API
- Intermediate operations (map, filter, sorted)
- Terminal operations (collect, forEach, reduce)
- Method references
- Functional interfaces

## 📝 Step-by-Step Instructions

### Step 1: Lambda Expressions
Anonymous functions:

```java
// Old way (anonymous class)
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});

// Lambda way
Collections.sort(names, (a, b) -> a.compareTo(b));
```

### Step 2: Stream API Basics
Work with collections:

```java
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.List;

List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Create stream
Stream<String> stream = names.stream();

// Filter
names.stream()
    .filter(name -> name.startsWith("A"))
    .forEach(System.out::println);

// Map (transform)
names.stream()
    .map(String::toUpperCase)
    .forEach(System.out::println);
```

### Step 3: Common Stream Operations
Useful stream methods:

```java
// Filter
list.stream().filter(x -> x > 10)

// Map
list.stream().map(x -> x * 2)

// Sorted
list.stream().sorted()

// Collect
list.stream().collect(Collectors.toList())

// Reduce
list.stream().reduce(0, (a, b) -> a + b)
```

### Step 4: Method References
Shorthand for lambdas:

```java
// Instead of: x -> x.toUpperCase()
String::toUpperCase

// Instead of: x -> System.out.println(x)
System.out::println

// Instead of: () -> new ArrayList<>()
ArrayList::new
```

## 💻 Exercises

### Exercise 1: Lambda Basics
Create lambdas for:
- Sorting collections
- Filtering lists
- Transforming data
- Event handlers

### Exercise 2: Stream Operations
Practice with:
- Filter operations
- Map operations
- Reduce operations
- Collect operations

### Exercise 3: Method References
Use method references for:
- Static methods
- Instance methods
- Constructors
- Arbitrary object methods

## 🎮 Project: NATO Phonetic Alphabet

### Requirements
Create a program that:
1. Reads words from user
2. Converts each letter to NATO phonetic alphabet
3. Uses streams and lambdas
4. Displays phonetic spelling

### Example Output
```
Enter a word: Hello
H for Hotel
E for Echo
L for Lima
L for Lima
O for Oscar
```

### Starter Code
```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NATOPhonetic {
    private static final Map<Character, String> natoAlphabet = new HashMap<>();
    
    static {
        natoAlphabet.put('A', "Alpha");
        natoAlphabet.put('B', "Bravo");
        // Add all letters
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = scanner.nextLine().toUpperCase();
        
        // Use streams to convert
        word.chars()
            .mapToObj(c -> (char) c)
            .map(c -> c + " for " + natoAlphabet.get(c))
            .forEach(System.out::println);
    }
}
```

## 📚 Resources

### Official Documentation
- [Lambda Expressions - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Stream API - Oracle Docs](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
- [Method References - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)

### Tutorials
- [Java Lambda Expressions - W3Schools](https://www.w3schools.com/java/java_lambda.asp)
- [Java Streams - Baeldung](https://www.baeldung.com/java-8-streams-introduction)
- [Lambda Expressions - GeeksforGeeks](https://www.geeksforgeeks.org/lambda-expressions-java-8/)

### Video Resources
- [Java Lambda Tutorial](https://www.youtube.com/results?search_query=java+lambda+expressions+tutorial)
- [Java Streams Tutorial](https://www.youtube.com/results?search_query=java+streams+tutorial)

### Practice Platforms
- [Stream Exercises](https://www.w3resource.com/java-exercises/stream/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Lambda | Anonymous function | `(x, y) -> x + y` |
| Stream | Sequence of elements | `list.stream()` |
| Filter | Select elements | `.filter(x -> x > 10)` |
| Map | Transform elements | `.map(x -> x * 2)` |
| Method Reference | Shorthand lambda | `String::toUpperCase` |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Lambda:** Re-read **Step 1** — `(param) -> expression` or `(a, b) -> a + b` — see **Step 2**
- **Stream:** Re-read **Step 2** — `list.stream()` then `.filter()`, `.map()`, `.collect()` — one terminal op per stream
- **Method reference:** Use `ClassName::methodName` or `object::methodName` when lambda just calls one method
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for NATO Phonetic with streams

**Related days:** Day 4 (ArrayList to stream); Day 25 (CSV processing with streams). **Quick reference:** Terminal ops: `collect()`, `forEach()`, `reduce()` · Intermediate: `filter()`, `map()`, `sorted()`

## ✅ Checklist
- [ ] Understand lambda syntax
- [ ] Can use streams
- [ ] Know common stream operations
- [ ] Can use method references
- [ ] Understand functional programming
- [ ] Completed NATO Phonetic project
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
   cd "path\to\100-java-basics-advance\day26\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac NATOPhonetic.java
   ```

4. **Run the compiled program**
   ```cmd
   java NATOPhonetic
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day26/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac NATOPhonetic.java
   ```

4. **Run the compiled program**
   ```bash
   java NATOPhonetic
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'NATOPhonetic.main()'"

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

## 🚀 Next Steps
After completing Day 26, you should be able to:
- Write lambda expressions
- Use streams effectively
- Apply functional programming
- Process collections efficiently

**Ready for Day 27?** You'll learn about JavaFX GUI programming!







