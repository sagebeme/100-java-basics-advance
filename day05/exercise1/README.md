# Day 5 - Exercise 1: Weighing tickets

Every delivery at the weighing station gets a numbered ticket. Write two methods in `src/WeighingTickets.java`:

| Method | Example |
|---|---|
| `ticketNumbers(int first, int last)` | `ticketNumbers(7, 10)` → `"T007 T008 T009 T010"` |
| `totalKg(double[] deliveries)` | `{12.5, 8.0, 20.25}` → `40.75` |

Use a plain `for` loop for the tickets and an enhanced `for` for the total. The tickets are separated by single spaces, with no space at the end.

## Files

- `src/WeighingTickets.java`: your code goes here.
- `src/WeighingTicketsTest.java`: the tests.
- `solution/WeighingTickets.java`: one answer.

## 📌 Stuck?

- `String.format("T%03d", 7)` gives `"T007"`.
- To avoid a space at the end, add the space *before* every ticket except the first one.
- `for (double kg : deliveries)` gives you each delivery in turn.
