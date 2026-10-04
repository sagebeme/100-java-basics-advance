# Day 52 - Exercise 1: Exponential backoff

When a site says "slow down" (or an API answers `429 Too Many Requests`), trying again immediately makes things worse. Write `nextDelay()` and `reset()` in `src/Backoff.java`: the first wait is `first`, and each further failure doubles it (1 s, 2 s, 4 s, 8 s…) but never beyond `cap`. A success calls `reset()`, which starts again from `first`.

Careful: after 100 failures, `first × 2^100` doesn't fit in a `long`. Cap the exponent before you multiply.

## 📌 Stuck?

`1L << n` is 2ⁿ as a `long`; `Math.min(failures, 20)` keeps it sane. One answer is in `solution/Backoff.java`.
