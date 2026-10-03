# Day 39 - Exercise 1: Leave days

Write `leaveDaysNeeded(LocalDate first, LocalDate last, Set<LocalDate> holidays)` in `src/LeaveDays.java`. A trip runs from `first` to `last`, both included. You need a day of leave for every **weekday** in that range that **isn't a public holiday**.

| Trip | Holidays | Leave days |
|---|---|---|
| Fri 25 Dec – Sun 27 Dec 2026 | 25, 26 Dec | `0` |
| Wed 23 Dec – Sun 27 Dec 2026 | 25, 26 Dec | `2` (Wed, Thu) |
| Mon 28 Dec 2026 – Fri 1 Jan 2027 | 1 Jan | `4` |

If `last` is before `first`, throw an `IllegalArgumentException`.

## Files

- `src/LeaveDays.java`: your code goes here.
- `src/LeaveDaysTest.java`: the tests.
- `solution/LeaveDays.java`: one answer.

## 📌 Stuck?

`first.datesUntil(last.plusDays(1))` is a stream of every date in the trip. Filter out Saturdays, Sundays and holidays, then `count()`.
