# Day 3 - Cherry Sorter

> Not every cherry makes the cut. Write the rules that decide which ones do.

## 🌱 On the shamba

At the factory, before anything else happens, the cherries are sorted. A clerk looks at the colour, drops them in water and checks for tiny holes. One bad handful can spoil the taste of a whole batch, so the rules are strict.

Today's program asks three questions about a cherry and gives the clerk's verdict:

```
=== Cherry Sorter ===
What colour is the cherry? (red, green, yellow, black...)
red
Did it float in the water? (yes/no)
no
Does it have small holes? (yes/no)
no
ACCEPT
```

The rules, in order:

| Check | Verdict |
|---|---|
| It has small holes | `REJECT: insect damage` |
| It floats | `REJECT: floater` |
| It's red | `ACCEPT` |
| It's green or yellow | `REJECT: unripe` |
| It's black or brown | `REJECT: overripe` |
| Anything else | `CHECK BY HAND` |

## 🎯 By the end of today
- Make decisions with `if`, `else if` and `else`
- Combine conditions with `&&` (and), `||` (or) and `!` (not)
- Compare text with `equals` and `equalsIgnoreCase`, not `==`
- Order your checks so the most important rule wins

## ☕ Bean fact

A healthy, ripe coffee cherry is dense, so it **sinks** in water. One that **floats** is usually underdeveloped, dried out or eaten from the inside. Factories in Kenya float-sort their cherries for this reason. The tiny holes come from the **coffee berry borer**, a beetle about the size of a sesame seed that lays its eggs inside the bean.

## 🧰 Java you'll use

**`if` / `else if` / `else`** runs the first block whose condition is true and skips the rest:

```java
if (kg > 50) {
    System.out.println("Big delivery");
} else if (kg > 10) {
    System.out.println("Normal delivery");
} else {
    System.out.println("Small delivery");
}
```

**Logical operators** combine `boolean`s:

```java
boolean open = hour >= 7 && hour < 17;  // both must be true
boolean notRipe = colour.equals("green") || colour.equals("yellow"); // either one
boolean dry = !raining;                  // the opposite
```

**Comparing text**: always use `equals` (or `equalsIgnoreCase` to ignore capitals). `==` checks whether two variables point at the same object in memory, not whether the text is the same, so it can say `false` for two identical words.

```java
colour.equalsIgnoreCase("RED") // true for "red", "Red" and "RED"
```

## 🪜 Steps

1. **Exercise 1: Is the station open?** (`exercise1/`). Combine conditions with `&&`, `||` and `!`.
2. **Exercise 2: Altitude check** (`exercise2/`). A chain of `else if`s.
3. **Project: Cherry Sorter** (`_start/src/CherrySorter.java`).
   1. Fill in `verdict` using the rules table above, checked **in that order**. A red cherry with holes is still rejected.
   2. Ignore capitals and spaces in the colour, so `" Red "` counts as red.
   3. In `main`, ask the three questions, turn the yes/no answers into `boolean`s, and print the verdict.

## 🧪 Taste test

```bash
cd day03/_start/src
javac CherrySorter.java
java CherrySorter
```

To run the tests, copy `_end/src/CherrySorterTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day03/_start/src day03/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day03/_start/src --scan-classpath
```

For an exercise, use `day03/exercise1/src` or `day03/exercise2/src` in both commands.

## 🆘 Stuck?

- **A damaged red cherry gets `ACCEPT`**: the colour check comes before the damage checks. Move the damage checks to the top.
- **`"Red"` isn't treated as red**: use `equalsIgnoreCase`, and `trim()` the input first.
- **`bad operand types for binary operator '&&'`**: both sides of `&&` must be `boolean`s. `hour >= 7 && < 17` doesn't work; write `hour >= 7 && hour < 17`.
- **Every path needs a `return`**: end the chain with a plain `else` (or a final `return`), so Java knows something is always returned.

## ✅ Checklist
- [ ] Wrote an `if` / `else if` / `else` chain
- [ ] Combined conditions with `&&`, `||` and `!`
- [ ] Compared text with `equalsIgnoreCase`
- [ ] Finished both exercises
- [ ] Cherry Sorter's tests pass
- [ ] Committed code to Git

## ➡️ Next

Cherries need rain. On Day 4 you'll store a whole week of rainfall in one variable: an array.
