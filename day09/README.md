# Day 9 - Maps, Sets, and Collections

## 📚 Learning Objectives
- Understand HashMap and Map interface
- Learn about HashSet and Set interface
- Work with key-value pairs
- Practice nested data structures
- Build programs using collections

## 🎯 Topics Covered
- HashMap and Map interface
- HashSet and Set interface
- Key-value pairs
- Nested collections
- Collection methods
- Iterating over maps

## 📝 Step-by-Step Instructions

### Step 1: HashMap Basics
Maps store key-value pairs:

```java
import java.util.HashMap;
import java.util.Map;

// Create HashMap
Map<String, Integer> scores = new HashMap<>();

// Add elements
scores.put("Alice", 95);
scores.put("Bob", 87);
scores.put("Charlie", 92);

// Access elements
int aliceScore = scores.get("Alice");

// Check if key exists
if (scores.containsKey("Bob")) {
    // Key exists
}

// Remove element
scores.remove("Charlie");
```

### Step 2: Iterating Over Maps
Different ways to iterate:

```java
// Iterate over keys
for (String key : scores.keySet()) {
    System.out.println(key + ": " + scores.get(key));
}

// Iterate over entries
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// Iterate over values
for (Integer value : scores.values()) {
    System.out.println(value);
}
```

### Step 3: Nested Collections
Collections within collections:

```java
Map<String, List<String>> countries = new HashMap<>();
countries.put("USA", Arrays.asList("New York", "Los Angeles"));
countries.put("UK", Arrays.asList("London", "Manchester"));
```

### Step 4: HashSet
Set stores unique elements:

```java
import java.util.HashSet;
import java.util.Set;

Set<String> uniqueNames = new HashSet<>();
uniqueNames.add("Alice");
uniqueNames.add("Bob");
uniqueNames.add("Alice"); // Won't add duplicate

// Check membership
if (uniqueNames.contains("Alice")) {
    // Element exists
}
```

## 💻 Exercises

### Exercise 1: HashMap Practice
Create programs that:
- Store and retrieve key-value pairs
- Count occurrences of words
- Build a phone directory
- Create a student grade book

### Exercise 2: Set Operations
Create programs that:
- Find unique elements
- Perform set operations (union, intersection)
- Remove duplicates from list
- Check membership efficiently

### Exercise 3: Nested Structures
Create programs that:
- Store lists in maps
- Store maps in lists
- Build complex data structures
- Navigate nested collections

## 🎮 Project: Secret Auction

### Requirements
Create a secret auction program:
1. Display auction logo
2. Ask for bidder's name
3. Ask for bid amount
4. Ask if there are more bidders
5. Clear screen for next bidder
6. Find highest bidder
7. Display winner

### Example Output
```
                         ___________
                         \\         /
                          )_______(
                          |"""""""|_.-._,.---------.,_.-._
                          |       | | |               | | ''-.
                          |       |_| |_             |||  _.-'
                          |_______| '-' `'---------'`''-'`'-..__
                          )"""""""(
                         /_________\\
                       .-------------.
                      /_______________\\

Welcome to the secret auction program.
What is your name?: Alice
What's your bid?: $150
Are there any other bidders? Type 'yes' or 'no'.
> yes
[Clear screen]

What is your name?: Bob
What's your bid?: $200
Are there any other bidders? Type 'yes' or 'no'.
> no

The winner is Bob with a bid of $200
```

### Starter Code
```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SecretAuction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> bids = new HashMap<>();
        
        System.out.println("Welcome to the secret auction program.");
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Use HashMap to store name-bid pairs
- Use loop to collect multiple bids
- Clear screen between bidders (print many newlines or use ANSI codes)
- Find maximum value in map
- Get key for maximum value

### Bonus Features
- Input validation
- Minimum bid requirement
- Bid history display
- Timer for auction
- Multiple item auctions

## 📚 Resources

### Official Documentation
- [Map Interface - Oracle Docs](https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html)
- [HashMap Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
- [Set Interface - Oracle Docs](https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html)
- [HashSet Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html)

### Tutorials
- [Java HashMap - W3Schools](https://www.w3schools.com/java/java_hashmap.asp)
- [Java Map Interface - Programiz](https://www.programiz.com/java-programming/map)
- [Java Collections - GeeksforGeeks](https://www.geeksforgeeks.org/collections-in-java-2/)

### Video Resources
- [Java HashMap Tutorial](https://www.youtube.com/results?search_query=java+hashmap+tutorial)
- [Java Collections Framework](https://www.youtube.com/results?search_query=java+collections+framework)

### Practice Platforms
- [Map Practice - CodingBat](https://codingbat.com/java)
- [Java Collection Exercises](https://www.w3resource.com/java-exercises/collection/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Map | Key-value pairs | `Map<String, Integer>` |
| HashMap | Map implementation | `new HashMap<>()` |
| Key | Unique identifier | `"Alice"` in `scores.get("Alice")` |
| Value | Data associated with key | `95` in `scores.put("Alice", 95)` |
| Set | Unique elements collection | `Set<String> unique = new HashSet<>()` |

## ✅ Checklist
- [ ] Understand HashMap and Map interface
- [ ] Can add, get, remove from maps
- [ ] Can iterate over maps
- [ ] Understand HashSet and Set
- [ ] Can work with nested collections
- [ ] Completed Secret Auction project
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
   cd "path\to\100-java-basics-advance\day09\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac SecretAuction.java
   ```

4. **Run the compiled program**
   ```cmd
   java SecretAuction
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day09/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac SecretAuction.java
   ```

4. **Run the compiled program**
   ```bash
   java SecretAuction
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
After completing Day 9, you should be able to:
- Use maps for key-value storage
- Work with sets for unique data
- Build complex data structures
- Organize data efficiently

**Ready for Day 10?** You'll learn about return values and build a Calculator!







