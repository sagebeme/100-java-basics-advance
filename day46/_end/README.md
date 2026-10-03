# Day 46 - Solution

The finished Bei Alert, checked by `BeiAlertTest.java`.

Things worth noticing:

- **Structured data first, visible text second.** `meta[itemprop=price]` is put there for search engines and survives redesigns; the `.price` text is only a fallback.
- **The history file only grows.** Each run appends today's prices, so tomorrow's run knows today's.
- **A "sale" is checked against history.** The blender claims "was KES 6,999", but it cost KES 5,499 two weeks ago, so that isn't a real discount.
- **Out-of-stock items never alert.** A cheap price you can't pay is just noise.
- **`judge` takes `today` as a parameter**, so the tests can replay any day.
