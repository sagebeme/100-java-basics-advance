# Day 43 - Exercise 2: A messy fare table

Real pages are written by people, so the same thing is written many ways. `src/fares.html` has a table of matatu fares where the price might say `KES 70`, `Ksh. 120`, `150/-` or `KES 1,000 (night)`, and some rows aren't fares at all.

Write two methods in `src/FareTable.java`:

**`priceKes(String text)`**: the first whole number in the text, ignoring commas, or `-1` if there isn't one.

| Text | Result |
|---|---|
| `KES 70` | `70` |
| `Ksh. 120` | `120` |
| ` 150/- ` | `150` |
| `KES 1,000 (night)` | `1000` |
| `ask the conductor` | `-1` |

**`read(Document page)`**: a `FareRow(route, from, to, priceKes)` for every row of `#fares` that has exactly **4 cells** and a price. Skip the header row, the note and the row with no price.

## Files

- `src/FareTable.java`: your code goes here (`FareRow` is in it).
- `src/fares.html`: the table.
- `src/FareTableTest.java`: the tests.
- `solution/FareTable.java`: one answer.

## 📌 Stuck?

- `text.replace(",", "")` first, then a regular expression `\d+` finds digits: `Pattern.compile("\\d+").matcher(text)` and `find()`.
- `page.select("#fares tr")` gives the rows; `row.select("td")` the cells. The header row has `th`, not `td`, so it has 0 cells.
