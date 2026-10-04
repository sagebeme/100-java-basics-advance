# Day 48 - Exercise 1: Rising prices

In clicker games, every upgrade you buy makes the next one dearer, usually by 15%. Write two methods in `src/Pricing.java`:

| Method | Example (base 15) |
|---|---|
| `costAfter(base, owned)` | the price when you already own `owned`: `round(base × 1.15^owned)`. `costAfter(15, 0)` is `15`, `costAfter(15, 1)` is `17`, `costAfter(15, 10)` is `61` |
| `totalCost(base, owned, more)` | the price of buying `more` in a row: `totalCost(15, 0, 3)` is `15 + 17 + 20 = 52` |

## 📌 Stuck?

`Math.pow(1.15, owned)` and `Math.round(...)`. For `totalCost`, add up `costAfter(base, owned + i)` for each `i`. One answer is in `solution/Pricing.java`.
