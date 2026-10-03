# Day 10 - Roast Checker

> Green beans don't smell like coffee yet. Roasting changes that. Write methods that return how far a roast went.

## 🌱 On the shamba

Wanjiru's green beans have travelled to a roastery. Green coffee smells grassy, like dried peas. In a hot drum the beans turn yellow, then brown, and at about **196°C** they start to pop like popcorn. Roasters call this the **first crack**. Stop soon after it for a light roast, keep going for medium or dark, and too far is burnt.

A roaster checks a batch two ways: the temperature the beans reached, and how much weight they lost, because hotter roasts drive out more water. Today's methods return both answers and check whether they agree:

```
=== Roast Checker ===
Final bean temperature (°C)?
212
Green weight (g)?
1000
Roasted weight (g)?
845
Thermometer says: MEDIUM
Weight lost: 15.5% (MEDIUM)
The thermometer and the scale agree.
```

| Final temperature | Level |
|---|---|
| below 196°C | `UNDERDEVELOPED` (stopped before first crack) |
| 196 to 205 | `LIGHT` |
| 206 to 218 | `MEDIUM` |
| 219 to 230 | `DARK` |
| above 230 | `BURNT` |

| Weight lost | Level |
|---|---|
| below 14% | `LIGHT` |
| 14% to just under 17% | `MEDIUM` |
| 17% or more | `DARK` |

## 🎯 By the end of today
- Return a value from a method, and use that value in another method
- Return early as soon as the answer is known
- Return a "this didn't work" value, like `-1`, for impossible input
- Return a `boolean` that answers a yes/no question
- Choose between several cases with `switch`

## ☕ Bean fact

Darker doesn't mean stronger. The longer a bean roasts, the more of its own flavour burns away, so a light roast tastes more of the farm (fruit, flowers, that blackcurrant from Nyeri), and a dark roast tastes more of the roasting (smoke, bitter chocolate). There's about as much caffeine in each.

## 🧰 Java you'll use

**A return value** is the method's answer. The caller can store it, print it, or pass it straight into another method:

```java
double loss = weightLossPercent(1000, 845);  // 15.5
String level = levelFromLoss(loss);          // "MEDIUM"
String same = levelFromLoss(weightLossPercent(1000, 845)); // the same, in one line
```

**Early return.** `return` ends the method on the spot. A chain of `if`s with a `return` in each needs no `else`:

```java
static String roastLevel(int temp) {
    if (temp < 196) return "UNDERDEVELOPED";
    if (temp <= 205) return "LIGHT";
    ...
    return "BURNT";
}
```

**A value that means "impossible".** A weight loss can't be negative, so `-1` makes a clear signal that the input was wrong:

```java
if (green <= 0 || roasted > green) {
    return -1;
}
```

**`switch`** picks one case by value, and reads more clearly than a long chain of `equals`:

```java
int mg = switch (drink) {
    case "espresso" -> 63;
    case "filter" -> 95;
    default -> 0;
};
```

## 🪜 Steps

1. **Exercise 1: How much caffeine?** (`exercise1/`). `switch`, and a `boolean` answer.
2. **Exercise 2: Roast timer** (`exercise2/`). Early returns, and a `-1` for impossible input.
3. **Project: Roast Checker** (`_start/src/RoastChecker.java`).
   1. `roastLevel(int finalTempC)`, using the first table.
   2. `weightLossPercent(green, roasted)`: the percentage of the green weight that's gone. Return `-1` if `green` is 0 or less, or if the roasted beans somehow weigh more than the green ones.
   3. `levelFromLoss(double lossPercent)`, using the second table. Return `UNKNOWN` for a negative loss.
   4. `readingsAgree(temp, green, roasted)`: `true` when both methods give the same level. Build it from the other three methods.
   5. In `main`, print the example's lines. When the readings disagree, print `Check your thermometer or your scale.`

## 🧪 Taste test

```bash
cd day10/_start/src
javac RoastChecker.java
java RoastChecker
```

To run the tests, copy `_end/src/RoastCheckerTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day10/_start/src day10/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day10/_start/src --scan-classpath
```

For an exercise, use `day10/exercise1/src` or `day10/exercise2/src` in both commands.

## 🆘 Stuck?

- **`missing return statement`**: Java needs a `return` on every path. End with a final `return` after the `if`s.
- **The loss is `0.0`**: integer division again. If you used `int` grams, `(1000 - 845) / 1000` is `0`. Use `double`s.
- **`readingsAgree` says `false` for a match**: compare the two `String`s with `equals`, not `==`.
- **A `switch` case runs into the next one**: that's the old `case "x":` style. Use the arrow style `case "x" -> ...`, which never falls through.

## ✅ Checklist
- [ ] Used one method's return value in another method
- [ ] Returned early from an `if`
- [ ] Returned `-1` for impossible input
- [ ] Wrote a method that returns a `boolean`
- [ ] Used a `switch`
- [ ] Finished both exercises
- [ ] Roast Checker's tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 11 is the Beginner capstone: simulate a whole season on Wanjiru's farm, from the first rains to payday.
