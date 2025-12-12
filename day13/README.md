# Day 13 - Debugging: How to Find and Fix Errors

## 📚 Learning Objectives
- Understand common Java errors
- Learn debugging techniques
- Master exception handling
- Use IDE debugging tools
- Practice error prevention

## 🎯 Topics Covered
- Common error types (syntax, runtime, logical)
- Exception handling (try-catch)
- Debugging strategies
- IDE debugging features
- Error messages interpretation
- Best practices

## 📝 Step-by-Step Instructions

### Step 1: Understanding Error Types
Java has different error categories:

```java
// Syntax Error - Won't compile
int x =  // Missing value

// Runtime Error - Compiles but crashes
int[] arr = new int[5];
int value = arr[10]; // ArrayIndexOutOfBoundsException

// Logical Error - Runs but wrong result
int average = (a + b) / 2; // Should be (a + b) / 2.0 for double
```

### Step 2: Exception Handling
Handle errors gracefully:

```java
try {
    // Code that might throw exception
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // Handle division by zero
    System.out.println("Cannot divide by zero!");
} catch (Exception e) {
    // Handle any other exception
    System.out.println("An error occurred: " + e.getMessage());
} finally {
    // Always executes
    System.out.println("Cleanup code");
}
```

### Step 3: Common Exceptions
Learn about common exceptions:

```java
// NullPointerException
String str = null;
int length = str.length(); // Error!

// ArrayIndexOutOfBoundsException
int[] arr = {1, 2, 3};
int value = arr[5]; // Error!

// NumberFormatException
int num = Integer.parseInt("abc"); // Error!

// InputMismatchException
Scanner scanner = new Scanner(System.in);
int num = scanner.nextInt(); // Error if user enters text!
```

### Step 4: Debugging Techniques
Effective debugging strategies:

```java
// 1. Print statements
System.out.println("Debug: value = " + value);

// 2. Use IDE debugger
// Set breakpoints
// Step through code
// Inspect variables

// 3. Read error messages carefully
// Line numbers
// Exception type
// Stack trace

// 4. Test incrementally
// Test small parts
// Verify assumptions
```

## 💻 Exercises

### Exercise 1: Error Identification
Identify errors in code:
- Syntax errors
- Runtime errors
- Logical errors
- Fix each error

### Exercise 2: Exception Handling
Create programs with:
- Try-catch blocks
- Multiple catch blocks
- Finally blocks
- Custom error messages

### Exercise 3: Debugging Practice
Practice debugging:
- Use print statements
- Use IDE debugger
- Read stack traces
- Fix common errors

## 🎮 Project: Debugging Challenge

### Requirements
Create a program with intentional bugs, then:
1. Identify all bugs
2. Fix syntax errors
3. Add exception handling
4. Fix logical errors
5. Test thoroughly
6. Document fixes

### Buggy Code Example
```java
public class BuggyProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter two numbers:");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        
        int result = a / b;
        System.out.println("Result: " + result);
        
        int[] numbers = {1, 2, 3};
        System.out.println(numbers[5]);
        
        String text = null;
        System.out.println(text.length());
    }
}
```

### Fixed Version
Add proper error handling and fix bugs!

## 📚 Resources

### Official Documentation
- [Exceptions - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Exception Hierarchy - Java API](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html)
- [Common Exceptions - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html)

### Tutorials
- [Java Exception Handling - W3Schools](https://www.w3schools.com/java/java_try_catch.asp)
- [Debugging in Java - GeeksforGeeks](https://www.geeksforgeeks.org/debugging-java-programs/)
- [Common Java Errors](https://www.programiz.com/java-programming/exceptions)

### Video Resources
- [Java Exception Handling Tutorial](https://www.youtube.com/results?search_query=java+exception+handling)
- [Debugging in IntelliJ IDEA](https://www.youtube.com/results?search_query=intellij+debugger+tutorial)
- [Common Java Errors](https://www.youtube.com/results?search_query=common+java+errors)

### Practice Platforms
- [Error Fixing Exercises](https://codingbat.com/java)
- [Exception Handling Practice](https://www.w3resource.com/java-exercises/exception-handling/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Syntax Error | Compile-time error | Missing semicolon |
| Runtime Error | Exception during execution | Division by zero |
| Logical Error | Wrong result | Incorrect formula |
| Try-Catch | Handle exceptions | `try { ... } catch { ... }` |
| Finally | Always executes | Cleanup code |
| Stack Trace | Error location info | Line numbers, method calls |

## ✅ Checklist
- [ ] Understand error types
- [ ] Can read error messages
- [ ] Can use try-catch blocks
- [ ] Know common exceptions
- [ ] Can use IDE debugger
- [ ] Can fix common errors
- [ ] Completed debugging exercises
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 13, you should be able to:
- Identify and fix errors
- Handle exceptions properly
- Use debugging tools
- Write robust code

**Ready for Day 14?** You'll build the Higher Lower game!

