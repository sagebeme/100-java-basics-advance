# Day 48 - Exercise 2: Which strategy wins?

Before letting a bot loose, simulate it. `src/Simulator.java` plays a simplified Chapati Clicker second by second: each second you earn your clicks plus your income, then the strategy buys what it wants. Write `simulate(boolean smart, int seconds, int clicksPerSecond)`, returning the **total** chapatis earned:

- **naive**: keep buying the *cheapest* affordable upgrade
- **smart**: keep buying the affordable upgrade with the shortest payback (cost ÷ income)

Upgrades: pan (base 15, +1/s), helper (base 100, +5/s), jiko (base 600, +24/s), stall (base 3000, +100/s). Each costs 15% more for every one you own (Exercise 1's rule).

The test checks that over 10 minutes at 5 clicks a second, the smart strategy earns more. Run it with different numbers and see how big the gap is.

## 📌 Stuck?

Keep `owned` in an `int[4]`. Each second: add the earnings to both `total` and `wallet`, then loop "pick an affordable upgrade, pay, own one more" until nothing's affordable. One answer is in `solution/Simulator.java`.
