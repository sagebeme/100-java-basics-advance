# Day 44 - Mitumba Price Watch: Scraping Many Pages Responsibly

> One page is easy. Real listings are spread over many pages, repeat themselves, and are written by a hundred different sellers. Crawl them properly.

## 📖 The story

Kevin resells second-hand (**mitumba**) clothes, and he buys from an online listings site for Gikomba market. Every morning he scrolls through page after page, comparing prices typed as `KES 1,200`, `Ksh 2,500`, `850/=` and `KES 1.8k`. Today you'll build him a price watcher that reads every page and gives a short report:

```
$ java PriceWatch
Found 9 listings for sale (4 requests, 1 second apart)
Average price: KES 1,333
Cheapest in each category:
  jackets  KES  1,200  Denim jacket, Levi's (Like new)
  shirts   KES    650  Football jersey, Harambee Stars (Like new)
  shoes    KES    900  Canvas sneakers (Worn)
Saved listings.csv, cheapest first
```

The site, *Gikomba Online*, is a practice website that runs on your own computer (`PracticeShop`), with three pages of listings and a `robots.txt`. You can crawl it as often as you like.

## 🎯 Learning Objectives
- Follow "next page" links until there are no more, with a safety limit
- Remove duplicates that appear on more than one page
- Clean messy text and numbers into a single format
- Read and obey `robots.txt`
- Wait between requests, and identify your program with a user agent
- Summarise scraped data: cheapest per category and averages

## 🧰 What you'll use

**Following links.** Each page links to the next one with `rel="next"`, a standard way of saying "this is the following page":

```java
Element link = page.selectFirst("a[rel=next]");
URI next = link == null ? null : URI.create(link.absUrl("href"));
```

Loop while `next` isn't `null`. Always add a page limit too, because a site with a bug (or a trap) can link to "next" forever.

**Duplicates.** Listings move between pages while you're reading them. Keep them in a `LinkedHashMap` keyed by the listing's ID (its SKU), and use `putIfAbsent`, so each appears once, in the order first seen.

**Politeness**, in code:

```java
if (pages > 0) Thread.sleep(delayMs);          // wait between requests
.header("User-Agent", "PriceWatch/1.0 (learning project)")
if (!robots.isAllowed(next.getPath())) break;  // respect the owner's wishes
```

**Regular expressions for messy numbers**: remove the commas, then find `digits`, an optional `.digits`, and an optional `k`.

## 🪜 Steps

1. **Exercise 1: Cleaning prices** (`exercise1/`). Spaces, commas, `/=` and `k`.
2. **Exercise 2: Reading robots.txt** (`exercise2/`). Which paths your robot must leave alone.
3. **Project: Mitumba Price Watch** (`_start/src/`).
   1. `readPage(page)`: a `Listing` for each card, cleaned, and without sold items.
   2. `crawl(first)`: read `robots.txt`, then follow the `rel="next"` links with a delay, a page limit and duplicate removal.
   3. `cheapestPerCategory` and `averagePrice`.
   4. Run it, and open `listings.csv`.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day44/_end/src day44/_end/src/*.java
cd day44/_end/src && java -cp ".:../../../lib/*" PriceWatch && cd ../../..
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day44/_end/src:lib/*" --scan-classpath
```

The program waits one second between pages, so it takes about three seconds. That's on purpose. The tests use a delay of zero.

## 🆘 Stuck?

- **The puffer jacket appears twice**: it's on pages 1 and 2. Key your map by SKU and use `putIfAbsent`.
- **The crawl never ends**: check that `next` becomes `null` when there's no `rel="next"` link, and that `pages` goes up every time round.
- **`absUrl` returns an empty string**: you parsed the page without a base URI. Pass the page's own URL to `Jsoup.parse(html, url)`.
- **The average is a long decimal**: round it with `Math.round`.

## ✅ Checklist
- [ ] Followed `rel="next"` links with a page limit
- [ ] Removed duplicate listings by SKU
- [ ] Cleaned prices written in many ways
- [ ] Read `robots.txt` and stayed out of disallowed paths
- [ ] Waited between requests and sent a user agent
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 45 combines scraping with an API that needs a login: build a playlist of the songs that topped the charts on any date, with OAuth.
