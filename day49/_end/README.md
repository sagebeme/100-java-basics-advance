# Day 49 - Solution

The finished Kazi Bot. `mvn test` runs `JobMatcherTest` (no browser) and `KaziBotTest` (headless Chrome).

Things worth noticing:

- **Choosing is separate from applying.** `JobMatcher` decides; `ApplyPage` only fills in forms.
- **Each step is checked before the next.** After every "Next", the bot reads the form's own error messages and stops with them.
- **A file upload is just `sendKeys` with a full path**, sent to the `<input type="file">`.
- **The ledger stops duplicates**, and `MAX_PER_RUN` keeps each run small. Careful beats fast when real people read what a bot sends.
