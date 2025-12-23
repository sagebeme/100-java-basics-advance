# Day 15 - Local Development Setup & Coffee Machine Project

## Learning Objectives
- Set up a proper Java development environment
- Understand project structure
- Learn about classes and objects
- Build a complete console application
- Practice OOP concepts

## Topics Covered
- IDE setup (IntelliJ IDEA)
- Project structure
- Classes and objects
- Methods and encapsulation
- Console I/O

## Project: Coffee Machine

Build a coffee machine program that:
- Displays available coffee options
- Accepts user input for coffee selection
- Checks if resources are sufficient
- Processes payment
- Makes coffee and updates resources
- Provides reports on resources and money

### Requirements
- Three coffee types: Espresso, Latte, Cappuccino
- Resource management: water, milk, coffee, money
- Menu display
- Resource checking
- Payment processing
- Report generation

### Coffee Recipes
- **Espresso**: 50ml water, 18g coffee, $1.50
- **Latte**: 200ml water, 150ml milk, 24g coffee, $2.50
- **Cappuccino**: 250ml water, 100ml milk, 24g coffee, $3.00

### Features
- `buy` - Order a coffee
- `fill` - Add resources
- `take` - Take money from machine
- `remaining` - Show current resources
- `exit` - Turn off machine

## Code Structure
```
day15/
├── README.md
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── learning/
│                   ├── CoffeeMachine.java
│                   └── Main.java
└── pom.xml
```

## Key Concepts
- **Classes**: Blueprint for objects
- **Objects**: Instances of classes
- **Methods**: Functions within classes
- **Encapsulation**: Data hiding and access control
- **State Management**: Tracking object state

## Example Interaction
```
What would you like? (espresso/latte/cappuccino):
> latte
Please insert coins.
How many quarters?: 10
How many dimes?: 10
How many nickels?: 10
How many pennies?: 10
Here is $4.10 in change.
Here is your latte ☕. Enjoy!
```

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
   cd "path\to\100-java-basics-advance\day15\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac CoffeeMachine.java
   ```

4. **Run the compiled program**
   ```cmd
   java CoffeeMachine
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day15/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac CoffeeMachine.java
   ```

4. **Run the compiled program**
   ```bash
   java CoffeeMachine
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

## Next Steps
After completing Day 15, you should understand:
- Basic OOP concepts
- Class design
- Method implementation
- State management
- User interaction patterns







