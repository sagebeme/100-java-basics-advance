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

## ✅ Checklist
- [ ] Can read from files
- [ ] Can write to files
- [ ] Understand file paths
- [ ] Can work with directories
- [ ] Can handle file exceptions
- [ ] Completed Mail Merge project
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 24, you should be able to:
- Work with files effectively
- Handle file operations
- Process file data
- Create file-based applications

**Ready for Day 25?** You'll learn about working with CSV data!

