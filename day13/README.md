# Day 13 - Fix the Weighbridge

> The mill's weighbridge program crashes, and when it doesn't crash it gives the wrong numbers. Find the bugs like a professional.

## 🌱 On the shamba

Lorries carry the dried parchment from the factories to the mill. Each lorry drives onto the **weighbridge**, a giant scale set into the road, once full on the way in and once empty on the way out. The difference is the coffee, and it decides what every factory, and so every farmer, is paid.

The mill's program has three bugs, and the manager has noticed:

- The program **crashes** before printing the total.
- The **average per lorry** is always a round number, even though the weights aren't.
- Typing `Othaya` for a factory total gives **0.0 kg**, though Othaya sent two lorries.

There's no new project to build today. Your job is to find and fix the bugs in `_start/src/Weighbridge.java`, and the real lesson is *how* you find them.

## 🎯 By the end of today
- Read a stack trace and jump straight to the line that failed
- Use a debugger: set a breakpoint, step line by line, and watch variables change
- Use a well-placed `println` when a debugger isn't handy
- Recognise four classic Java bugs: off-by-one, `==` on `String`s, a hidden `int`, and `null`
- Write down what you *expected* before checking what actually happened

## ☕ Bean fact

A loaded lorry weighs many tonnes, so a weighbridge reading that's off by even 1% can be more than a hundred kilos of coffee. In Kenya, weighing equipment used in trade has to be checked and certified by the government's Weights and Measures department.

## 🧰 Java you'll use

**Reading a stack trace.** Run the program as it is and you get this:

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
	at Weighbridge.totalNet(Weighbridge.java:14)
	at Weighbridge.main(Weighbridge.java:48)
```

Read it from the top:

1. **What went wrong**: `ArrayIndexOutOfBoundsException`, meaning something asked for index `3` of an array whose last index is `2`.
2. **Where**: the first `at` line is where it happened, line 14 in `totalNet`.
3. **How it got there**: each line below is the method that called the one above. `main` called `totalNet` on line 48.

Always start with the first line that's in *your* code.

**Using a debugger.** In IntelliJ IDEA or VS Code:

1. Click in the margin next to a line number to set a **breakpoint**: a red dot.
2. Start the program with **Debug** instead of **Run**. It pauses at the breakpoint.
3. Hover over a variable, or look at the Variables panel, to see its value right now.
4. **Step Over** (F8 in IntelliJ, F10 in VS Code) runs one line at a time. Watch the variables change and stop at the first one that surprises you.

**`println` debugging** works anywhere. Print the values you're unsure of, then delete the lines when you're done:

```java
System.out.println("DEBUG i=" + i + " total=" + total);
```

**Four classic bugs** worth checking first:

| Bug | Looks like | Fix |
|---|---|---|
| Off-by-one | `i <= array.length` | `i < array.length` |
| `==` on text | `name == "Othaya"` | `name.equals("Othaya")` |
| A hidden `int` | `int total; total += 3.5;` compiles and drops the `.5` | Make it a `double` |
| `null` | `map.get(key).trim()` when the key isn't there | Check with `containsKey` first |

## 🪜 Steps

1. **Exercise 1: The missing phone number** (`exercise1/`). Read a `NullPointerException` and fix it.
2. **Exercise 2: Lorry checks** (`exercise2/`). Two quiet bugs that only the tests reveal.
3. **Project: Fix the Weighbridge** (`_start/src/Weighbridge.java`). For each bug:
   1. **Reproduce it.** Run the program and type `Othaya` when it asks.
   2. **Write down what you expected**, for example "Average should be 3970.17".
   3. **Find the line.** Use the stack trace for the crash, and the debugger or a `println` for the wrong numbers.
   4. **Fix the smallest thing you can**, then run the tests again.
   5. **Commit each fix on its own**, with a message that says what was wrong.

## 🧪 Taste test

```bash
cd day13/_start/src
javac Weighbridge.java
java Weighbridge
```

When all three bugs are fixed, it prints:

```
=== Weighbridge ===
Total coffee in: 11910.5 kg
Average per lorry: 3970.1666666666665 kg
Which factory's total?
Othaya
Othaya: 8610.5 kg
```

To run the tests, copy `_end/src/WeighbridgeTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day13/_start/src day13/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day13/_start/src --scan-classpath
```

For an exercise, use `day13/exercise1/src` or `day13/exercise2/src` in both commands.

## 🆘 Stuck?

- **Fixed the crash, but nothing else changed?** Good, that's progress. Now set a breakpoint inside `averageNet` and watch `total` as it adds each lorry.
- **`totalFor` gives `0.0`, but `factories[i]` and `factory` both say `Othaya` in the debugger**: they hold the same text, but they're two different objects. Which operator compares text?
- **The tests pass but you're not sure why**: compare your file with `_end/src/Weighbridge.java`. Each fix there has a comment explaining what it was.

## ✅ Checklist
- [ ] Read the stack trace and found the line that crashed
- [ ] Used a breakpoint and stepped through a loop
- [ ] Fixed the off-by-one, the `==` and the hidden `int`
- [ ] Fixed the `NullPointerException` in Exercise 1
- [ ] Fixed both bugs in Exercise 2
- [ ] Weighbridge's tests pass
- [ ] Committed each fix separately

## ➡️ Next

On Day 14, the last Beginner day, the coffee finally reaches a cup, and you'll learn to read a coffee shop menu.
