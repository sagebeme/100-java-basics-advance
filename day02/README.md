# Day 2 - Understanding Data Types and String Manipulation

## 📚 Learning Objectives
- Deep dive into Java data types
- Understand String manipulation methods
- Learn about type conversion
- Work with mathematical operations
- Practice string formatting

## 🎯 Topics Covered
- Primitive vs Reference types
- String class and its methods
- String concatenation
- Type conversion and casting
- Mathematical operations
- String formatting with printf
- StringBuilder for efficient string operations

## 📝 Step-by-Step Instructions

### Step 1: Review Data Types
Java has two categories of data types:
- **Primitive types**: int, double, boolean, char, byte, short, long, float
- **Reference types**: String, Arrays, Objects

### Step 2: String Methods
Strings in Java are objects with many useful methods:

```java
String text = "Hello World";
text.length();        // Returns 11
text.toUpperCase();   // "HELLO WORLD"
text.toLowerCase();   // "hello world"
text.substring(0, 5); // "Hello"
text.indexOf("World"); // 6
```

### Step 3: String Concatenation
Combine strings using the `+` operator:

```java
String firstName = "John";
String lastName = "Doe";
String fullName = firstName + " " + lastName; // "John Doe"
```

### Step 4: Type Conversion
Convert between types when needed:

```java
// String to int
String numStr = "123";
int num = Integer.parseInt(numStr);

// int to String
int number = 456;
String numString = String.valueOf(number);
```

## 💻 Exercises

### Exercise 1: String Manipulation
Create a program that:
- Takes a sentence as input
- Converts it to uppercase
- Converts it to lowercase
- Counts the number of characters
- Finds the first word

### Exercise 2: Type Conversion
Convert between different types:
- String "123" to int
- int 456 to String
- double 3.14 to int (with casting)
- int 100 to double

### Exercise 3: String Formatting
Use printf to format output:
- Format a number with 2 decimal places
- Format a string with padding
- Format multiple values

## 🎮 Project: Tip Calculator

### Requirements
Create a tip calculator that:
1. Asks for the total bill amount
2. Asks for the tip percentage (10, 12, or 15)
3. Asks for the number of people
4. Calculates the tip amount
5. Calculates total per person
6. Displays formatted results

### Example Output
```
Welcome to the tip calculator!
What was the total bill? $124.56
What percentage tip would you like to give? 10, 12, or 15? 12
How many people to split the bill? 7
Each person should pay: $19.93
```

### Starter Code
```java
import java.util.Scanner;

public class TipCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the tip calculator!");
        
        // Your code here
        
        scanner.close();
    }
}
```

### Solution Hints
- Use `scanner.nextDouble()` for decimal numbers
- Use `scanner.nextInt()` for whole numbers
- Calculate: tip = bill * (tipPercent / 100)
- Calculate: totalPerPerson = (bill + tip) / numberOfPeople
- Use `String.format()` or `printf()` for currency formatting

## 📚 Resources

### Official Documentation
- [Java String Class - Oracle Docs](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)
- [Java Primitive Types - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [StringBuilder Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html)

### Tutorials
- [Java Strings - W3Schools](https://www.w3schools.com/java/java_strings.asp)
- [Java String Methods - Programiz](https://www.programiz.com/java-programming/string)
- [Type Conversion in Java - GeeksforGeeks](https://www.geeksforgeeks.org/type-conversion-java-examples/)

### Video Resources
- [Java String Methods Tutorial](https://www.youtube.com/results?search_query=java+string+methods)
- [Java Type Conversion](https://www.youtube.com/results?search_query=java+type+conversion)

### Practice Platforms
- [String Practice - CodingBat](https://codingbat.com/java/String-1)
- [Java String Exercises](https://www.w3resource.com/java-exercises/string/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| String Methods | Built-in functions for strings | `text.length()`, `text.toUpperCase()` |
| Concatenation | Joining strings together | `"Hello" + " " + "World"` |
| Type Conversion | Changing one type to another | `Integer.parseInt("123")` |
| Formatting | Displaying values in specific format | `String.format("%.2f", 3.14159)` |
| StringBuilder | Efficient string building | `new StringBuilder().append("text")` |

## ✅ Checklist
- [ ] Understand primitive vs reference types
- [ ] Know common String methods
- [ ] Can perform type conversions
- [ ] Can format strings and numbers
- [ ] Completed Tip Calculator project
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 2, you should be able to:
- Manipulate strings effectively
- Convert between data types
- Format output nicely
- Build interactive console programs

**Ready for Day 3?** You'll learn about control flow and logical operators!

