# Exercise 2: Type Conversion

## Objective
Practice converting between different data types including String, int, and double.

## Requirements
Convert between different types:
- String "123" to int
- int 456 to String
- double 3.14 to int (with casting)
- int 100 to double

## Instructions
1. Create a Java class called `TypeConversion`
2. Perform all required type conversions
3. Display the results
4. Verify conversions are correct

## Starter Code
```java
public class TypeConversion {
    public static void main(String[] args) {
        // TODO: Convert String "123" to int
        // String str = "123";
        // int num = ?
        
        // TODO: Convert int 456 to String
        // int number = 456;
        // String numStr = ?
        
        // TODO: Convert double 3.14 to int (with casting)
        // double decimal = 3.14;
        // int integer = ?
        
        // TODO: Convert int 100 to double
        // int value = 100;
        // double doubleValue = ?
        
        // TODO: Print all results
    }
}
```

## Expected Output
```
String "123" to int: 123
int 456 to String: 456
double 3.14 to int: 3
int 100 to double: 100.0
```

## Tips
- Use `Integer.parseInt()` for String to int
- Use `String.valueOf()` or `Integer.toString()` for int to String
- Use explicit casting `(int)` for double to int
- Implicit conversion works for int to double

## Solution Hints
- `int num = Integer.parseInt(str);`
- `String numStr = String.valueOf(number);`
- `int integer = (int) decimal;`
- `double doubleValue = value;` (implicit conversion)
