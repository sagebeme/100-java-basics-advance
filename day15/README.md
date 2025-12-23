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

## Next Steps
After completing Day 15, you should understand:
- Basic OOP concepts
- Class design
- Method implementation
- State management
- User interaction patterns







