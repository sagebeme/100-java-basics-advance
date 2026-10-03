# Day 3 - Exercise 1: Is the station open?

The weighing station has opening hours, and farmers walk a long way to get there. Write `isOpen(String day, int hour)` so a farmer can check before setting off.

- Monday to Friday: open from 7 (7am) up to, but not including, 17 (5pm).
- Saturday: open from 7 up to, but not including, 12 (noon).
- Sunday: closed all day.

`hour` uses the 24-hour clock, so `14` is 2pm. `day` might arrive as `"saturday"` or `"SATURDAY"`.

| Call | Result |
|---|---|
| `isOpen("Monday", 7)` | `true` |
| `isOpen("Monday", 17)` | `false` |
| `isOpen("Saturday", 11)` | `true` |
| `isOpen("Saturday", 12)` | `false` |
| `isOpen("Sunday", 10)` | `false` |

## Files

- `src/StationHours.java`: your code goes here.
- `src/StationHoursTest.java`: the tests.
- `solution/StationHours.java`: one answer.

## 📌 Stuck?

Deal with Sunday first and return `false`. Then handle Saturday. Everything left is a weekday. `>=` includes the number and `<` doesn't, which is exactly how "from 7 up to 17" works.
