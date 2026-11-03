# Day 25 - Exercise 2: The co-op's weekly milk report

Exercise 1 turned the co-op's CSV into rows; `src/Delivery.java` (already done) holds one row as an object. Now the chairperson wants the numbers for Saturday's meeting. Write them in `src/MilkReport.java`, with plain loops (tomorrow does the same with streams).

The tests use this week:

| Farmer | Date | Litres | Grade |
|---|---|---|---|
| Wanjiku | 2026-11-01 | 18 | A |
| Kamau | 2026-11-01 | 12 | B |
| Njoroge | 2026-11-01 | 25 | A |
| Wanjiku | 2026-11-02 | 20 | A |
| Kamau | 2026-11-02 | 7 | C |
| Njoroge | 2026-11-02 | 18 | B |

| Method | For that week |
|---|---|
| `totalByFarmer(deliveries)` | `{Kamau=19, Njoroge=43, Wanjiku=38}`, farmers in alphabetical order. |
| `topFarmers(deliveries, n)` | The `n` biggest totals, as text, biggest first: `topFarmers(week, 2)` is `["Njoroge 43", "Wanjiku 38"]`. Equal totals go alphabetically. Asking for more farmers than there are gives them all. |
| `averageLitres(deliveries)` | `16.67` (100 litres over 6 deliveries). No deliveries: `0`. |
| `gradeShare(deliveries, grade)` | The percent of all the **litres** that were that grade, rounded: A is `63`, B `30`, C `7`. No milk: `0`. |
| `rejected(deliveries)` | Grade C milk is turned away: the C deliveries, in the order they came in. |

## Files

- `src/MilkReport.java`: your code goes here.
- `src/Delivery.java`: done.
- `src/MilkReportTest.java`: the tests.
- `solution/MilkReport.java`: one answer.

```bash
javac -cp "lib/*" -d day25/exercise2/src day25/exercise2/src/*.java
java -cp "day25/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- `TreeMap` keeps its keys sorted, so `totalByFarmer` is one loop: `totals.put(name, totals.getOrDefault(name, 0) + litres)`.
- For `topFarmers`, copy the totals into a list, `new ArrayList<>(totals.entrySet())`, and sort it. A preview of tomorrow, `list.sort((a, b) -> ...)` takes a small function that returns a negative number when `a` should come first: compare the litres backwards, `Integer.compare(b.getValue(), a.getValue())`, and only if that's `0`, the names forwards.
- Percentages: `Math.round(100.0 * part / whole)`. Without the `.0` it's whole-number division, and 63/100 is 0.
