# Day 44 - Exercise 1: Cleaning prices

Sellers on a mitumba site type prices however they like. Write two methods in `src/Cleaning.java`:

**`collapseSpaces(String text)`**: trim the ends and turn every run of spaces, tabs or line breaks into one space.

**`priceKes(String text)`**: the price in whole shillings, or `-1` if there's no number.

| Text | Result |
|---|---|
| `KES 1,200` | `1200` |
| `Ksh 2,500` | `2500` |
| `850/=` | `850` |
| `KES 1.8k` | `1800` |
| `2K` | `2000` |
| `SOLD` | `-1` |

## Files

- `src/Cleaning.java`: your code goes here.
- `src/CleaningTest.java`: the tests.
- `solution/Cleaning.java`: one answer.

## 📌 Stuck?

- `text.replaceAll("\\s+", " ")`: `\s+` means "one or more whitespace characters".
- Remove commas first. Then a regular expression with two groups, the number and an optional `k`: `(\d+(?:\.\d+)?)\s*(k)?` with `Pattern.CASE_INSENSITIVE`. `m.group(2)` is `null` when there's no `k`.
