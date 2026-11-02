# Day 36 - NSE Price Alert: Combining Two Data Feeds

> When Safaricom shares jump 6% in a day, you want to know, and you want to know *why*. Combine a price feed and a news feed into one useful alert.

## 📖 The story

Akinyi has a few shares on the **Nairobi Securities Exchange (NSE)**, bought through a mobile app. She doesn't want to stare at prices all day. She wants a message when something big happens:

```
$ java PriceAlert
SCOM ▲ 6.43% (KES 17.10 → 18.20) Safaricom
  • Nairobi bourse closes mixed; KCB and Safaricom lead volumes (Market Matters)
  • Safaricom shares jump after M-Pesa growth beats forecasts (Biashara Daily)
KCB ▼ 6.05% (KES 38.00 → 35.70) KCB Group
  • Nairobi bourse closes mixed; KCB and Safaricom lead volumes (Market Matters)
  • KCB Group falls as investors weigh loan loss provisions (Soko Wire)
2 alerts. Quiet day for EABL, EQTY.
```

Real market data and news APIs need accounts and keys (Day 35 showed how to keep those safe) and charge for frequent calls. So today's program reads **recorded responses**, `prices.json` and `news.json`, in the same shape the APIs use. Swapping a file for an `HttpClient` call (Day 33) is a small change later. **The prices in this lesson are made up.**

## 🎯 Learning Objectives
- Combine two separate data sources into one result
- Calculate percentage changes, and round money correctly with `BigDecimal`
- Match news to a company with case-insensitive keyword search
- Sort with a `Comparator` (newest first) and take the top few with streams
- Format a readable alert with symbols, arrows and aligned numbers

## 🧰 Java you'll use

**`BigDecimal` for money.** A `double` can't store most decimal fractions exactly: `0.1 + 0.2` is `0.30000000000000004`. For amounts and percentages you show people, use `BigDecimal` and choose how to round:

```java
BigDecimal before = new BigDecimal("17.10");
BigDecimal after = new BigDecimal("18.20");
BigDecimal change = after.subtract(before)
        .multiply(BigDecimal.valueOf(100))
        .divide(before, 2, RoundingMode.HALF_UP);   // 6.43
```

Build a `BigDecimal` from a `String` (or from JSON with `getBigDecimal`), not from a `double`, or the inaccuracy comes along with it.

**Sorting and taking the top few** with a stream:

```java
headlines.stream()
        .filter(h -> h.mentions("Safaricom"))
        .sorted(Comparator.comparing(Headline::published).reversed())   // newest first
        .limit(3)
        .toList();
```

**`Instant`** is a moment in time, which is what `"2026-10-01T09:30:00Z"` is: the `Z` means UTC. `Instant.parse` reads it.

## 🪜 Steps

1. **Exercise 1: Percent change** (`exercise1/`). `BigDecimal` arithmetic and rounding.
2. **Exercise 2: Matching headlines** (`exercise2/`). Whole-word, case-insensitive matching.
3. **Project: NSE Price Alert** (`_start/src/`).
   1. `Market.parsePrices(json)`: a `Stock` for each symbol, with its name and its last two closing prices.
   2. `Stock.percentChange()`: from the previous close to the latest, to 2 decimal places.
   3. `Market.parseNews(json)`: a `Headline` for each article.
   4. `PriceAlert.alerts(stocks, news, threshold)`: for every stock that moved by at least `threshold` percent, **up or down**, the alert text with the two newest headlines that mention its symbol or its name. Biggest move first.
   5. `main`: read both files, print the alerts, and name the stocks that didn't move much.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day36/_end/src day36/_end/src/*.java
cd day36/_end/src && java -cp ".:../../../lib/*" PriceAlert && cd ../../..
java -cp "day36/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

Change a price in `prices.json` and run it again to see a different alert.

## 🆘 Stuck?

- **`ArithmeticException: Non-terminating decimal expansion`**: `BigDecimal.divide` needs to know how many decimals to keep and how to round: `divide(x, 2, RoundingMode.HALF_UP)`.
- **`6.4` instead of `6.43`, or `6.430`**: set the scale on the result with `setScale(2, RoundingMode.HALF_UP)`, and print with `toPlainString()`.
- **A fall isn't alerted**: compare the *size* of the move. `change.abs().compareTo(threshold) >= 0`.
- **"KCB" matches inside "KCBX"**: match whole words. Exercise 2 shows how, including the trap with `\b`.

## ✅ Checklist
- [ ] Parsed two JSON feeds
- [ ] Calculated a percentage change with `BigDecimal` and rounded it
- [ ] Alerted on big moves both up and down
- [ ] Matched, sorted and limited headlines with a stream
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 37 tracks something closer to home than share prices: your own habits, with streaks and a calendar heat map.
