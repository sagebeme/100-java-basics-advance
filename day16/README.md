# Day 16 - Object-Oriented Programming (OOP) Basics

## 📚 Learning Objectives
- Understand OOP principles
- Learn about classes and objects
- Master constructors
- Understand instance variables and methods
- Practice encapsulation

## 🎯 Topics Covered
- Classes and objects
- Constructors
- Instance variables
- Instance methods
- Encapsulation
- Access modifiers
- Getters and setters

## 📝 Step-by-Step Instructions

### Step 1: Understanding Classes
Classes are blueprints for objects:

```java
public class Car {
    // Instance variables (attributes)
    private String brand;
    private String model;
    private int year;
    
    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    // Instance methods (behaviors)
    public void start() {
        System.out.println("Car is starting...");
    }
    
    public String getBrand() {
        return brand;
    }
}
```

### Step 2: Creating Objects
Instantiate objects from classes:

```java
Car myCar = new Car("Toyota", "Camry", 2023);
myCar.start();
System.out.println(myCar.getBrand());
```

### Step 3: Constructors
Initialize object state:

```java
// Default constructor
public Car() {
    this.brand = "Unknown";
    this.model = "Unknown";
    this.year = 2023;
}

// Parameterized constructor
public Car(String brand, String model, int year) {
    this.brand = brand;
    this.model = model;
    this.year = year;
}
```

### Step 4: Encapsulation
Protect data with access modifiers:

```java
private String brand; // Only accessible within class

public String getBrand() { // Public getter
    return brand;
}

public void setBrand(String brand) { // Public setter
    this.brand = brand;
}
```

## 💻 Exercises

### Exercise 1: Basic Class
Create a Person class with:
- Name, age, email attributes
- Constructor
- Getters and setters
- Display method

### Exercise 2: Bank Account
Create a BankAccount class with:
- Account number, balance
- Deposit and withdraw methods
- Balance validation
- Transaction history

### Exercise 3: Book Class
Create a Book class with:
- Title, author, pages
- Reading progress tracking
- Completion status

## 🎮 Project: Coffee Machine (OOP Version)

### Requirements
Refactor the Coffee Machine project using OOP:
1. Create CoffeeMachine class
2. Create Coffee class for different types
3. Create ResourceManager class
4. Use encapsulation properly
5. Organize code into multiple classes

### Starter Code Structure
```java
// CoffeeMachine.java
public class CoffeeMachine {
    private ResourceManager resources;
    private double money;
    
    public CoffeeMachine() {
        // Initialize resources
    }
    
    public void makeCoffee(Coffee coffee) {
        // Make coffee logic
    }
}

// Coffee.java
public class Coffee {
    private String name;
    private Map<String, Integer> ingredients;
    private double cost;
}

// ResourceManager.java
public class ResourceManager {
    private int water;
    private int milk;
    private int coffee;
}
```

## 📚 Resources

### Official Documentation
- [Java Classes - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/concepts/class.html)
- [Objects - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/concepts/object.html)
- [Constructors - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html)

### Tutorials
- [Java OOP - W3Schools](https://www.w3schools.com/java/java_oop.asp)
- [Java Classes and Objects - Programiz](https://www.programiz.com/java-programming/class-objects)
- [OOP Concepts - GeeksforGeeks](https://www.geeksforgeeks.org/object-oriented-programming-oops-concept-in-java/)

### Video Resources
- [Java OOP Tutorial](https://www.youtube.com/results?search_query=java+oop+tutorial)
- [Classes and Objects Explained](https://www.youtube.com/results?search_query=java+classes+and+objects)

### Practice Platforms
- [OOP Exercises](https://www.w3resource.com/java-exercises/class/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Class | Blueprint for objects | `public class Car { }` |
| Object | Instance of class | `Car myCar = new Car();` |
| Constructor | Initializes object | `public Car() { }` |
| Instance Variable | Belongs to object | `private String brand;` |
| Encapsulation | Data hiding | Private fields, public methods |

## ✅ Checklist
- [ ] Understand classes and objects
- [ ] Can create constructors
- [ ] Understand instance variables
- [ ] Know access modifiers
- [ ] Can implement encapsulation
- [ ] Completed OOP Coffee Machine
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 16, you should understand:
- OOP fundamentals
- Class design
- Object creation
- Encapsulation principles

**Ready for Day 17?** You'll build a Quiz project using OOP!



