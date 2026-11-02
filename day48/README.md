# Day 48 - Chapati Clicker Bot: A Game-Playing Bot

> A bot that only clicks is a machine. A bot that decides what to buy is a strategy. Write both, and prove which strategy wins before you run it.

## 📖 The story

*Chapati Clicker* is a little practice game: click the chapati to cook one, then spend chapatis on upgrades (an extra pan, a helper, a bigger jiko, a second stall) that cook for you every second. Each upgrade costs 15% more than the last one. Today's bot plays it:

```
$ mvn -q compile exec:java
In 41.2 seconds: 12 upgrades bought, 1188 chapatis in the pan, 47 a second.
```

Clicking is the easy part. The interesting question is **what to buy**: the cheapest thing? The biggest? The bot uses **payback time**, cost ÷ income, which is how long an upgrade takes to earn its price back, and sometimes it decides to save up instead.

The game is a practice page that runs on your computer, like Day 47's site. Botting real online games usually breaks their rules and spoils them for other players.

## 🎯 Learning Objectives
- Read live state from a page that keeps changing
- Handle `StaleElementReferenceException` when the page redraws an element you're holding
- Turn a goal into a strategy: payback time and "save up or buy now"
- Simulate a strategy in plain Java before running it in a browser
- Keep the strategy separate from the clicking, and test each one

## 🧰 What you'll use

**Stale elements.** A `WebElement` points at one exact element. If the page replaces it (and this game redraws ten times a second), using your old reference throws `StaleElementReferenceException`. The fix is to find it again:

```java
static <T> T retrying(Supplier<T> read) {
    for (int attempt = 1; ; attempt++) {
        try { return read.get(); }
        catch (StaleElementReferenceException e) { if (attempt == 3) throw e; }
    }
}
long chapatis = retrying(() -> Long.parseLong(driver.findElement(By.id("count")).getText()));
```

**Payback time**: an upgrade costing 100 that earns 5 a second pays for itself in 20 seconds. Lower is better.

**Data attributes** make pages easy for bots *and* tests to read: each upgrade button carries `data-cost`, `data-rate` and `data-owned`.

## 🪜 Steps

1. **Exercise 1: Rising prices** (`exercise1/`). The 15% rule, and the cost of buying several.
2. **Exercise 2: Which strategy wins?** (`exercise2/`). Simulate ten minutes of play without a browser.
3. **Project: Chapati Clicker Bot** (`_start/`, a Maven project).
   1. `Strategy.bestBuy`: the affordable upgrade with the shortest payback.
   2. `Strategy.shouldSaveFor`: is waiting for a better deal quicker than buying now?
   3. `ClickerBot.play`: click in bursts, and after each burst buy until the strategy says stop.
   4. Watch it with `--watch`.

## 🧪 Run it and test it

From `day48/_end` (or `_start`), with Maven and Chrome installed:

```bash
mvn test
mvn -q compile exec:java
mvn -q compile exec:java -Dexec.args=--watch
```

The exercises are plain Java. From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day48/exercise2/src day48/exercise2/src/*.java
java -cp "day48/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 🆘 Stuck?

- **`StaleElementReferenceException`**: find the element again rather than keeping it in a field. `GamePage.retrying` shows the pattern.
- **The bot never buys anything**: it may be saving up forever. `shouldSaveFor` must return `false` when there's no income.
- **The bot is slow**: every `findElement` is a round trip to Chrome. Read the shop once per burst, not once per click.

## ✅ Checklist
- [ ] Read changing state from a live page
- [ ] Handled stale elements
- [ ] Wrote and tested a payback-time strategy
- [ ] Simulated two strategies and compared them
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 49 automates a job application, on a practice jobs board: filter listings by your skills, then fill in multi-step forms.
