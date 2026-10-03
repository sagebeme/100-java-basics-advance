# Day 4 - Rainfall Week

> Seven days of rain, one variable. Meet the array.

## 🌱 On the shamba

Coffee trees are fussy about rain. After a dry spell, a good shower makes them burst into white flowers that smell like jasmine. Every one of those flowers can become a cherry. Too little rain and the cherries stay small; too much and they split or catch disease.

Wanjiru's son Brian keeps a rain gauge, a plastic tube marked in millimetres, behind the house. He writes down the reading every evening. Today's program stores a week of readings and reports on them:

```
=== Rainfall Week ===
Rain on Monday (mm)?
12.5
...
Rain on Sunday (mm)?
0
Total: 31.0 mm
Average: 4.4 mm a day
Driest day: Tuesday
Rainy days: 4
```

## 🎯 By the end of today
- Store many values of one type in an array
- Read and change an element by its index, starting from `0`
- Use `.length` and walk through an array with a `for` loop
- Keep a growing list in an `ArrayList`, with `add`, `get`, `remove` and `size`

## ☕ Bean fact

Kenya's coffee areas get two rainy seasons a year: the **long rains** from about March to May and the **short rains** from about October to December. So many farms flower twice and harvest twice. The cherries take roughly seven to nine months to ripen after the flowers appear.

## 🧰 Java you'll use

**An array** holds a fixed number of values of one type. Each value has an index, and the first index is `0`:

```java
double[] rain = {12.5, 0.0, 3.2, 8.0, 0.4, 6.9, 0.0};
rain[0];          // 12.5, Monday
rain[6];          // 0.0, Sunday
rain.length;      // 7
rain[1] = 2.0;    // change Tuesday
```

You can also make an empty array of a given size. Every `double` in it starts at `0.0`:

```java
double[] rain = new double[7];
```

**Visiting every element** uses a `for` loop with an index. You'll learn loops properly tomorrow; for today, copy this pattern:

```java
double total = 0;
for (int i = 0; i < rain.length; i++) {
    total = total + rain[i];
}
```

**`ArrayList`** is a list that grows and shrinks. It holds objects, so for numbers you write `Integer` or `Double` instead of `int` or `double`:

```java
import java.util.ArrayList;

ArrayList<String> queue = new ArrayList<>();
queue.add("Wanjiru");     // ["Wanjiru"]
queue.add("Otieno");      // ["Wanjiru", "Otieno"]
queue.get(0);             // "Wanjiru"
queue.remove(0);          // removes and returns "Wanjiru"
queue.size();             // 1
queue.contains("Otieno"); // true
```

## 🪜 Steps

1. **Exercise 1: The picking team** (`exercise1/`). Read from an array by index.
2. **Exercise 2: The queue at the station** (`exercise2/`). Add to and remove from an `ArrayList`.
3. **Project: Rainfall Week** (`_start/src/RainfallWeek.java`).
   1. `DAYS` is an array of day names. Use it so `rain[i]` and `DAYS[i]` describe the same day.
   2. Fill in `total` and `average`.
   3. Fill in `driestDay`. Keep the index of the smallest reading so far, then return that day's name. If two days tie, return the first one.
   4. Fill in `rainyDays`, which counts the days with at least `minMm` of rain.
   5. In `main`, read seven readings into a `new double[7]` and print the report. Use 1.0 mm as the "rainy day" line.

## 🧪 Taste test

```bash
cd day04/_start/src
javac RainfallWeek.java
java RainfallWeek
```

To run the tests, copy `_end/src/RainfallWeekTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day04/_start/src day04/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day04/_start/src --scan-classpath
```

For an exercise, use `day04/exercise1/src` or `day04/exercise2/src` in both commands.

## 🆘 Stuck?

- **`ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 7`**: the last index is `length - 1`. Loop with `i < rain.length`, not `<=`.
- **The driest day is always Monday**: compare each reading with the smallest one *so far*, and update both the index and the smallest value when you find a smaller one.
- **`ArrayList<int>` doesn't compile**: lists hold objects. Use `ArrayList<Integer>`.

## ✅ Checklist
- [ ] Created an array, read from it and changed one element
- [ ] Looped over an array with `.length`
- [ ] Found the smallest value and where it was
- [ ] Used `ArrayList`'s `add`, `get`, `remove` and `size`
- [ ] Finished both exercises
- [ ] Rainfall Week's tests pass
- [ ] Committed code to Git

## ➡️ Next

After picking, the beans are spread out in the sun to dry. On Day 5 you'll use loops to count how many days that takes.
