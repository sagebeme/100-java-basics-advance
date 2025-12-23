# Day 8 - Function Parameters & Caesar Cipher

## 📚 Learning Objectives
- Deep dive into method parameters
- Understand pass-by-value vs pass-by-reference
- Learn about variable arguments (varargs)
- Build encryption/decryption program
- Practice character manipulation

## 🎯 Topics Covered
- Method parameters in detail
- Pass-by-value concept
- Character encoding (ASCII)
- String manipulation
- Encryption algorithms
- Modular code design

## 📝 Step-by-Step Instructions

### Step 1: Understanding Parameters
Parameters allow methods to accept input:

```java
// Single parameter
public static void greet(String name) {
    System.out.println("Hello, " + name);
}

// Multiple parameters
public static int add(int a, int b, int c) {
    return a + b + c;
}

// Variable arguments (varargs)
public static int sum(int... numbers) {
    int total = 0;
    for (int num : numbers) {
        total += num;
    }
    return total;
}
```

### Step 2: Pass-by-Value
Java passes primitives by value (copy):

```java
public static void modify(int x) {
    x = 100; // Doesn't affect original
}

int num = 5;
modify(num);
System.out.println(num); // Still 5
```

### Step 3: Character Operations
Work with characters and ASCII:

```java
char letter = 'A';
int ascii = (int) letter; // 65
char shifted = (char) (letter + 3); // 'D'
```

### Step 4: Caesar Cipher Logic
Shift each letter by a fixed number:

```java
// Encryption: shift forward
char encrypted = (char) ((letter - 'A' + shift) % 26 + 'A');

// Decryption: shift backward
char decrypted = (char) ((letter - 'A' - shift + 26) % 26 + 'A');
```

## 💻 Exercises

### Exercise 1: Parameter Practice
Create methods that:
- Accept multiple parameters
- Use different parameter types
- Return calculated values
- Handle edge cases

### Exercise 2: Character Manipulation
Create methods for:
- Converting case (upper/lower)
- Shifting characters
- Checking if character is letter
- Getting character position in alphabet

### Exercise 3: String Processing
Create methods that:
- Process strings character by character
- Apply transformations
- Validate input
- Format output

## 🎮 Project: Caesar Cipher

### Requirements
Create a Caesar Cipher program that:
1. Asks user to encode or decode
2. Asks for the message
3. Asks for the shift number
4. Encrypts or decrypts the message
5. Displays the result

### Example Output
```
Type 'encode' to encrypt, type 'decode' to decrypt:
> encode
Type your message:
> hello
Type the shift number:
> 5
The encoded text is: mjqqt

Type 'encode' to encrypt, type 'decode' to decrypt:
> decode
Type your message:
> mjqqt
Type the shift number:
> 5
The decoded text is: hello
```

### Starter Code
```java
import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Type 'encode' to encrypt, type 'decode' to decrypt:");
        String direction = scanner.nextLine();
        
        // Your code here
        
        scanner.close();
    }
    
    // Create encode and decode methods
}
```

### Solution Hints
- Create separate methods for encode and decode
- Handle both uppercase and lowercase letters
- Keep spaces and punctuation unchanged
- Use modulo to wrap around alphabet
- Validate user input

### Bonus Features
- Support for numbers and special characters
- Multiple shift options
- File encryption/decryption
- Brute force decryption
- Frequency analysis

## 📚 Resources

### Official Documentation
- [Java Methods - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Character Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html)
- [String Methods - Java API](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)

### Tutorials
- [Java Method Parameters - Programiz](https://www.programiz.com/java-programming/methods)
- [Caesar Cipher Algorithm](https://www.geeksforgeeks.org/caesar-cipher-in-cryptography/)
- [Character Encoding in Java](https://www.baeldung.com/java-char-encoding)

### Video Resources
- [Java Method Parameters Tutorial](https://www.youtube.com/results?search_query=java+method+parameters)
- [Caesar Cipher Implementation](https://www.youtube.com/results?search_query=caesar+cipher+java)

### Practice Platforms
- [Method Exercises](https://codingbat.com/java)
- [Cryptography Basics](https://www.khanacademy.org/computing/computer-science/cryptography)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Parameter | Input to method | `method(int param)` |
| Pass-by-Value | Copy of value passed | Primitive types |
| ASCII | Character encoding | `'A' = 65` |
| Character Shift | Moving in alphabet | `'A' + 3 = 'D'` |
| Modulo | Wrap around | `(x + shift) % 26` |

## ✅ Checklist
- [ ] Understand method parameters deeply
- [ ] Know pass-by-value concept
- [ ] Can manipulate characters
- [ ] Understand ASCII encoding
- [ ] Can implement encryption algorithm
- [ ] Completed Caesar Cipher project
- [ ] Committed code to Git

## 📂 Code Examples

This day includes starter and completed code examples:
- **`_start/`** - Starting code with TODO comments and learning notes
- **`_end/`** - Completed solutions with detailed explanations

Check these folders to see the progression from start to finish and learn from the learning curve notes!

## 🚀 Next Steps
After completing Day 8, you should be able to:
- Work with method parameters effectively
- Manipulate characters and strings
- Implement encryption algorithms
- Build secure communication tools

**Ready for Day 9?** You'll learn about dictionaries (Maps) and build a Secret Auction!







