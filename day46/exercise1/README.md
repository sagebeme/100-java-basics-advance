# Day 46 - Exercise 1: Structured data

Many shops tag their product pages with **schema.org** data for search engines: `<meta itemprop="price" content="1499.00">`. It's more reliable than the visible text, which changes with every redesign and is sometimes simply wrong. Look at `src/shoes.html`: the banner says KES 1,299, but the data says 1,499.

Write two methods in `src/StructuredPrice.java`:

| Method | For `shoes.html` |
|---|---|
| `price(Document page)` | `1499`: the `meta[itemprop=price]` content rounded to whole shillings, or, if there's no such tag, the digits of the visible `.price`. `-1` if there's neither. |
| `currency(Document page)` | `"KES"`: the `meta[itemprop=priceCurrency]` content, or `"KES"` if it's missing |

## Files

- `src/StructuredPrice.java`: your code goes here.
- `src/shoes.html`: the page.
- `src/StructuredPriceTest.java`: the tests.
- `solution/StructuredPrice.java`: one answer.

## 📌 Stuck?

`page.selectFirst("meta[itemprop=price]")` and `.attr("content")`. `Math.round(Double.parseDouble("1499.00"))`. For the visible price, `text.replaceAll("[^0-9]", "")` keeps only the digits.
