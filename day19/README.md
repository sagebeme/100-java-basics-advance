# Day 19 - Inheritance and Polymorphism

## 📚 Learning Objectives
- Understand inheritance
- Learn about superclass and subclass
- Master method overriding
- Understand polymorphism
- Practice with abstract classes

## 🎯 Topics Covered
- Inheritance (extends keyword)
- Superclass and subclass
- Method overriding
- super keyword
- Polymorphism
- Abstract classes
- Method overloading vs overriding

## 📝 Step-by-Step Instructions

### Step 1: Inheritance Basics
Create subclasses from superclasses:

```java
// Superclass
public class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
}

// Subclass
public class Dog extends Animal {
    public Dog(String name) {
        super(name); // Call superclass constructor
    }
    
    public void bark() {
        System.out.println(name + " is barking");
    }
}
```

### Step 2: Method Overriding
Override superclass methods:

```java
public class Animal {
    public void makeSound() {
        System.out.println("Some sound");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}
```

### Step 3: Polymorphism
Use superclass reference for subclass objects:

```java
Animal myDog = new Dog("Buddy");
Animal myCat = new Cat("Whiskers");

myDog.makeSound(); // Calls Dog's makeSound
myCat.makeSound(); // Calls Cat's makeSound
```

### Step 4: super Keyword
Access superclass members:

```java
public class Dog extends Animal {
    public void display() {
        super.eat(); // Call superclass method
        this.bark(); // Call subclass method
    }
}
```

## 💻 Exercises

### Exercise 1: Basic Inheritance
Create class hierarchies:
- Animal -> Dog, Cat, Bird
- Vehicle -> Car, Motorcycle, Truck
- Shape -> Circle, Rectangle, Triangle

### Exercise 2: Method Overriding
Override methods in subclasses:
- Override toString()
- Override equals()
- Override superclass methods

### Exercise 3: Polymorphism
Practice polymorphism:
- Use superclass references
- Call overridden methods
- Use instanceof operator

## 🎮 Project: Turtle Race Game

### Requirements
Create a turtle race game using inheritance:
1. Create Animal superclass
2. Create Turtle subclass
3. Create multiple turtle instances
4. Race turtles to finish line
5. Display winner
6. Use polymorphism for turtle management

### Example Output
```
Welcome to the Turtle Race!

Turtle 1: [=====>        ]
Turtle 2: [======>       ]
Turtle 3: [====>         ]
Turtle 4: [=======>      ]
Turtle 5: [====>         ]

Turtle 2 wins! 🏆
```

### Starter Code
```java
public class Animal {
    protected String name;
    protected int position;
    
    public Animal(String name) {
        this.name = name;
        this.position = 0;
    }
    
    public void move() {
        // Base movement logic
    }
}

public class Turtle extends Animal {
    public Turtle(String name) {
        super(name);
    }
    
    @Override
    public void move() {
        // Turtle-specific movement
        position += new Random().nextInt(3) + 1;
    }
}
```

## 📚 Resources

### Official Documentation
- [Inheritance - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)
- [Polymorphism - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html)
- [Overriding Methods - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/IandI/override.html)

### Tutorials
- [Java Inheritance - W3Schools](https://www.w3schools.com/java/java_inheritance.asp)
- [Java Polymorphism - Programiz](https://www.programiz.com/java-programming/polymorphism)
- [Inheritance in Java - GeeksforGeeks](https://www.geeksforgeeks.org/inheritance-in-java/)

### Video Resources
- [Java Inheritance Tutorial](https://www.youtube.com/results?search_query=java+inheritance+tutorial)
- [Polymorphism Explained](https://www.youtube.com/results?search_query=java+polymorphism)

### Practice Platforms
- [OOP Exercises](https://www.w3resource.com/java-exercises/class/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Inheritance | Subclass inherits from superclass | `class Dog extends Animal` |
| Override | Replace superclass method | `@Override public void method()` |
| super | Access superclass | `super.method()` |
| Polymorphism | Same interface, different behavior | `Animal a = new Dog()` |
| Abstract Class | Cannot instantiate | `abstract class Shape` |

## ✅ Checklist
- [ ] Understand inheritance
- [ ] Can create subclasses
- [ ] Can override methods
- [ ] Understand polymorphism
- [ ] Can use super keyword
- [ ] Completed Turtle Race
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
   cd "path\to\100-java-basics-advance\day19\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac TurtleRace.java
   ```

4. **Run the compiled program**
   ```cmd
   java TurtleRace
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day19/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac TurtleRace.java
   ```

4. **Run the compiled program**
   ```bash
   java TurtleRace
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'ClassName.main()'"

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
- **"package does not exist"** - Remove package declaration if running from command line, or use proper package structure


## 🚀 Next Steps
After completing Day 19, you should understand:
- Inheritance relationships
- Method overriding
- Polymorphism concepts
- Code reuse through inheritance

**Ready for Day 20?** You'll start building the Snake Game!







