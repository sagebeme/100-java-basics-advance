# Day 35 - Solution

The finished SMS Alerts, checked by `SmsAlertsTest.java`.

Things worth noticing:

- **`AppConfig.load` takes the environment as a `Map`.** `main` passes `System.getenv()`; the tests pass `Map.of(...)`. Nothing in the tests depends on how the computer running them is set up.
- **One error lists everything missing.** Fixing settings one failed run at a time is miserable.
- **The mask has a fixed length**, so it doesn't even reveal how long the key is.
- **`config.properties` is safe to commit, and `config.example.properties` documents the secrets** with placeholder values. That's the usual pattern in real projects (you'll see `.env.example` in the TypeScript world).
- **`SmsGateway` is an interface**, just like Day 32's `Mailer`. A real provider is one new class away.
