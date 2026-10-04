# Day 52 - Solution

The finished Picha Bot. `mvn test` runs `RulesTest` (no browser) and `PichaBotTest` (headless Chrome, with a short throttle window so it's quick).

Things worth noticing:

- **The bot notices refusals.** After every click it checks whether the follow really happened, instead of assuming it did.
- **Backoff doubles, and resets after success.** Hammering a site that asked you to slow down is how accounts get banned and servers get overloaded.
- **There's a limit on attempts**, so one stubborn refusal can't keep the bot waiting forever.
- **Spam is skipped and reported**, so a person can check the list.
