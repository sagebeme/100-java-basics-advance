# Day 36 - Exercise 1: Percent change

Write two methods in `src/PercentChange.java`, using `BigDecimal` throughout:

**`percentChange(String before, String after)`**: the change as a percentage, rounded half-up to 2 decimal places.

| before → after | Result |
|---|---|
| `17.10` → `18.20` | `6.43` |
| `38.00` → `35.70` | `-6.05` |
| `0.10` → `0.30` | `200.00` |

Throw an `IllegalArgumentException` if `before` is zero, since a change from nothing has no percentage.

**`describe(BigDecimal change)`**: `"▲ 6.43%"`, `"▼ 6.05%"` or `"● 0.00%"` for no change.

## Files

- `src/PercentChange.java`: your code goes here.
- `src/PercentChangeTest.java`: the tests.
- `solution/PercentChange.java`: one answer.

## 📌 Stuck?

- `new BigDecimal("17.10")` from text, never from a `double`.
- `divide(divisor, 2, RoundingMode.HALF_UP)` rounds as it divides.
- `signum()` is `1`, `0` or `-1`, and `abs()` drops the minus sign.
- Compare a `BigDecimal` with zero using `compareTo(BigDecimal.ZERO) == 0`, not `equals`: `0.00` and `0` aren't `equals`.
