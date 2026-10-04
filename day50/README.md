# Day 50 - Mtaa Feed Bot: A Polite Social Media Bot

> Schedule posts, like what your friends share, and never become the account everyone mutes.

## 📖 The story

*Mtaa* is a practice social network that runs on your own computer: a login page and a feed that loads more posts as you go. Your bot logs in, publishes the posts you wrote ahead of time in `posts.txt` (only the ones that are due), and likes posts from people you follow about things you care about:

```
$ mvn -q compile exec:java
Posted 1, liked 4, held back 0 (rate limit).
```

Every action goes through a **rate limiter**, so the bot never does more than five things a minute, however much it wants to.

**Real social networks forbid automated liking and following in their terms**, and detect it. Bots that post on a schedule usually have to use the network's official API, with its own rules. The skills here (schedules, rules, rate limits) are exactly what those official APIs expect from you.

## 🎯 Learning Objectives
- Log in through a form, and read the error when login fails
- Load a feed that arrives in batches ("Load more"), with a safety limit
- Publish from a schedule, without repeating yourself
- Decide what to like with clear, tested rules
- Limit a bot's actions with a sliding-window rate limiter
- Keep credentials in environment variables

## 🧰 What you'll use

**A sliding-window rate limiter**: remember when each recent action happened, forget the ones older than a minute, and allow a new action only if fewer than the limit remain.

**Waiting for "more"**: after clicking "Load more", wait until the number of posts goes up. The feed sets `data-count`, which makes that easy to check.

**A schedule file**, one post per line:

```
2026-10-04T07:30 | Habari za asubuhi! Day 50 of 100 Days of Java ☕
```

## 🪜 Steps

1. **Exercise 1: A rate limiter** (`exercise1/`). Five actions a minute, tested with a fake clock.
2. **Exercise 2: Check before you post** (`exercise2/`). Too long, too many hashtags, shouting, repeats.
3. **Project: Mtaa Feed Bot** (`_start/`, a Maven project).
   1. `LikeRules.shouldLike`.
   2. `PostQueue.due`.
   3. `FeedBot.run`: load everything, publish what's due, like what fits, and ask the limiter before every action.

## 🧪 Run it and test it

From `day50/_end` (or `_start`), with Maven and Chrome:

```bash
mvn test
mvn -q compile exec:java            # creates posts.txt the first time; edit it and run again
```

The exercises are plain Java: compile and test `day50/exercise1/src` with `lib/*` on the classpath, as on earlier days.

## 🆘 Stuck?

- **The bot likes its own posts**: check the author against your username.
- **"Load more" loops forever**: stop when the button is disabled, and keep a maximum number of loads.
- **Posts go out twice**: read your earlier posts from the feed before publishing.

## ✅ Checklist
- [ ] Logged in, and handled a failed login
- [ ] Loaded a batched feed with a safety limit
- [ ] Published scheduled posts without repeats
- [ ] Liked by rules, through a rate limiter
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 51 measures your internet speed, compares it with what you pay for, and drafts a complaint, on a practice speed-test site.
