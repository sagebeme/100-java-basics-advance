# Exercise 1: String Manipulation

## Objective
Practice string manipulation methods in Java including case conversion, length calculation, and substring operations.

## Requirements
Create a program that:
- Takes a sentence as input
- Converts it to uppercase
- Converts it to lowercase
- Counts the number of characters
- Finds the first word

## Instructions
1. Create a Java class called `StringManipulation`
2. Use Scanner to get user input
3. Apply string methods to manipulate the input
4. Display all results

## Starter Code
```java
import java.util.Scanner;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        
        // TODO: Convert to uppercase
        // String upper = ?
        
        // TODO: Convert to lowercase
        // String lower = ?
        
        // TODO: Count characters
        // int length = ?
        
        // TODO: Find first word (use indexOf and substring)
        // String firstWord = ?
        
        // TODO: Print all results
        
        scanner.close();
    }
}
```

## Expected Output
```
Enter a sentence: Hello World Java
Uppercase: HELLO WORLD JAVA
Lowercase: hello world java
Character count: 15
First word: Hello
```

## Tips
- Use `toUpperCase()` and `toLowerCase()` methods
- Use `length()` to count characters
- Use `indexOf(" ")` to find first space, then `substring(0, index)` for first word

## Solution Hints
- `String upper = sentence.toUpperCase();`
- `String lower = sentence.toLowerCase();`
- `int length = sentence.length();`
- `String firstWord = sentence.substring(0, sentence.indexOf(" "));`
