# Day 51 - Exercise 2: Bits and bytes

Internet plans are sold in **megabits** per second (Mbps), but files are measured in **bytes**, and one byte is 8 bits. Mixing them up is why a "20 Mbps" line downloads a 100 MB file in about 40 seconds, not 5. Write three methods in `src/Units.java`:

| Method | Example |
|---|---|
| `mbps(long bytes, long millis)` | `2_500_000` bytes in `1000` ms → `20.0` Mbps |
| `secondsToDownload(double megabytes, double mbps)` | `100` MB at `20` Mbps → `40.0` |
| `describe(double mbps)` | below 5: `"slow"`; below 25: `"fine for HD video"`; otherwise `"fast"` |

Use 1 megabit = 1,000,000 bits, as internet providers do.

## 📌 Stuck?

Bits per second = bytes × 8 ÷ seconds. One answer is in `solution/Units.java`.
