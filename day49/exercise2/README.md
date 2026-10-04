# Day 49 - Exercise 2: A daily limit

A bot that sends 200 applications in a minute looks like spam, and probably is. Write two methods in `src/DailyLimit.java`:

| Method | Returns |
|---|---|
| `sentOn(List<LocalDate> history, LocalDate day)` | how many applications were sent on that day |
| `remaining(List<LocalDate> history, LocalDate day, int limit)` | how many more are allowed that day, never below 0 |

## 📌 Stuck?

`history.stream().filter(day::equals).count()`, and `Math.max(0, ...)`. One answer is in `solution/DailyLimit.java`.
