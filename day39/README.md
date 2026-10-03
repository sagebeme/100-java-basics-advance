# Day 39 - Capstone Part 1: Long Weekend Deal Finder

> Christmas falls on a Friday this year. Find every cheap flight out of Nairobi for that long weekend before your friends do.

## 📖 The story

Every Kenyan with a job knows the trick: a public holiday on a Friday or a Monday is a free three-day weekend. Today and tomorrow you'll build a two-part capstone. **Part 1** (today) finds the next long weekend, searches a fare API for each place on your wishlist, and reports the deals that are under your limit:

```
$ java DealFinder
Next long weekend: Fri 25 Dec – Sun 27 Dec (Christmas Day, Boxing Day)
3 deals from Nairobi:
  Mombasa    MBA  KES   6,200  Ndege Link     direct  KES 800 under your limit
  Diani      UKA  KES   8,900  Ndege Link     direct  KES 600 under your limit
  Zanzibar   ZNZ  KES  21,500  Ndege Link     direct  KES 500 under your limit
No deal yet: Kisumu (cheapest KES 4,800, your limit KES 4,500), Kigali (cheapest KES 27,400, your limit KES 25,000)
```

**Part 2** (Day 40) turns it into a club: members sign up with their own wishlists and get the deals by email.

Real flight APIs need business accounts. So the project includes a **practice fare API**, `PracticeFareServer`, a real HTTP server that starts on your computer and answers from `fares.json`. Your `FareClient` talks to it over HTTP exactly as it would talk to a real API. Set `FARE_API_URL` and the same code uses a different server. **The airlines and prices are made up.**

## 🎯 Learning Objectives
- Break a capstone into small pieces you can test one at a time
- Work out long weekends from a list of public holidays, including merging Easter's four days
- Call an HTTP API (Day 33), with its address from the environment (Day 35)
- Use `Optional` for "there might not be an answer"
- Sort with a `Comparator` and format a readable report

## 🧰 Java you'll use

**`Optional<T>`** is a box that holds one value or nothing. It makes "maybe there's no deal" impossible to forget:

```java
Optional<Fare> best = fares.stream()
        .filter(f -> f.priceKes() <= limit)
        .min(Comparator.comparingInt(Fare::priceKes));

if (best.isPresent()) { ... best.get() ... }
best.map(Fare::airline).orElse("no deal");
```

**`DayOfWeek`** tells you what day a date falls on:

```java
LocalDate.of(2026, 12, 25).getDayOfWeek();   // FRIDAY
```

**A server and a client in one program.** `PracticeFareServer` uses the same `HttpServer` you met in Day 33's tests. It's `AutoCloseable`, so try-with-resources stops it when you're done:

```java
try (PracticeFareServer api = new PracticeFareServer(Path.of("fares.json"))) {
    FareClient client = new FareClient(api.baseUri().toString());
    ...
}
```

## 🪜 Steps

1. **Exercise 1: Leave days** (`exercise1/`). How many days off do you need to book for a trip?
2. **Exercise 2: Cheapest per city** (`exercise2/`). Group fares with `Collectors.groupingBy`.
3. **Project: Long Weekend Deal Finder** (`_start/src/`). The records, `FareClient` and `PracticeFareServer` are ready.
   1. `Holidays.longWeekends`: a Friday holiday makes Fri–Sun, a Monday one Sat–Mon, and weekends that touch merge. Name every holiday inside each weekend.
   2. `Holidays.next(holidays, today)`: the first long weekend that starts today or later.
   3. `DealFinder.cheapestWithin(wish, fares)` and `sortDeals(deals)`.
   4. `main`: search each wish for the weekend's dates and print the report.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day39/_end/src day39/_end/src/*.java
cd day39/_end/src && java -cp ".:../../../lib/*" DealFinder && cd ../../..
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day39/_end/src:lib/*" --scan-classpath
```

Pass a date to pretend it's a different day: `java ... DealFinder 2027-02-01` looks for Easter instead. Edit `wishlist.csv` to change your limits.

`holidays.csv` lists Kenya's fixed-date public holidays and Easter for late 2026 and 2027. Dates can change by gazette notice, and Eid holidays depend on the moon, so check the official list before you book.

## 🆘 Stuck?

- **Easter shows up as two weekends**: when the next weekend starts on or before the day after the last one ends, merge them instead of adding a new one.
- **Boxing Day isn't named**: after finding a weekend's dates, look for *any* holiday inside them, not just the one that created it.
- **`Connection refused`**: the practice server has stopped. Keep the client's calls inside the try-with-resources block.
- **`NoSuchElementException: No value present`**: you called `get()` on an empty `Optional`. Check `isPresent()` first, or use `orElse`.

## ✅ Checklist
- [ ] Found long weekends, including a four-day Easter
- [ ] Searched a fare API over HTTP for each wish
- [ ] Used `Optional` for "maybe no deal"
- [ ] Sorted deals by saving and printed a clear report
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 40: Part 2. Members, sign-ups, personal wishlists, and an email to each member with only their deals.
