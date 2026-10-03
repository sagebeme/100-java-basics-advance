# Day 3 - Exercise 2: Altitude check

In Kenya, Arabica coffee grows best on the slopes of Mount Kenya and the Aberdares, roughly between 1,400 and 2,100 metres above sea level. Higher farms are cooler, so the cherries ripen slowly and the coffee often tastes brighter.

Write `altitudeRating(int metres)`:

| Altitude | Rating |
|---|---|
| below 1400 | `"too low"` |
| 1400 to 1699 | `"good"` |
| 1700 to 2100 | `"excellent"` |
| above 2100 | `"too high"` |

## Files

- `src/AltitudeCheck.java`: your code goes here.
- `src/AltitudeCheckTest.java`: the tests.
- `solution/AltitudeCheck.java`: one answer.

## 📌 Stuck?

Check from the bottom up: `if (metres < 1400)`, then `else if (metres < 1700)`, and so on. Each `else if` already knows the earlier checks failed, so it only needs one comparison. Test the edges: 1400, 1700, 2100 and 2101.
