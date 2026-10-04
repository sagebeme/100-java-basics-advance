# Day 53 - Solution

The finished Nyumba Data Entry bot. `mvn test` runs `CleanerTest` (no browser) and `DataEntryBotTest` (headless Chrome).

Things worth noticing:

- **The right tool for each page.** The listings need no JavaScript, so jsoup reads them; the form needs a browser, so Selenium fills it.
- **Clean before you type.** Duplicates and rows without a price never reach the form.
- **One row at a time, confirmed.** The bot waits for each response to be recorded before typing the next.
- **It checks its own work.** At the end it reads back what the form recorded and compares it with what it meant to send.
