# Day 50 - Solution

The finished Mtaa Feed Bot. `mvn test` runs `RulesTest` (no browser) and `FeedBotTest` (headless Chrome).

Things worth noticing:

- **Every action asks the rate limiter first.** Posts and likes share one budget, and anything over it is counted as held back, not forced through.
- **Likes have rules**: someone you follow, an interest of yours, not yourself, not already liked. A bot that likes everything is noise.
- **The queue never posts twice**: the bot reads its own earlier posts from the feed first.
- **Credentials come from the environment** (Day 35), with practice defaults.
