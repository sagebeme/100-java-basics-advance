# Day 1 - The Harvest Log

> Your first Java program: write down what a coffee farmer picked today.

## 🌱 On the shamba

It's 4pm in Othaya, Nyeri. Wanjiru has been picking coffee since 7am. She carries her sacks of red cherries to the cooperative's weighing station, and the clerk writes one line in the book: who delivered, how much, and whether it was all ripe.

Today you'll write that line in Java. Your program asks three questions, stores each answer in a variable, and prints the log line.

```
=== Harvest Log ===
Farmer's name?
Wanjiru
Kilograms of cherry picked?
23.5
Were they all red and ripe? (yes/no)
yes
Wanjiru delivered 23.5 kg of cherry. All ripe: yes
```

## 🎯 By the end of today
- Declare variables of the four types you'll use most: `int`, `double`, `boolean` and `String`
- Read a line of input with `Scanner`
- Turn text into a number with `Double.parseDouble`
- Join text and values with `+`, and print them with `System.out.println`

## ☕ Bean fact

Coffee doesn't start as a brown bean. It grows on a bush as a fruit called a **cherry**, which turns red when it's ripe. Inside each cherry are usually two seeds, and those seeds are the coffee beans. In Kenya, most coffee is grown on small family farms, and the farmers take their cherries to a cooperative to be weighed and processed.

## 🧰 Java you'll use

**A variable** is a labelled box for one value. In Java you say what type of value goes in the box before you use it:

```java
String farmer = "Wanjiru";   // text
int trees = 300;             // a whole number
double kgPicked = 23.5;      // a number with a decimal point
boolean allRipe = true;      // true or false
```

**`Scanner`** reads what the user types. `nextLine()` always gives you text, so a number has to be converted:

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
String name = scanner.nextLine();
double kg = Double.parseDouble(scanner.nextLine());
```

**Joining text** works with `+`. Java turns numbers and booleans into text for you when you add them to a `String`:

```java
System.out.println(farmer + " picked " + kgPicked + " kg");
```

## 🪜 Steps

1. **Exercise 1: Farm profile** (`exercise1/`). Declare a variable for each fact about a farm and print them.
2. **Exercise 2: Scale readings** (`exercise2/`). Convert between text, `int` and `double`.
3. **Project: The Harvest Log** (`_start/src/HarvestLog.java`).
   1. Fill in `logLine` so it builds the sentence from its three parameters. Print `yes` or `no` for `allRipe`, not `true` or `false`.
   2. In `main`, ask the three questions and store each answer in a variable of the right type.
   3. A farmer types `yes` or `no`. Turn that into a `boolean` with `answer.equalsIgnoreCase("yes")`.
   4. Print the log line, then close the `Scanner`.

## 🧪 Taste test

Run the program:

```bash
cd day01/_start/src
javac HarvestLog.java
java HarvestLog
```

Check it with the tests. Copy `_end/src/HarvestLogTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day01/_start/src day01/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day01/_start/src --scan-classpath
```

Each exercise has its tests in its own `src/` folder, so run the same two commands with `day01/exercise1/src` instead.

## 🆘 Stuck?

- **`cannot find symbol`**: a variable is misspelled, or it's used before the line that declares it. Java is case-sensitive, so `kgpicked` and `kgPicked` are two different names.
- **`NumberFormatException`**: `Double.parseDouble` was given something that isn't a number, like `23,5` or an empty line. Use a dot for decimals.
- **The log says `true` instead of `yes`**: use `allRipe ? "yes" : "no"`, which picks one of two texts depending on the boolean.
- **Still stuck?** Read `_end/src/HarvestLog.java` once, close it, then write your own version from memory.

## ✅ Checklist
- [ ] Declared an `int`, a `double`, a `boolean` and a `String`
- [ ] Read input with `Scanner`
- [ ] Converted text to a number with `Double.parseDouble`
- [ ] Finished both exercises
- [ ] The Harvest Log prints the right line and its tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 2 follows the cherries into the factory: how many kilograms of cherry does it take to make one cup of coffee?
