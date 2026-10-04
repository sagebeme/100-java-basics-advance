# Day 47 - Solution

The finished Tamasha Bot. `mvn test` runs `EventPickerTest` (no browser) and `TamashaBotTest`, which drives a headless Chrome through the practice site. The browser tests skip themselves if Chrome can't start.

Things worth noticing:

- **Page objects.** `SearchPage` and `RegisterPage` hold every locator. `TamashaBot` reads like a story: search, pick, register.
- **No `Thread.sleep`.** Every wait is a `WebDriverWait` for a real condition: `data-ready="true"`, or an error or a code appearing.
- **The decision is plain Java.** `EventPicker` has its own fast tests and knows nothing about browsers.
- **The bot reports the page's own error messages**, so a failed run tells you exactly what the form didn't like.
