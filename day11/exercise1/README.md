# Day 11 - Exercise 1: What time of year is it?

Kenya's coffee areas have two harvests. The big one, the **main crop**, comes after the long rains. A smaller **fly crop** comes after the short rains. In much of central Kenya that works out roughly like this:

| Months | Season |
|---|---|
| October to December (10 to 12) | `"main crop"` |
| April to June (4 to 6) | `"fly crop"` |
| Any other month (1 to 12) | `"growing"` |
| Anything else | `"not a month"` |

Write `seasonFor(int month)` in `src/CoffeeCalendar.java`, then `harvestMonths()`, which uses a loop and `seasonFor` to return how many of the twelve months are harvest months.

## Files

- `src/CoffeeCalendar.java`: your code goes here.
- `src/CoffeeCalendarTest.java`: the tests.
- `solution/CoffeeCalendar.java`: one answer.

## 📌 Stuck?

Handle the impossible months first: `month < 1 || month > 12`. In `harvestMonths`, loop from 1 to 12 and count the months whose season isn't `"growing"`.
