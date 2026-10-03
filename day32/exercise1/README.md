# Day 32 - Exercise 1: Date maths

Write three methods in `src/DateMaths.java`:

| Method | Example |
|---|---|
| `ageOn(LocalDate born, LocalDate day)` | born 1991-10-02, on 2026-10-01 → `34`; on 2026-10-02 → `35` |
| `daysUntilNextBirthday(LocalDate born, LocalDate today)` | born 1991-10-02, today 2026-09-30 → `2`; today 2026-10-02 → `0`; today 2026-10-03 → `364` |
| `friendly(LocalDate date)` | 2026-10-02 → `"Friday, 2 October 2026"` |

For `daysUntilNextBirthday`, someone born on 29 February has their next birthday on 28 February in an ordinary year.

## Files

- `src/DateMaths.java`: your code goes here.
- `src/DateMathsTest.java`: the tests.
- `solution/DateMaths.java`: one answer.

## 📌 Stuck?

- `Period.between(born, day).getYears()` counts whole years.
- Try this year's birthday; if it's already passed, use next year's. `ChronoUnit.DAYS.between(a, b)` counts the days.
- `born.withYear(2027)` on a 29 February date gives 28 February automatically.
- `DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)`.
