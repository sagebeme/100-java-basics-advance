# Day 52 - Picha Bot: Following Back, and Backing Off

> When a website says "slow down", a good bot slows down, and slows down more if it's told again.

## 📖 The story

*Picha* (photo) is a practice photo-sharing site. Neema has eight new followers. Some are friends, and some are obviously spam ("free_phones_4u", 0 posts). She wants to follow back the real ones. The site allows only three follows in ten seconds; more than that and it shows **"Slow down!"** and ignores you for a while:

```
$ mvn -q compile exec:java
Followed back: amina_designs, brian.k, wanjiru.codes, otieno_photos, kevin_mitumba, neema.art
Skipped as spam: free_phones_4u, xx_win_now_xx
Backed off 4 times when the site asked.
```

Real photo apps limit automated following too, and often lock accounts that do it. Their official APIs have rate limits, and the answer to a rate limit is always the same: **back off**.

## 🎯 Learning Objectives
- Detect when an action didn't work, instead of assuming it did
- Back off exponentially when a site refuses, and reset after a success
- Put a limit on retries
- Filter out spam with simple, testable rules
- Make browser tests fast by giving the practice site a short throttle window

## 🧰 What you'll use

**Exponential backoff**: wait 1 s after the first refusal, 2 s after the next, then 4, 8… up to a cap. It's what every well-behaved client does with an HTTP `429 Too Many Requests`.

```java
for (int attempt = 1; attempt <= maxAttempts; attempt++) {
    if (page.followBack(user)) { backoff.reset(); break; }
    Thread.sleep(backoff.nextDelay().toMillis());
}
```

**Checking the result.** After clicking, read the button's `aria-pressed`. If it's still `false`, the site refused.

## 🪜 Steps

1. **Exercise 1: Exponential backoff** (`exercise1/`).
2. **Exercise 2: Spotting spam accounts** (`exercise2/`).
3. **Project: Picha Bot** (`_start/`, a Maven project): `followBackRealPeople`, the loop that skips spam, follows real people and backs off when refused.

## 🧪 Run it and test it

From `day52/_end` (or `_start`), with Maven and Chrome:

```bash
mvn test
mvn -q compile exec:java     # takes about half a minute, because it waits when the site asks
```

## 🆘 Stuck?

- **Every follow after the third fails, forever**: you're clicking during the "slow down" period. Wait before trying again.
- **The bot waits much longer than needed**: call `backoff.reset()` after each success.
- **The test is slow**: open the site with `?window=1200` so the throttle lasts 1.2 seconds, not 10.

## ✅ Checklist
- [ ] Checked whether each action really worked
- [ ] Backed off exponentially, with a cap and a reset
- [ ] Limited the number of attempts
- [ ] Skipped spam with tested rules
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 53 is the last automation day: scrape rental listings and type them into a form, the classic "data entry" job, done by a bot.
