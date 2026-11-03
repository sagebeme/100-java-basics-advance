# Day 16 - Classes and Objects: The Coffee Machine, Rebuilt

> Yesterday's machine was one long class full of loose variables. Today each thing gets its own class: a drink, a set of tanks, and the machine that ties them together.

## 📖 The story

Njeri's office now has *two* coffee machines, one on each floor. With yesterday's code that's a problem: the water, milk and coffee were `static` fields, so there could only ever be one set of tanks. Today you rebuild the machine out of **objects**:

- a `Coffee` knows its own recipe and price;
- a `ResourceManager` owns the tanks, and is the only code allowed to change them;
- a `CoffeeMachine` holds a `ResourceManager` and the money, and talks to the customer.

From the outside it behaves exactly like Day 15:

```
What would you like? (espresso/latte/cappuccino):
espresso
Please insert coins.
How many quarters? 10
How many dimes? 0
How many nickels? 0
How many pennies? 0
Here is $1.00 in change.
Here is your espresso. Enjoy!
```

On the inside, `new CoffeeMachine()` twice gives you two machines with their own tanks.

## 🎯 Learning Objectives
- Write a class with private fields, a constructor and methods
- Create objects with `new`, and understand that each object has its own fields
- Use `this` to tell a field from a parameter with the same name
- Protect data with encapsulation: private fields, and public methods that keep them valid
- Split one big program into classes that each have one job

## 🧰 Java you'll use

**A class is a blueprint; an object is one thing built from it.**

```java
public class Coffee {
    private String name;
    private int water;
    private double cost;

    public Coffee(String name, int water, double cost) {   // the constructor
        this.name = name;      // this.name is the field, name is the parameter
        this.water = water;
        this.cost = cost;
    }

    public String getName() { return name; }               // a getter
}

Coffee latte = new Coffee("latte", 200, 2.50);
```

**Encapsulation.** `private` means only code inside the class can touch a field. The rest of the program asks the object to do things (`resources.useResources(50, 0, 18)`) instead of reaching in and changing numbers itself. That's how a class makes sure its data never goes wrong: there's only one door in, and it checks who comes through.

**Objects holding objects.** `CoffeeMachine` has a `ResourceManager` field. The machine doesn't know how the tanks work; it just asks them.

## 🪜 Steps

1. **[Exercise 1: A chama member card](exercise1/README.md)** (`exercise1/`): private fields, a constructor that refuses a bad phone number, getters, and a setter that guards its field.
2. **[Exercise 2: The sacco account](exercise2/README.md)** (`exercise2/`): deposits and withdrawals in whole shillings, a balance that can't go below zero, and a history nobody outside can change.
3. **Project: Coffee Machine, OOP version** (`_start/src/`).
   1. `Coffee`: name, water, milk, coffee and cost, set once in the constructor, with a getter for each.
   2. `ResourceManager`: the three tanks. `checkResources(water, milk, coffee)` says whether there's enough and names what's missing; `useResources` takes from the tanks; `addResources` tops them up; `displayReport` prints them.
   3. `CoffeeMachine`: a `ResourceManager` starting at 300ml, 200ml and 100g, the money, and the menu loop. `processPayment(cost)` returns `true` only if the coins covered the price, and `makeCoffee` uses that answer.

## 🧪 Run it and test it

Every class must be compiled together:

```bash
cd day16/_end/src
javac *.java
java CoffeeMachine
```

To test your own version, copy `_end/src/CoffeeTest.java` and `_end/src/ResourceManagerTest.java` into `_start/src`, then from the repo root:

```bash
javac -cp "lib/*" -d day16/_start/src day16/_start/src/*.java
java -cp "day16/_start/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

For an exercise, use `day16/exercise1/src` or `day16/exercise2/src` in both commands. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **`cannot find symbol: class Coffee`**: you compiled one file on its own. Use `javac *.java`.
- **A field is always 0 or `null`**: in the constructor you wrote `water = water;`, which sets the parameter to itself. Write `this.water = water;`.
- **The machine makes coffee after a refund**: decide from what `processPayment` returns, not from the total money in the machine. After a few sales, the total is always more than one drink's price.
- **Two machines share their tanks**: a field is still `static`. Remove `static` so each object gets its own.

## ✅ Checklist
- [ ] Wrote classes with private fields, constructors and getters
- [ ] Created objects with `new`, and saw each one keep its own fields
- [ ] Used `this` in a constructor
- [ ] Only `ResourceManager` changes the tanks
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 17 builds a quiz out of objects: a `Question` class, and a `QuizBrain` that asks them one by one.
