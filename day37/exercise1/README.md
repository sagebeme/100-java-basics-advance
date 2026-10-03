# Day 37 - Exercise 1: Streaks

Write two methods in `src/Streaks.java`. Both take the days a habit was done as a `Set<LocalDate>`.

**`current(Set<LocalDate> done, LocalDate today)`**: how many days in a row, ending today. If today isn't done (yet), count back from yesterday instead.

**`longest(Set<LocalDate> done)`**: the longest run of consecutive days anywhere.

```
done: 28 Sep, 29 Sep, 30 Sep, 2 Oct, 3 Oct     today: 3 Oct
current → 2      longest → 3
```

## Files

- `src/Streaks.java`: your code goes here.
- `src/StreaksTest.java`: the tests.
- `solution/Streaks.java`: one answer.

## 📌 Stuck?

- `current`: a `while (done.contains(day))` loop, moving `day = day.minusDays(1)` each time.
- `longest`: copy the dates into a `TreeSet` so they're in order. A run continues when `previous.plusDays(1).equals(day)`, and otherwise starts again at 1.
