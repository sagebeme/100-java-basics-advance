# Day 43 - Exercise 1: Page facts

`src/stall.html` is a saved page for a stall at Gikomba market. Write four methods in `src/PageFacts.java`, each taking a jsoup `Document`:

| Method | Returns for `stall.html` |
|---|---|
| `title(page)` | `"Baraka's Stall · Gikomba Market"` |
| `headings(page)` | the `<h2>` texts in order: `[Jackets, Sneakers, Find us]` |
| `links(page)` | every link as an **absolute** URL, each once, sorted |
| `imagesWithoutAlt(page)` | how many `<img>` have **no** `alt` attribute at all: `1` |

The page is parsed with the base URI `https://baraka.example/`, so `/jackets` becomes `https://baraka.example/jackets`.

Note that `alt=""` is *allowed*: it means "this picture is decoration, skip it". Only a missing `alt` is a problem.

## Files

- `src/PageFacts.java`: your code goes here.
- `src/stall.html`: the page.
- `src/PageFactsTest.java`: the tests.
- `solution/PageFacts.java`: one answer.

## 📌 Stuck?

- `page.select("h2")` finds elements with a CSS selector, and `.eachText()` gives their texts.
- `link.absUrl("href")` resolves a relative link against the base URI.
- `img:not([alt])` selects images with no `alt` attribute.
- A `TreeSet` both removes duplicates and sorts.
