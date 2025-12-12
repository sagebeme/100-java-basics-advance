# Exercise 2: File Writing

## Objective
Practice writing to files in Java, including creating new files, appending to existing files, and formatting output.

## Requirements
Create programs that:
- Write data to files
- Append to existing files
- Format output
- Create multiple files

## Instructions
1. Create a Java class called `FileWriter`
2. Write data to a new file
3. Append data to an existing file
4. Format the output nicely
5. Create multiple files with different content

## Starter Code
```java
import java.io.FileWriter;
import java.io.IOException;

public class FileWriter {
    public static void main(String[] args) {
        // TODO: Write to a new file
        try {
            FileWriter writer = new FileWriter("output.txt");
            writer.write("Hello, World!\n");
            writer.write("This is line 2.\n");
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("Error writing file!");
        }
        
        // TODO: Append to existing file
        try {
            FileWriter appendWriter = new FileWriter("output.txt", true);
            appendWriter.write("This is appended text.\n");
            appendWriter.close();
            System.out.println("Text appended successfully!");
        } catch (IOException e) {
            System.out.println("Error appending to file!");
        }
        
        // TODO: Create multiple files with formatted output
        // Create files: data1.txt, data2.txt, data3.txt
    }
}
```

## Expected Output
```
File written successfully!
Text appended successfully!
Created 3 files successfully!
```

## Tips
- Use `FileWriter` for writing
- Use `FileWriter(filename, true)` for appending
- Use `\n` for new lines
- Format output with `String.format()` if needed

## Solution Hints
- `FileWriter writer = new FileWriter("output.txt");`
- `FileWriter appendWriter = new FileWriter("output.txt", true);`
- `writer.write(String.format("Line %d: %s\n", i, data));`
