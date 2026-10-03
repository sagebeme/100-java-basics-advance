# Day 36 - Solution

The finished NSE Price Alert, checked by `PriceAlertTest.java`.

Things worth noticing:

- **`getBigDecimal("close")`** reads the price straight into a `BigDecimal`, so no `double` rounding sneaks in.
- **`.abs()` in one place** makes the threshold work for falls as well as rises, and sorting by `abs()` puts the biggest move first whichever way it went.
- **`Headline.mentions` uses `(?<!\w)` and `(?!\w)`** (sturdier than `\b` for names ending in a dot) and `Pattern.quote`, so a company name with a dot or bracket in it can't break the pattern.
- **The feeds are just text.** `Market` doesn't care whether the JSON came from a file or from `HttpClient`, so switching to a live API won't change the alert logic.
