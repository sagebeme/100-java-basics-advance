# Day 46 - Bei Alert: An Automated Price Tracker

> "Flash sale! Was KES 6,999!" Was it, though? Track prices over time, get an email when something you want really gets cheaper, and catch the fake discounts.

## 📖 The story

Njoki is saving for a new phone. She checks the shop every evening, and every week there's a "flash sale" that doesn't feel like much of one. Today you'll build **Bei Alert** (*bei* means price): it reads the product pages on her watchlist, remembers every price it has seen, and emails her only when the price is really worth it:

```
$ java BeiAlert njoki@example.com
🔔 Spark 20 Smartphone, 128 GB                KES  18,499  KES 18,499 is at or under your target of KES 19,000
   Wireless Earbuds Pro                       KES   3,299  Out of stock
   Jikoni 1.5L Blender                        KES   5,999  Not a real sale: it was KES 5,499 earlier this month
```

The shop, *Duka Digital*, is made up, and its pages are saved in the project. The program can also read a live page by URL, following the rules from Day 43: one page, a clear user agent, and only where the site allows it.

## 🎯 Learning Objectives
- Read **schema.org structured data** from a page, with the visible text as a fallback
- Keep a price history in a CSV file that grows every run
- Ask questions of the history: lowest and highest over the last 30 days
- Write alert rules that check history, not just today's number
- Send alerts through Day 32's `Mailer`

## 🧰 What you'll use

**Structured data.** Shops add hidden tags so search engines can show prices in results:

```html
<meta itemprop="price" content="18499">
<link itemprop="availability" href="https://schema.org/InStock">
```

```java
page.selectFirst("meta[itemprop=price]").attr("content");   // "18499"
```

These rarely change when the shop redesigns its pages, so they make the sturdiest scraper. The visible price is a backup.

**Appending to a file**, so history builds up day by day:

```java
Files.writeString(csv, "2026-10-03,PHONE-01,18499\n", StandardOpenOption.APPEND);
```

**History questions** with streams:

```java
history.before(sku, today, 30).stream().mapToInt(Integer::intValue).min();   // the 30-day low
```

## 🪜 Steps

1. **Exercise 1: Structured data** (`exercise1/`). Trust the data over the banner.
2. **Exercise 2: Is it really cheaper?** (`exercise2/`). Percentages below the high, and "lowest yet".
3. **Project: Bei Alert** (`_start/src/`).
   1. `ProductPage.read`: name, SKU, price, the crossed-out "was" price, and whether it's in stock.
   2. `AlertRules.judge`: out of stock, a fake sale, at target, or how far to go, in that order.
   3. Run it for `2026-10-03`, read the email in `outbox/`, and look at the new lines at the end of `history.csv`.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day46/_end/src day46/_end/src/*.java
cd day46/_end/src && java -cp ".:../../../lib/*" BeiAlert you@example.com 2026-10-03 && cd ../../..
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day46/_end/src:lib/*" --scan-classpath
```

Each run adds three lines to `history.csv`. Run `git checkout day46/_end/src/history.csv` to reset it.

## 🆘 Stuck?

- **The price is 1299 when the data says 1499**: you read the visible banner first. Prefer `meta[itemprop=price]`.
- **`NumberFormatException` on `"18,499"`**: remove everything that isn't a digit first.
- **Every sale looks fake**: only call it fake when the page *claims* a discount (a "was" price) and the 30-day low is at or below today's price.
- **The 30-day low includes today**: compare with prices *before* today, or every price would be its own lowest.

## ✅ Checklist
- [ ] Read a price from structured data, with a fallback
- [ ] Kept a growing price history
- [ ] Found 30-day lows and highs
- [ ] Spotted a fake sale
- [ ] Sent an alert email through the `Mailer`
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 47 drives a real web browser from Java with Selenium, against a practice events site that runs on your own computer.
