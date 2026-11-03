# Day 15 - Coffee Machine: Your First Program With State

> Njeri's office bought a second-hand coffee machine from an American firm that was moving out. It still counts US coins. Today you write the program inside it.

## 📖 The story

On Day 14 Njeri learned to read a coffee menu. Now she's the one the office asks when the new machine misbehaves. It holds water, milk and coffee, makes three drinks, and takes quarters, dimes, nickels and pennies, because that's what it was built for:

```
What would you like? (espresso/latte/cappuccino):
Type 'report' to see resources or 'off' to turn off the machine.
latte
Please insert coins.
How many quarters? 10
How many dimes? 10
How many nickels? 10
How many pennies? 10
Here is your latte. Enjoy!
Here is $1.60 in change.
report
Water: 100ml
Milk: 50ml
Coffee: 76g
Money: $2.50
```

This is the first program in the course that **remembers** things between choices: every drink uses up water, milk and coffee, and the machine keeps the money. Ask for a second latte now and it says `Sorry, there is not enough water.`

It's also the start of the Intermediate stage, so it's the day to set up a proper editor (an IDE) instead of a plain text editor.

## 🎯 Learning Objectives
- Set up an IDE (IntelliJ IDEA, VS Code or Eclipse) and run a program from it
- Keep a program's **state** in variables that live as long as the program
- Split a program into small methods, each with one job
- Check that there's enough of everything before changing anything
- Handle money as decimals carefully, and format it with `String.format`

## 🧰 Java you'll use

**State that outlives a method.** Variables declared inside `main` disappear when `main` ends, and other methods can't see them. Fields at the top of the class are shared by every method:

```java
public class CoffeeMachine {
    private static int water = 300;   // ml
    private static int milk = 200;    // ml
    private static int coffee = 100;  // g
    private static double money = 0;
}
```

They're `static` today because there's only one machine. On Day 16 you'll see what changes when there could be many.

**Check first, then change.** `checkResources` only answers a question; it changes nothing. Only after it says yes, and the coins are enough, do you subtract the water, milk and coffee.

**The recipes:**

| Drink | Water | Milk | Coffee | Price |
|---|---|---|---|---|
| Espresso | 50ml | 0 | 18g | $1.50 |
| Latte | 200ml | 150ml | 24g | $2.50 |
| Cappuccino | 250ml | 100ml | 24g | $3.00 |

**Coins:** a quarter is $0.25, a dime $0.10, a nickel $0.05 and a penny $0.01. `String.format("%.2f", change)` prints `1.60`, not `1.5999999999999996`.

## 🪜 Steps

**Project: Coffee Machine** (`_start/src/CoffeeMachine.java`). The starter is an empty `main`: today you design the program yourself.

1. Add the four fields for water, milk, coffee and money, starting at 300ml, 200ml, 100g and $0.
2. `printReport()`: print each resource, and the money with two decimal places.
3. `checkResources(water, milk, coffee)`: return `false`, with a message naming what's missing, if any one runs short. The tests call it by this name.
4. `makeCoffee(drink, water, milk, coffee, cost)`: check resources, ask for coins, then either make the drink and give change, or refund.
5. `main`: loop until `off`, handling `report` and the three drinks, and saying `Invalid choice!` for anything else.

## 🧪 Run it and test it

```bash
cd day15/_end/src
javac CoffeeMachine.java
java CoffeeMachine
```

For your own version, use `_start/src`. To run the tests against it, copy `_end/src/CoffeeMachineTest.java` next to your file, then from the repo root:

```bash
javac -cp "lib/*" -d day15/_start/src day15/_start/src/*.java
java -cp "day15/_start/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **Resources never go down**: you're changing a local variable with the same name as the field. Inside `makeCoffee`, name the parameters differently (`waterNeeded`) or subtract from the field.
- **The machine took the money but gave no coffee**: check resources *before* asking for coins.
- **Change prints as `1.5999999999999996`**: format it with `String.format("%.2f", change)`.
- **`Could not find or load main class`**: run `java CoffeeMachine` from the folder that holds `CoffeeMachine.class`.

## ✅ Checklist
- [ ] Set up an IDE and ran a program from it
- [ ] The machine remembers resources and money between drinks
- [ ] Refuses a drink, saying what's missing, when a resource runs short
- [ ] Gives change, and refunds when the coins aren't enough
- [ ] `report` and `off` work
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 16 rebuilds the same machine with objects: a `Coffee` class for each drink, and a `ResourceManager` that guards the tanks.
