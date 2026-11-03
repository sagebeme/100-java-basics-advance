# Day 71 - Data Analysis: Is the Shop Doing Better?

> Six months of sales figures and one question: are things getting better? Today you answer it with numbers instead of feelings: the average, the middle, the spread and the trend.

## 📖 The story

Chebet runs a cosmetics shop in Eldoret, and her M-Pesa statements show what came in each month. Her sister says the shop is struggling; Chebet thinks it's growing. They're both looking at the same figures:

```
month,amount
Jan,1000
Feb,1200
Mar,900
Apr,1500
May,1800
Jun,2000
```

(thousands of shillings). March was bad, so her sister remembers March. Today's program reads the figures and writes a report that settles it:

```
Sales Report
Records: 6
Mean: 1400.00
Median: 1350.00
Max: 2000.00
Min: 900.00
Std Dev: 404.15
Trend: UP
```

The trend compares the average of the first half of the months with the second half: up if the second half is more than 1% higher, down if it's more than 1% lower, otherwise flat.

## 🎯 Learning Objectives
- Read CSV text into records
- Work out the mean, median, minimum, maximum and standard deviation, and know what each tells you
- Handle the edge cases: no data, one value, an even or odd number of values
- Describe a trend with a simple, explainable rule
- Return a `record` of results instead of printing from inside the maths
- Test statistics with known answers and a tolerance for decimals

## 🧰 What you'll use

**The numbers, and what they mean:**

| | How | Tells you |
|---|---|---|
| Mean | add them, divide by how many | the "typical" month, but one huge month pulls it up |
| Median | sort, take the middle (or the average of the two middle ones) | the typical month, ignoring extremes |
| Standard deviation | the square root of the average squared distance from the mean | how much months differ from each other |

**Streams do most of it:**

```java
double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
List<Double> sorted = values.stream().sorted().toList();
double variance = values.stream().mapToDouble(v -> Math.pow(v - mean, 2)).average().orElse(0);
```

**A record for the answer.** `Statistics(mean, max, min, median, standardDeviation)` keeps the calculation separate from the report, so a test can check each number and a web page could show them differently.

**Comparing decimals in tests.** `0.1 + 0.2` isn't exactly `0.3` in a `double`. Use `assertEquals(expected, actual, 0.01)` with a tolerance.

## 🪜 Steps

**Project: Chebet's sales report** (the `day71/` folder is a finished version: build your own first, then compare).

1. `SaleRecord(month, amount)` and `loadData(csv)`, skipping the header.
2. `calculateStats(values)`: all five numbers, and all zeros for an empty list rather than an exception.
3. `findTrend(values)`: compare the two halves, with a 1% band for "flat".
4. `generateReport(records)`: the text above.
5. Tests with figures you've worked out by hand, including an odd count for the median and each kind of trend.
6. Try it with your own figures: airtime spending, matatu fares, or a team's goals.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day71
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **The median is wrong for six values**: with an even count there are two middle values, at `n/2 - 1` and `n/2`. Average them.
- **The median changes the original list's order**: `sorted()` on a stream makes a new list; `Collections.sort` would sort the caller's list in place.
- **`NoSuchElementException` on an empty file**: `Collections.max` of an empty list throws. Check for empty first.
- **A test fails with `expected 404.15 but was 404.1475`**: give `assertEquals` a tolerance, or round only when printing.
- **The trend is always FLAT**: a 1% band is `0.01`, not `1`.

## ✅ Checklist
- [ ] Read the CSV into records
- [ ] Mean, median, min, max and standard deviation, including the empty case
- [ ] A trend with a rule you can explain
- [ ] The tests pass
- [ ] Ran it on figures of my own
- [ ] Committed code to Git

## ➡️ Next

Day 72 draws these numbers as charts and saves them as PNG images.
