# Day 47 - Exercise 1: Locators

A bot is only as good as its **locators**, the selectors it uses to find things on a page. Bad ones break the moment a designer moves a button. In `src/Locators.java`, fill in each constant with a CSS selector for `src/practice.html`. The test checks each one with jsoup, which understands the same selectors as Selenium's `By.cssSelector`:

| Constant | Must select |
|---|---|
| `SEARCH_BOX` | the search input |
| `ALL_EVENTS` | all three event cards |
| `BOOKABLE_EVENTS` | only the events that aren't `sold-out` |
| `EVENT_104_REGISTER` | the Register link inside the event with `data-id="104"` |
| `PRICES_IN_KES` | every element with a `data-kes` attribute |

Prefer ids and meaningful classes or attributes over positions like `li:nth-child(3)`. Positions change; meaning rarely does.

## 📌 Stuck?

`#q` (id), `li.event` (tag and class), `li.event:not(.sold-out)`, `li[data-id='104'] a.register`, `[data-kes]`. One answer is in `solution/Locators.java`.
