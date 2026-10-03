# Day 11 - Season Simulator Capstone Project

> The Beginner capstone. Simulate a whole year on Wanjiru's farm, from the first rains to payday.

## 🌱 On the shamba

For ten days you've followed coffee one step at a time: picking (Day 1), weighing (Day 7), drying (Day 5), grading (Day 6), payday (Day 8). Today you put the whole journey into one program.

Nobody knows how much rain a season will bring, so the simulator rolls the dice: it picks a random rainfall, and everything else follows from it. Run it a few times and you'll see why farmers worry about the weather.

```
=== Season Simulator ===
Farmer's name?
Wanjiru
How many coffee trees?
300
Season report for Wanjiru
Rainfall: 1250 mm (a good year)
Harvest: 750.0 kg of cherry from 300 trees
Drying: 14 days on the beds
Green beans: 125.0 kg (AA 37.5, AB 50.0, PB 12.5, C 25.0)
Payout: KES 60,562.50
Simulate another season? (yes/no)
no
```

The rules of this simulated world (simplified, but the shape is real):

| Step | Rule |
|---|---|
| Rainfall | A random number from 600 to 2200 mm a year |
| Harvest per tree | Below 800 mm: 1.0 kg (drought). 800 to 1800 mm: 2.5 kg. Above 1800 mm: 1.8 kg (too wet, so disease) |
| Drying | Beans start at 52% moisture and are dry at 11%. They lose 3 points a day, but only 2 a day after more than 1800 mm of rain |
| Green beans | 6 kg of cherry makes 1 kg (Day 2) |
| Grades | AA 30%, AB 40%, PB 10%, C 20% of the green beans |
| Payout | KES 85 per kg of cherry, minus a 5% levy (Day 8) |

## 🎯 By the end of today
- Break a big problem into small methods, each with one job
- Use `Random`, and pass it into methods so they can be tested
- Combine a `Map`, loops, decisions and formatted output in one program
- Reuse ideas from earlier days instead of starting from nothing

## ☕ Bean fact

A coffee tree takes three to four years after planting before it gives its first proper harvest, and it can keep producing for decades. That's why a single bad season hurts so much: a farmer can't just plant something else next month.

## 🧰 Java you'll use

**`Random`** makes random numbers:

```java
import java.util.Random;

Random rng = new Random();
rng.nextDouble();      // a decimal from 0.0 up to (not including) 1.0
rng.nextInt(6);        // a whole number from 0 to 5
600 + rng.nextDouble() * 1600; // a decimal from 600 up to 2200
```

**Pass the `Random` in.** A method that makes its own `new Random()` gives different answers every run, so you can't test it. A method that takes a `Random` as a parameter can be given one with a fixed **seed**, and then it gives the same numbers every time:

```java
static double randomRainfall(Random rng) { ... }

randomRainfall(new Random());    // real use: different every season
randomRainfall(new Random(42));  // in a test: the same every time
```

Better still, keep the randomness in one small method and let everything else take plain numbers. Then most of the program doesn't need a `Random` at all to be tested.

## 🪜 Steps

1. **Exercise 1: What time of year is it?** (`exercise1/`). Kenya's two coffee seasons.
2. **Exercise 2: Weather dice** (`exercise2/`). Randomness you can test.
3. **Project: Season Simulator** (`_start/src/SeasonSimulator.java`). One method per row of the rules table. Test each before moving on.
   1. `randomRainfall(Random rng)`.
   2. `kgPerTree(rainfallMm)` and `harvestKg(trees, rainfallMm)`.
   3. `dryingDays(rainfallMm)`: a `while` loop, like Day 5.
   4. `gradeSplit(greenKg)`: a `LinkedHashMap` from grade to kilograms, in the order AA, AB, PB, C.
   5. `payout(cherryKg)`.
   6. `seasonReport(farmer, trees, rainfallMm)`: the six report lines, built from the methods above. Describe the rainfall as `a dry year` (below 800), `a very wet year` (above 1800) or `a good year`.
   7. `main`: ask for the farmer and the trees, then loop: roll a rainfall, print the report, and ask whether to simulate another season.

## 🧪 Taste test

```bash
cd day11/_start/src
javac SeasonSimulator.java
java SeasonSimulator
```

To run the tests, copy `_end/src/SeasonSimulatorTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day11/_start/src day11/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day11/_start/src --scan-classpath
```

For an exercise, use `day11/exercise1/src` or `day11/exercise2/src` in both commands.

## 🆘 Stuck?

- **Where do I start?** With the smallest methods: `kgPerTree`, then `harvestKg`. Leave `seasonReport` and `main` until the end.
- **The grades come out in a strange order**: use a `LinkedHashMap`, which keeps the order you `put` them in.
- **The report's numbers look wrong**: test each method on its own with the example (1250 mm, 300 trees) and compare with the report above.
- **`1,250 mm` vs `1250 mm`**: print the rainfall with `%.0f`, without a comma, to match the tests.

## ✅ Checklist
- [ ] Wrote one small method for each rule
- [ ] Passed a `Random` into a method instead of creating one inside it
- [ ] Built the report from the other methods
- [ ] The menu loop asks to simulate another season
- [ ] Finished both exercises
- [ ] Season Simulator's tests pass
- [ ] Committed code to Git

## ➡️ Next

Anyone could change a farmer's total in today's program with one line of code. On Day 12 you'll protect the records with `private` fields and scope.
