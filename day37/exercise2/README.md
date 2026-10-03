# Day 37 - Exercise 2: Rates and best days

Write two methods in `src/HabitStats.java`:

**`rate(Set<LocalDate> done, LocalDate today, int days)`**: the whole-number percentage of the last `days` days, ending today, that were done. Done days outside that window don't count.

**`bestWeekday(Set<LocalDate> done)`**: the day of the week with the most completions, as a `DayOfWeek`. If two days tie, the earlier one in the week (Monday first) wins. With no days at all, return `null`.

```
24 of the last 30 days done                  → rate 80
done on 3 Mondays, 4 Saturdays, 1 Sunday     → SATURDAY
```

## Files

- `src/HabitStats.java`: your code goes here.
- `src/HabitStatsTest.java`: the tests.
- `solution/HabitStats.java`: one answer.

## 📌 Stuck?

- `Math.round(count * 100f / days)` rounds to the nearest whole percent.
- Count per weekday in an `EnumMap<DayOfWeek, Integer>`, or an `int[7]` indexed by `day.getDayOfWeek().getValue() - 1`.
- Loop over `DayOfWeek.values()`, which is Monday to Sunday, and keep the first one with the highest count.
