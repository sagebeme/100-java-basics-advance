# Day 25 - Working with CSV Data

## 📚 Learning Objectives
- Read and parse CSV files
- Process CSV data
- Write CSV files
- Work with data analysis
- Create data-driven applications

## 🎯 Topics Covered
- CSV format
- CSV parsing
- Data processing
- CSV writing
- Data analysis basics
- Working with datasets

## 📝 Step-by-Step Instructions

### Step 1: Reading CSV Files
Parse CSV data:

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {
    public static ArrayList<String[]> readCSV(String filename) {
        ArrayList<String[]> data = new ArrayList<>();
        
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                data.add(values);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        
        return data;
    }
}
```

### Step 2: Processing CSV Data
Work with CSV data:

```java
// Skip header row
for (int i = 1; i < data.size(); i++) {
    String[] row = data.get(i);
    String name = row[0];
    int value = Integer.parseInt(row[1]);
    // Process data
}
```

### Step 3: Writing CSV Files
Write data to CSV:

```java
import java.io.FileWriter;
import java.io.IOException;

public static void writeCSV(String filename, ArrayList<String[]> data) {
    try {
        FileWriter writer = new FileWriter(filename);
        
        for (String[] row : data) {
            writer.write(String.join(",", row) + "\n");
        }
        
        writer.close();
    } catch (IOException e) {
        System.out.println("Error writing file!");
    }
}
```

### Step 4: Data Analysis
Analyze CSV data:

```java
// Count items
int count = data.size() - 1; // Exclude header

// Sum values
int total = 0;
for (int i = 1; i < data.size(); i++) {
    total += Integer.parseInt(data.get(i)[1]);
}

// Find maximum
int max = Integer.MIN_VALUE;
for (int i = 1; i < data.size(); i++) {
    int value = Integer.parseInt(data.get(i)[1]);
    if (value > max) max = value;
}
```

## 💻 Exercises

### Exercise 1: CSV Reading
Create programs that:
- Read CSV files
- Parse CSV data
- Handle different delimiters
- Skip header rows

### Exercise 2: Data Processing
Create programs that:
- Filter data
- Sort data
- Calculate statistics
- Transform data

### Exercise 3: CSV Writing
Create programs that:
- Write data to CSV
- Format CSV output
- Append to CSV files
- Create reports

## 🎮 Project: U.S. States Game

### Requirements
Create a U.S. States guessing game:
1. Load states data from CSV
2. Display map/image
3. Ask user to name states
4. Mark correct guesses on map
4. Track score
5. Save states to learn to CSV
6. Game ends when all states guessed

### Example CSV (50_states.csv)
```csv
state,x,y
Alabama,139,112
Alaska,204,51
Arizona,113,143
...
```

### Example Output
```
Guess a state's name: California
Correct! 1/50 states found.

Guess a state's name: Texas
Correct! 2/50 states found.

[Map with marked states]
```

### Starter Code
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatesGame {
    private Map<String, int[]> states;
    private ArrayList<String> guessedStates;
    
    public void loadStates() {
        // Read CSV and populate states map
    }
    
    public void playGame() {
        // Game loop
    }
}
```

## 📚 Resources

### Official Documentation
- [File I/O - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/io/)
- [String Methods - Java API](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)

### Tutorials
- [CSV Processing in Java](https://www.baeldung.com/java-csv)
- [Reading CSV Files](https://www.geeksforgeeks.org/reading-csv-file-java/)
- [CSV Libraries](https://www.baeldung.com/java-csv-file-array)

### Video Resources
- [Java CSV Tutorial](https://www.youtube.com/results?search_query=java+csv+tutorial)
- [CSV File Processing](https://www.youtube.com/results?search_query=java+read+csv+file)

### Practice Platforms
- [Data Processing Exercises](https://www.w3resource.com/java-exercises/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| CSV Format | Comma-separated values | `name,value,date` |
| Parsing | Extract data | `line.split(",")` |
| Data Processing | Analyze data | Count, sum, average |
| CSV Writing | Save to CSV | `String.join(",", values)` |
| Data Structure | Organize data | Map, List for CSV data |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Reading CSV:** Re-read **Step 1** — read line by line; `line.split(",")` for columns; skip or use header row — see **Step 2**
- **Parsing:** Handle quoted fields and commas inside quotes if needed; trim whitespace: `s.trim()`
- **Writing CSV:** Re-read **Step 2** — `String.join(",", values)` or build line manually — see **Step 2**
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for States Game logic

**Related days:** Day 24 (file read/write); Day 26 (streams for processing). **Quick reference:** `line.split(",", -1)` keeps trailing empty fields

## ✅ Checklist
- [ ] Can read CSV files
- [ ] Can parse CSV data
- [ ] Can process CSV data
- [ ] Can write CSV files
- [ ] Can analyze data
- [ ] Completed U.S. States Game
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
   cd "path\to\100-java-basics-advance\day25\_end\src"
   ```

3. **Compile** (if multiple files: `javac *.java`)
   ```cmd
   javac StatesGame.java
   ```

4. **Run the compiled program**
   ```cmd
   java StatesGame
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day25/_end/src
   ```

3. **Compile** (if multiple files: `javac *.java`)
   ```bash
   javac StatesGame.java
   ```

4. **Run the compiled program**
   ```bash
   java StatesGame
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'StatesGame.main()'"

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
- **CSV file not found** - Place your CSV file in the same directory or use the correct path

## 🚀 Next Steps
After completing Day 25, you should be able to:
- Work with CSV files
- Process data effectively
- Create data-driven applications
- Analyze datasets

**Ready for Day 26?** You'll learn about Streams and Lambda Expressions!







