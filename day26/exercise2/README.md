# Day 26 - Exercise 2: Mama Oliech's till, in streams

Mama Oliech's restaurant takes M-Pesa at a till. At closing time she wants a few answers about the day's payments. Yesterday's exercise would have used loops. Today, write every answer as **one stream**, with no `for` loop, in `src/Till.java`. `src/Payment.java` is done.

The tests use this day:

| Code | Phone | KES | Hour |
|---|---|---|---|
| QK1 | 0712000001 | 150 | 7 |
| QK2 | 0722000002 | 1200 | 7 |
| QK3 | 0712000001 | 80 | 9 |
| QK4 | 0733000003 | 2500 | 13 |
| QK5 | 0722000002 | 1000 | 13 |
| QK6 | 0700000004 | 50 | 13 |
| QK7 | 0712000001 | 1000 | 18 |

| Method | For that day | Stream steps you'll want |
|---|---|---|
| `total(payments)` | `5980` | `mapToInt`, `sum` |
| `bigPayers(payments, atLeast)` | `bigPayers(day, 1000)` is `["0712000001", "0722000002", "0733000003"]`: each phone once, in order | `filter`, `map`, `distinct`, `sorted`, `collect` |
| `perHour(payments)` | `{7=2, 9=1, 13=3, 18=1}`, hours in order | `collect(Collectors.groupingBy(...))` |
| `largest(payments)` | QK4. With no payments, an empty `Optional`, not `null` and not an error. | `max` |
| `codesFor(payments, phone)` | `"QK1, QK3, QK7"`; a phone that never paid gives `""` | `filter`, `map`, `Collectors.joining(", ")` |

## Files

- `src/Till.java`: your code goes here.
- `src/Payment.java`: done.
- `src/TillTest.java`: the tests.
- `solution/Till.java`: one answer.

```bash
javac -cp "lib/*" -d day26/exercise2/src day26/exercise2/src/*.java
java -cp "day26/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- `payments.stream().mapToInt(Payment::getShillings).sum()` is all of `total`. `mapToInt` turns the stream into plain `int`s, which can be summed.
- `groupingBy` takes up to three things: what to group by, what kind of map to build, and what to do with each group. `Collectors.groupingBy(Payment::getHour, TreeMap::new, Collectors.counting())` groups by hour, in a `TreeMap` so the hours come out in order, and counts each group.
- `max` needs to know how to compare: `Comparator.comparingInt(Payment::getShillings)`. It returns an `Optional` because an empty list has no largest.
