# Day 4 - Solution

`src/RainfallWeek.java` is one finished version, checked by `src/RainfallWeekTest.java`.

Things worth noticing:

- **Two arrays that line up.** `DAYS[i]` and `mm[i]` are the same day, so finding an index in one gives you the answer in the other.
- **`average` reuses `total`** instead of adding everything up a second time.
- **`driestDay` remembers an index, not a value.** It starts by assuming day `0` is the driest, then checks every day after it. Strict `<` means a tie keeps the earlier day.
- **`main` fills the array in a loop.** `new double[7]` makes seven empty slots, and the loop fills slot `i` with the answer for `DAYS[i]`.
