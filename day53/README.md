# Day 53 - Nyumba Data Entry: Scrape, Clean, Type, Check

> "Copy these listings into our survey form." It's the classic data-entry job. Do it with a bot, and make the bot check its own work.

## 📖 The story

A housing researcher wants Nairobi rents in her survey form, one response per house. The listings are on a website, written by landlords in every style: `KES 65,000 / month`, `Ksh 12,500`, `Bedsitter near TRM`, `Call for price`. Some appear twice. Today's bot does the whole job:

```
$ mvn -q compile exec:java
Scraped 8 listings; 6 clean rows after removing duplicates and gaps.
Entered and checked 6 responses. Saved rentals.csv too.
```

Both the listings page (*Nyumba Listings*) and the survey form are practice pages that run on your computer.

This is the last automation day. You've driven browsers, waited, retried, backed off and checked results. Day 54 starts building the *other* side, the web apps that bots and people use.

## 🎯 Learning Objectives
- Combine two tools: jsoup for static pages, Selenium for interactive ones
- Clean messy data into records, and remove duplicates
- Enter data one row at a time, waiting for each to be saved
- Verify the result by reading it back
- Write CSV that handles commas and quotes

## 🧰 What you'll use

**Pick the lightest tool.** If a page works without JavaScript, fetching it with `HttpClient` and parsing it with jsoup is ten times faster than a browser. Use Selenium only where you must.

**Records and sets.** Records compare by their values, so a `LinkedHashSet<Rental>` removes exact duplicates and keeps the order.

**Check your work.** After entering everything, read back what the site recorded and compare it with your list. A bot that types into the void without checking will happily get every row wrong.

## 🪜 Steps

1. **Exercise 1: Cleaning listings** (`exercise1/`).
2. **Exercise 2: CSV that survives a round trip** (`exercise2/`).
3. **Project: Nyumba Data Entry** (`_start/`, a Maven project): `Cleaner.clean`, `SurveyForm.submit` and `DataEntryBot.enterAll`.

## 🧪 Run it and test it

From `day53/_end` (or `_start`), with Maven and Chrome:

```bash
mvn test
mvn -q compile exec:java -Dexec.args=--watch
```

## 🆘 Stuck?

- **Rows go missing**: you typed the next row before the last one was saved. Wait until the response count goes up.
- **`2BR` isn't recognised**: make the pattern case-insensitive and allow no space: `(\d+)\s*(?:br|bed|bedroom)`.
- **The final check fails on areas**: trim spaces when cleaning, because the form trims them too.

## ✅ Checklist
- [ ] Scraped a static page with jsoup
- [ ] Cleaned and de-duplicated the data
- [ ] Entered each row and waited for it to save
- [ ] Read the results back and compared them
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 54 introduces Spring Boot: your first web application in Java.
