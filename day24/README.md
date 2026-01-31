# Day 24 - File I/O and Paths

## 📚 Learning Objectives
- Read and write files
- Understand file paths
- Work with directories
- Handle file operations
- Practice with different file formats

## 🎯 Topics Covered
- File reading and writing
- Path and Paths classes
- File and FileReader/FileWriter
- BufferedReader/BufferedWriter
- Scanner for file reading
- Exception handling for files

## 📝 Step-by-Step Instructions

### Step 1: Reading Files
Read text files:

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

try {
    File file = new File("data.txt");
    Scanner scanner = new Scanner(file);
    
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println(line);
    }
    scanner.close();
} catch (FileNotFoundException e) {
    System.out.println("File not found!");
}
```

### Step 2: Writing Files
Write to files:

```java
import java.io.FileWriter;
import java.io.IOException;

try {
    FileWriter writer = new FileWriter("output.txt");
    writer.write("Hello, World!\n");
    writer.write("This is a new line.");
    writer.close();
} catch (IOException e) {
    System.out.println("Error writing file!");
}
```

### Step 3: Working with Paths
Use Path and Paths:

```java
import java.nio.file.Path;
import java.nio.file.Paths;

Path path = Paths.get("folder", "subfolder", "file.txt");
String absolutePath = path.toAbsolutePath().toString();
String fileName = path.getFileName().toString();
```

### Step 4: Directory Operations
Work with directories:

```java
import java.io.File;

File directory = new File("myFolder");
if (!directory.exists()) {
    directory.mkdir(); // Create directory
}

File[] files = directory.listFiles();
for (File file : files) {
    System.out.println(file.getName());
}
```

## 💻 Exercises

### Exercise 1: File Reading
Create programs that:
- Read text files line by line
- Count lines and words
- Search for specific text
- Process file content

### Exercise 2: File Writing
Create programs that:
- Write data to files
- Append to existing files
- Format output
- Create multiple files

### Exercise 3: File Operations
Create programs that:
- List directory contents
- Create directories
- Copy files
- Delete files

## 🎮 Project: Mail Merge

### Requirements
Create a mail merge program:
1. Read names from a file
2. Read letter template from file
3. Replace placeholder with each name
4. Create personalized letters
5. Save each letter to separate file

### Example Files
**names.txt:**
```
Alice
Bob
Charlie
```

**letter_template.txt:**
```
Dear [NAME],

You are invited to our event!

Best regards,
Event Team
```

### Example Output
Creates files: `letter_Alice.txt`, `letter_Bob.txt`, `letter_Charlie.txt`

### Starter Code
```java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MailMerge {
    public static void main(String[] args) {
        // Read names
        // Read template
        // Create personalized letters
        // Save to files
    }
}
```

## 📚 Resources

### Official Documentation
- [File I/O - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/io/)
- [Path Interface - Java API](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)
- [File Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)

### Tutorials
- [Java File I/O - W3Schools](https://www.w3schools.com/java/java_files.asp)
- [File Reading/Writing - Programiz](https://www.programiz.com/java-programming/file-operations)
- [Java NIO - GeeksforGeeks](https://www.geeksforgeeks.org/java-nio-file/)

### Video Resources
- [Java File I/O Tutorial](https://www.youtube.com/results?search_query=java+file+io+tutorial)
- [Reading and Writing Files](https://www.youtube.com/results?search_query=java+read+write+files)

### Practice Platforms
- [File I/O Exercises](https://www.w3resource.com/java-exercises/io/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| File Reading | Read from file | `Scanner scanner = new Scanner(file)` |
| File Writing | Write to file | `FileWriter writer = new FileWriter()` |
| Path | File location | `Paths.get("folder", "file.txt")` |
| Exception Handling | Handle file errors | `try-catch` blocks |
| Directory | Folder operations | `File.mkdir()`, `listFiles()` |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Reading:** Re-read **Step 1** — `Scanner scanner = new Scanner(new File("path"));` then `hasNextLine()` / `nextLine()` — close when done
- **Writing:** Re-read **Step 2** — `FileWriter writer = new FileWriter("path");` then `writer.write(str);` — close when done
- **File not found:** Path is relative to current working directory; use try-catch for `FileNotFoundException`
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for Mail Merge flow

**Related days:** Day 13 (try-catch); Day 25 (CSV = read line, split); Day 30 (JSON).

**Quick reference:** `Paths.get("a","b","file.txt")` · `file.exists()` before read · Always close Scanner/Writer in finally or try-with-resources

## ✅ Checklist
- [ ] Can read from files
- [ ] Can write to files
- [ ] Understand file paths
- [ ] Can work with directories
- [ ] Can handle file exceptions
- [ ] Completed Mail Merge project
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
   cd "path\to\100-java-basics-advance\day24\_end\src"
   ```

3. **Compile** (if multiple files: `javac *.java`)
   ```cmd
   javac MailMerge.java
   ```

4. **Run the compiled program**
   ```cmd
   java MailMerge
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day24/_end/src
   ```

3. **Compile** (if multiple files: `javac *.java`)
   ```bash
   javac MailMerge.java
   ```

4. **Run the compiled program**
   ```bash
   java MailMerge
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'MailMerge.main()'"

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
- **"File not found"** - Run from the directory that contains your data files, or use absolute paths

## 🚀 Next Steps
After completing Day 24, you should be able to:
- Work with files effectively
- Handle file operations
- Process file data
- Create file-based applications

**Ready for Day 25?** You'll learn about working with CSV data!







