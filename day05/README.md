# Day 5 - Drying Bed Countdown

> The beans are washed. Now they sit in the sun until they're dry enough to store. How many days will that take?

## 🌱 On the shamba

At the factory, the cherries' skin and sticky fruit are removed, and the beans are washed. They come out in a papery shell called **parchment**, and they're wet: about half of their weight is water.

Next they're spread on long raised tables called **drying beds**. Workers turn them by hand through the day and cover them when it rains. When the moisture drops to about **11%**, the beans can be stored and shipped without going mouldy.

Today's program counts down the days, one loop at a time:

```
=== Drying Bed Countdown ===
Starting moisture (%)?
52
Weather for the next days (S = sunny, C = cloudy, R = rain)?
SSSSCRSSSSSSSSSS
Day 1: 49.0% #########
Day 2: 46.0% #########
...
Day 15: 11.5% ##
Day 16: 8.5% #
Dry after 16 days.
```

## 🎯 By the end of today
- Repeat work with a `for` loop when you know how many times
- Repeat with a `while` loop when you stop on a condition
- Walk through every item with an enhanced `for` (`for (char day : forecast.toCharArray())`)
- Stop a loop early with `return` or `break`
- Build text inside a loop

## ☕ Bean fact

Kenya's famous "washed" coffees are dried in parchment on raised **African beds**: wire mesh on wooden legs, so air reaches the beans from below. Drying slowly over one to three weeks, and stirring the beans often, keeps the flavour clean and bright.

## 🧰 Java you'll use

**`for`** runs a set number of times:

```java
for (int day = 1; day <= 7; day++) {
    System.out.println("Day " + day);
}
```

**`while`** runs for as long as its condition is true. Make sure something inside the loop changes the condition, or it never stops:

```java
double moisture = 52;
int days = 0;
while (moisture > 11) {
    moisture = moisture - 3;
    days++;
}
```

**Enhanced `for`** visits each item without an index. `toCharArray()` turns a `String` into its characters:

```java
for (char weather : "SSCR".toCharArray()) {
    if (weather == 'R') {
        continue; // skip to the next day
    }
}
```

Single characters use single quotes: `'R'` is a `char`, and `"R"` is a `String`. You can compare `char`s with `==`.

**Building text in a loop**:

```java
String bar = "";
for (int i = 0; i < 5; i++) {
    bar = bar + "#";
}
// bar is "#####"
```

## 🪜 Steps

1. **Exercise 1: Weighing tickets** (`exercise1/`). `for` and enhanced `for`.
2. **Exercise 2: Ask until it makes sense** (`exercise2/`). A `while` loop that keeps asking until the answer is valid.
3. **Project: Drying Bed Countdown** (`_start/src/DryingBed.java`).
   1. `daysToDry(start, target, lossPerDay)`: the same loss every day. Use a `while` loop. If `lossPerDay` is `0` or less, the beans will never dry, so return `-1` straight away instead of looping forever.
   2. `daysToDryWithWeather(start, target, forecast)`: a sunny day removes 3 points, a cloudy day removes 1.5, and a rainy day removes nothing because the beans are covered. Loop over the forecast's characters and return the day they reach the target. If the forecast runs out first, return `-1`.
   3. `moistureBar(moisture)`: one `#` for every full 5 points, so `52` gives 10 of them.
   4. In `main`, print each day's moisture and bar, then the result.

## 🧪 Taste test

```bash
cd day05/_start/src
javac DryingBed.java
java DryingBed
```

To run the tests, copy `_end/src/DryingBedTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day05/_start/src day05/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day05/_start/src --scan-classpath
```

For an exercise, use `day05/exercise1/src` or `day05/exercise2/src` in both commands.

## 🆘 Stuck?

- **The program hangs**: a `while` loop whose condition never becomes false. Check that the moisture actually changes inside the loop, and that a loss of `0` returns `-1` before the loop starts.
- **Off by one day**: decide whether you count the day before or after removing the moisture, then check against a small example by hand. 52 → 11 at 3 a day takes 14 days.
- **`incomparable types: char and String`**: compare a `char` with `'S'`, not `"S"`.
- **Is "dry" `<` or `<=` the target?** Reaching exactly 11% counts as dry, so stop when `moisture <= target`.

## ✅ Checklist
- [ ] Wrote a `for` loop and a `while` loop
- [ ] Looped over a `String`'s characters with an enhanced `for`
- [ ] Left a loop early with `return`
- [ ] Made sure a loop can't run forever
- [ ] Finished both exercises
- [ ] Drying Bed Countdown's tests pass
- [ ] Committed code to Git

## ➡️ Next

Dry beans are sorted by size, and the size sets the price. On Day 6 you'll write methods that label them the way Kenya does.
