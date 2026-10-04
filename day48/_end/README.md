# Day 48 - Solution

The finished Chapati Clicker Bot. `mvn test` runs `StrategyTest` (no browser) and `ClickerBotTest` (headless Chrome).

Things worth noticing:

- **The strategy is plain Java.** Payback time, cost divided by income, decides what to buy, and it's tested in milliseconds.
- **Stale elements are handled in one place.** The page redraws ten times a second, so `GamePage.retrying` finds an element again if it was replaced mid-read.
- **The bot plays in bursts**: click, then buy until the strategy says stop. Checking the shop after every single click would spend more time reading than playing.
