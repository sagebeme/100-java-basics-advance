# Day 2 - Cherry to Cup

> How many kilograms of cherry does it take to make one cup of coffee? Today, Java does the maths.

## 🌱 On the shamba

Wanjiru delivered 23.5 kg of cherry yesterday. She has never tasted the coffee made from it, and she's curious: how many cups is that?

On the way from her farm to a cup, the coffee loses a lot of weight:

1. **Cherry → green beans.** At the factory the fruit is removed and the seeds are washed and dried. About **6 kg of cherry** gives **1 kg of green (raw) beans**.
2. **Green → roasted.** Roasting dries the beans further. They keep about **85%** of their weight.
3. **Roasted → cups.** One cup uses about **10 g** of ground coffee, roughly two teaspoons.

Today's program turns kilograms of cherry into cups:

```
=== Cherry to Cup ===
Kilograms of cherry?
23.5
23.5 kg of cherry makes 3.92 kg of green beans, 3.33 kg roasted, about 332 cups.
That's enough for one cup a day for 0 years and 332 days.
```

## 🎯 By the end of today
- Choose between `int` and `double`, and know what each one is for
- Cast a `double` to an `int`, and know that it drops the decimals
- Spot integer division (`7 / 2` is `3`) and avoid it when you need decimals
- Use `final` for a value that never changes
- Format numbers in text with `String.format`

## ☕ Bean fact

Kenya grades and sells its coffee as **green beans**, not roasted ones. Most of it is roasted in another country, so the farmer who grew it may never drink a cup made from their own harvest.

## 🧰 Java you'll use

**`final`** marks a value that never changes. By convention its name is in capitals:

```java
final double CHERRY_PER_KG_GREEN = 6.0;
```

**`int` vs `double`.** An `int` holds whole numbers, and a `double` holds decimals. When you divide two `int`s, Java throws the decimals away:

```java
int a = 7 / 2;          // 3, not 3.5
double b = 7 / 2;       // still 3.0: the division happened between two ints first
double c = 7.0 / 2;     // 3.5: one side is a double, so the answer is too
double d = (double) 7 / 2; // 3.5: cast before dividing
```

**Casting** forces a value into another type. Going from `double` to `int` cuts off the decimals, it doesn't round:

```java
int cups = (int) 332.9; // 332
```

**`String.format`** puts values into text. `%.2f` means "a decimal with 2 places", `%d` means "a whole number", and `%s` means "text":

```java
String.format("%.2f kg makes %d cups", 3.329, 332); // "3.33 kg makes 332 cups"
```

## 🪜 Steps

1. **Exercise 1: Member codes** (`exercise1/`). Practise `String` methods by building a cooperative member code.
2. **Exercise 2: Farm maths** (`exercise2/`). Avoid the integer-division trap.
3. **Project: Cherry to Cup** (`_start/src/CherryToCup.java`).
   1. Declare the three `final` constants: 6 kg of cherry per kg of green, 0.85 of the weight kept after roasting, and 10 g per cup.
   2. Fill in `greenKg`, `roastedKg` and `cups`. `cups` returns an `int`, so convert kilograms to grams and cast.
   3. Fill in `summary` with `String.format`.
   4. In `main`, read the kilograms, print the summary, then work out years and days with `/` and `%`. This is one place where integer division is exactly what you want.

## 🧪 Taste test

```bash
cd day02/_start/src
javac CherryToCup.java
java CherryToCup
```

To run the tests, copy `_end/src/CherryToCupTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day02/_start/src day02/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day02/_start/src --scan-classpath
```

For an exercise, use `day02/exercise1/src` or `day02/exercise2/src` in both commands.

## 🆘 Stuck?

- **The answer is a round number when it shouldn't be**: integer division. Make one side a `double` before dividing.
- **`possible lossy conversion from double to int`**: Java won't drop decimals unless you ask. Add a cast: `(int) value`.
- **`332.99999` instead of `333`**: decimals in a computer aren't exact. `String.format("%.2f", x)` rounds for display.
- **Years and days**: `cups / 365` gives whole years and `cups % 365` gives the days left over.

## ✅ Checklist
- [ ] Used `final` for the three constants
- [ ] Can explain why `7 / 2` is `3` in Java
- [ ] Cast a `double` to an `int`
- [ ] Formatted numbers with `String.format`
- [ ] Finished both exercises
- [ ] Cherry to Cup's tests pass
- [ ] Committed code to Git

## ➡️ Next

Not every cherry makes it to the factory. On Day 3 you'll write the rules that decide which ones do.
