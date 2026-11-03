# Day 92 - Portfolio Project: Web Scraper

> A lot of useful data lives on web pages with no download button. Today's tool fetches a page, picks out the items you point it at with CSS selectors, and saves them as a spreadsheet, politely and safely.

## 📖 The story

Juma runs a small blog about jobs in Mombasa's port and tourism industries. Every morning he visits the same few listing pages and copies the new headlines and links into a spreadsheet by hand. It takes an hour. He wants a tool: give it a page, tell it which part of the page is a "listing", and get a CSV he can open in Excel or Google Sheets.

```
URL to scrape: https://example.com/jobs
CSS selector for each item (e.g. 'article'): article
CSS selector for the title within an item (e.g. 'h2'): h2
CSS selector for the link within an item (e.g. 'a'): a
Output CSV file path: jobs.csv
Saved 12 items to jobs.csv
```

## 🎯 Learning Objectives
- Fetch and parse HTML with jsoup, with a timeout and a clear user agent
- Select elements with CSS selectors, and read text and absolute links from them
- Skip incomplete items instead of saving half-empty rows
- Write correct CSV: quoting commas, quotes and line breaks
- Protect spreadsheet users from formulas hidden in scraped text
- Test parsing against fixed HTML, and keep the one network test from failing offline

## 🧰 What you'll use

**jsoup** fetches a page and lets you query it like the browser's developer tools:

```java
Document page = Jsoup.connect(url).userAgent(USER_AGENT).timeout(10_000).get();
for (Element article : page.select("article")) {
    Element title = article.selectFirst("h2");
    Element link = article.selectFirst("a");
    // link.absUrl("href") turns "/posts/first" into "https://example.com/posts/first"
}
```

Find the selectors by right-clicking an item in your browser and choosing **Inspect**.

**Parse separately from fetch.** `fetch(url)` touches the network; `extractItems(document, ...)` only reads a `Document`. Tests build a `Document` from a fixed HTML string with `Jsoup.parse(html, baseUrl)`, so they're fast and never break because a website changed.

**CSV is easy to get wrong.** A title like `Cooks, waiters and cleaners` has a comma, so it must be wrapped in quotes, and quotes inside it doubled. And a title like `=HYPERLINK("http://...")` would be *run* by Excel when the file is opened, so values that start with `=`, `+`, `-` or `@` get a `'` in front, which makes the spreadsheet treat them as text.

**Scraping politely.** Read the site's terms and its `/robots.txt` first. Don't hammer it: one request, or a pause between many. If the site has an API or an RSS feed, use that instead.

## 🪜 Steps

**Your brief** (the `day92/` folder is one finished version: build your own first, then compare).

1. `ScrapedItem(title, link)`.
2. `WebScraper.fetch` (bad and unreachable URLs become an `IOException`) and `extractItems`.
3. `CsvSaver` with quoting and the formula guard.
4. `ScraperApp`: ask for the URL, the selectors and the output file.
5. Tests: extraction from fixed HTML, skipping incomplete items, no matches, bad URLs, CSV quoting, formulas, an empty list, and one live fetch of `example.com` that skips when offline.
6. Make it yours: follow "next page" links (with a pause between requests), remember items already saved and add only new ones, or schedule it to run every morning.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day92
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **Nothing matches**: the page may build its content with JavaScript after loading. jsoup only sees the HTML the server sends. View the page source (not the inspector) to check, and look for an API the page calls.
- **Links come out as `/posts/first`**: use `absUrl("href")`, not `attr("href")`, and make sure the document knows its base URL.
- **Excel shows one column**: some locales expect `;` between values. Choose the separator when importing.
- **`403 Forbidden`**: the site blocks scrapers. Respect that; look for an official feed or API.

## ✅ Checklist
- [ ] Items extracted with CSS selectors, with absolute links
- [ ] Correct, safe CSV output
- [ ] Parsing tested against fixed HTML
- [ ] Checked the site's terms and `robots.txt` before scraping it
- [ ] Added paging, de-duplication or scheduling
- [ ] Committed code to Git

## ➡️ Next

Day 93 automates a game: a bot that plays by reading the screen and pressing keys.
