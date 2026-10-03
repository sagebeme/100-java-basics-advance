# Day 43 - Solution

The finished Headline Digest, checked by `HeadlineScraperTest.java`.

Things worth noticing:

- **One CSS selector does the filtering**: `article.story:not(.sponsored)`. The advert never reaches the Java code.
- **Every optional part is checked for `null`.** The last story has no summary, and the scraper still works. Real pages are always missing something.
- **`absUrl("href")`** turns `/news/...` into a full address, using the page's base URI.
- **The CSV writer quotes fields properly**: a headline with a comma and quote marks in it is still one column.
- **`load` sends a clear user agent and a timeout** when given a live URL, and loads one page, not a whole site.
