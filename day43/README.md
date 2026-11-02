# Day 43 - Headline Digest: Web Scraping with jsoup

> Some useful information has no API, only a web page. Learn to read pages from Java, and to do it politely.

## 📖 The story

Every morning, Achieng reads the same news site on her phone, scrolling past adverts and the stories she's already seen. She'd rather get a short list: section, headline, link. The site has no API, but every page is HTML, and on Days 41 and 42 you learned how HTML is built. Today you'll take it apart:

```
$ java HeadlineScraper
Mwangaza Daily · Kenya's news, made up for practice
7 stories: {Business=2, Culture=1, News=2, Sport=2}
  [News    ] Expressway to cut night-time fares for matatus
             https://mwangaza.example/news/2026/10/03/expressway-night-fares
  [Business] Tea prices rise at the Mombasa auction for a third week
             https://mwangaza.example/business/2026/10/03/tea-auction-prices
  ...
Saved headlines.csv
```

The newspaper, *Mwangaza Daily*, is made up, and its front page is saved in the project, so you can practise as often as you like without bothering a real website.

## 🎯 Learning Objectives
- Parse HTML into a document tree with **jsoup**
- Find elements with CSS selectors: classes, attributes and `:not(...)`
- Read text, attributes and absolute URLs from elements
- Handle parts of a page that are missing, without crashing
- Write scraped data to a properly quoted CSV file
- Scrape responsibly: check the terms and `robots.txt`, identify yourself, and go slowly

## 🧰 What you'll use

**Parsing and selecting**:

```java
Document page = Jsoup.parse(html, "https://mwangaza.example/");   // the base URI resolves relative links
Elements stories = page.select("article.story:not(.sponsored)");   // CSS selectors, like in your stylesheet
Element link = story.selectFirst("h2 a");                          // null if there isn't one
link.text();            // "Tea prices rise at the Mombasa auction..."
link.attr("href");      // "/business/2026/10/03/tea-auction-prices"
link.absUrl("href");    // "https://mwangaza.example/business/2026/10/03/tea-auction-prices"
```

**A live page** (only when you're allowed to):

```java
Document page = Jsoup.connect("https://example.com/")
        .userAgent("HeadlineDigest/1.0 (learning project)")
        .timeout(10_000)
        .get();
```

**Scraping responsibly.** A website belongs to someone. Before scraping a real one:

1. **Read its terms of use.** Many forbid scraping. If there's an API or an RSS feed, use that instead.
2. **Check `/robots.txt`** (for example `https://example.com/robots.txt`). It lists the paths the owner asks robots to stay out of.
3. **Go slowly.** One page every few seconds at most. Your program can send requests far faster than a person, and that can overload a small site.
4. **Say who you are** with a clear user agent, and never collect personal data you don't need.

## 🪜 Steps

1. **Exercise 1: Page facts** (`exercise1/`). Titles, headings, links and images.
2. **Exercise 2: A messy fare table** (`exercise2/`). Clean numbers that people wrote in many different ways.
3. **Project: Headline Digest** (`_start/src/`).
   1. `scrape(page)`: a `Story` for each `article.story` that isn't `.sponsored`, with its id, section, headline, absolute URL, time and summary. Missing parts become `"Other"`, `null` or `""`.
   2. `countBySection(stories)`: how many stories in each section, sorted by name.
   3. `csvField(value)`: quote a field that contains a comma, a quote or a line break, and double the quotes inside.
   4. Run it on the saved page, open `headlines.csv` in a spreadsheet, and check every headline is in one cell.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day43/_end/src day43/_end/src/*.java
cd day43/_end/src && java -cp ".:../../../lib/*" HeadlineScraper && cd ../../..
java -cp "day43/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 🆘 Stuck?

- **`NullPointerException` on one story**: `selectFirst` returns `null` when nothing matches. Check before calling `.text()`.
- **Links come out as `/news/...`**: use `absUrl("href")`, and parse with a base URI.
- **The advert is in your list**: `article.story:not(.sponsored)`, with no space between `story` and `:not`.
- **A live site gives `HTTP error fetching URL. Status=403`**: the site is refusing robots. Respect that, and look for an API or RSS feed instead.
- **One headline spreads over two cells in the spreadsheet**: it has a comma in it. Fix `csvField`.

## ✅ Checklist
- [ ] Parsed a page with jsoup and selected elements with CSS selectors
- [ ] Skipped adverts with `:not(...)`
- [ ] Handled missing parts without crashing
- [ ] Wrote properly quoted CSV
- [ ] Can explain what `robots.txt` is and why scraping should be slow
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 44 scrapes many pages, not one: follow "next page" links through a listings site and clean up what you find.
