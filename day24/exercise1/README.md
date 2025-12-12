# Exercise 1: File Reading

## Objective
Practice reading from files in Java, processing file content, and performing operations on file data.

## Requirements
Create programs that:
- Read text files line by line
- Count lines and words
- Search for specific text
- Process file content

## Instructions
1. Create a text file named `sample.txt` with some content
2. Create a Java class called `FileReader`
3. Read the file line by line
4. Count total lines
5. Count total words
6. Search for a specific word
7. Display statistics

## Starter Code
```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static void main(String[] args) {
        try {
            File file = new File("sample.txt");
            Scanner scanner = new Scanner(file);
            
            int lineCount = 0;
            int wordCount = 0;
            String searchWord = "Java";
            int wordOccurrences = 0;
            
            // TODO: Read file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                
                // TODO: Count words in line
                // Split line by spaces and count
                
                // TODO: Search for specific word
                // Check if line contains searchWord
            }
            
            // TODO: Print statistics
            // System.out.println("Total lines: " + lineCount);
            // System.out.println("Total words: " + wordCount);
            // System.out.println("Occurrences of '" + searchWord + "': " + wordOccurrences);
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
```

## Expected Output
```
Total lines: 5
Total words: 25
Occurrences of 'Java': 3
```

## Tips
- Use `hasNextLine()` and `nextLine()` to read lines
- Use `split(" ")` to split line into words
- Use `contains()` or `indexOf()` to search for text
- Handle FileNotFoundException

## Solution Hints
- `while (scanner.hasNextLine()) { String line = scanner.nextLine(); }`
- `String[] words = line.split("\\s+"); wordCount += words.length;`
- `if (line.toLowerCase().contains(searchWord.toLowerCase())) { wordOccurrences++; }`
