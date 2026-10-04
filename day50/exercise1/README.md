# Day 50 - Exercise 1: A rate limiter

Write `tryAcquire()` in `src/RateLimiter.java`: allow at most `max` actions in any window of `per` (say, 5 a minute). Keep the times of recent actions in a queue; drop the ones older than the window; if fewer than `max` remain, record this one and return `true`, otherwise return `false`.

This "sliding window" is how many real APIs limit you, and how a polite bot limits itself. The tests use a fake clock, so they run instantly.

## 📌 Stuck?

`recent.peekFirst()` is the oldest time. It's too old when it isn't after `now.minus(per)`. One answer is in `solution/RateLimiter.java`.
