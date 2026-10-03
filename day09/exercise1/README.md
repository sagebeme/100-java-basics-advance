# Day 9 - Exercise 1: Totals by farmer

On Day 7 you kept two lists in step, names in one and weights in the other. A `Map` does the same job with less that can go wrong: each farmer's name is a key, and the value is their running total.

Write `totalsByFarmer(List<String> farmers, List<Double> kgs)`. It returns a map from each farmer to their total kilograms, in the order each farmer **first** appeared:

```java
farmers: ["Wanjiru", "Otieno", "Wanjiru"]
kgs:     [23.5,      31.0,     10.0]
```

gives `{Wanjiru=33.5, Otieno=31.0}`.

## Files

- `src/FarmerTotals.java`: your code goes here.
- `src/FarmerTotalsTest.java`: the tests.
- `solution/FarmerTotals.java`: one answer.

## 📌 Stuck?

- Use a `LinkedHashMap` so the first-seen order is kept.
- `totals.getOrDefault(name, 0.0)` gives the total so far, or `0.0` for someone new. Add this delivery and `put` it back.
- There's also a one-line way: `totals.merge(name, kg, Double::sum)`. Try the long way first.
