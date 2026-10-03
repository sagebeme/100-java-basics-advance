# Day 8 - Exercise 1: Copies and arrays

At the end of a good season, the cooperative adds a bonus to every farmer's kilograms. Write two versions in `src/BonusKilos.java` and see which one works the way you'd expect:

| Method | What it does |
|---|---|
| `withBonus(double kg, double bonus)` | **Returns** `kg + bonus`. The caller's own variable doesn't change. |
| `addBonusToAll(double[] kgs, double bonus)` | Returns nothing (`void`), but adds `bonus` to **every element** of the caller's array. |

Then, in `main`, try this and predict the output before you run it:

```java
double mine = 50;
withBonus(mine, 10);
System.out.println(mine);          // ?

double[] team = {50, 20};
addBonusToAll(team, 10);
System.out.println(team[0]);       // ?
```

## Files

- `src/BonusKilos.java`: your code goes here.
- `src/BonusKilosTest.java`: the tests.
- `solution/BonusKilos.java`: one answer.

## 📌 Stuck?

Re-read **Java you'll use** in the [day's README](../README.md). The answers are `50.0` and `60.0`. If that surprises you, the README explains why.
