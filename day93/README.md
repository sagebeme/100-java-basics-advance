# Day 93 - Portfolio Project: Game Automation

> Browser automation is how teams test web apps without clicking through them by hand, and how bots play games. Today's bot opens a real Chrome window (an invisible one), plays Rock Paper Scissors against a web page, and reports how its strategy did.

## 📖 The story

Mwende is a QA tester at a fintech startup in Nairobi. Every release, she clicks through the same 40 screens to check nothing broke. Her lead tells her to learn **Selenium**, the tool that drives a real browser from code. To practise, she picks something small and fun: a Rock Paper Scissors page, and a bot that plays it.

```
Opened game page
Round 1: played ROCK, computer played scissors, outcome: win
Round 2: played ROCK, computer played paper, outcome: loss
Round 3: played SCISSORS, computer played paper, outcome: win
...
Wins: 7, Losses: 6, Ties: 7, Win rate: 35.0%
```

The game page is bundled with the project, so the bot never touches a real website, and the tests can run anywhere Chrome is installed.

## 🎯 Learning Objectives
- Drive a real browser with Selenium WebDriver: open a page, click, read text
- Run Chrome headless (no window), for tests and servers
- Wait for the page instead of sleeping
- Separate the *decision* (a `Strategy`) from the *doing* (the bot)
- Release external resources reliably with `AutoCloseable` and try-with-resources
- Test pure logic with plain unit tests, and the browser parts with a few real end-to-end tests

## 🧰 What you'll use

**Selenium basics:**

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless=new");
WebDriver driver = new ChromeDriver(options);       // Selenium finds a matching driver itself

driver.get(gamePage.toUri().toString());
driver.findElement(By.id("rock")).click();
String result = driver.findElement(By.id("result")).getText();
driver.quit();                                      // always: or Chrome processes pile up
```

**Waiting.** Pages change after events, sometimes after a delay. `WebDriverWait` checks a condition repeatedly until it's true or a timeout passes, which is faster and more reliable than `Thread.sleep`.

**Strategies as an interface:**

```java
public interface Strategy {
    Move nextMove(List<RoundResult> history);
}
```

`CyclingStrategy` plays rock, paper, scissors in turn. `CounterLastMoveStrategy` plays whatever beats the computer's last move. Both are tested without a browser, from a hand-made history.

**Honest results.** This computer picks at random, so no strategy can beat it in the long run: about a third wins, a third losses, a third ties. A strategy only helps against an opponent with a pattern. Try writing one for a computer that always repeats its last move, and watch the win rate jump.

**Clean-up.** `RpsGameBot` implements `AutoCloseable`, and `close()` quits the browser, so `try (RpsGameBot bot = ...)` never leaves Chrome running, even after an exception.

## 🪜 Steps

**Your brief** (the `day93/` folder is one finished version: build your own first, then compare).

1. `Move`, `RoundResult`, `Strategy`, and the two strategies.
2. `PerformanceSummary.from(history)` with the win rate, and `ActivityLog`.
3. `RpsGameBot`: open the bundled `rps-game.html` in headless Chrome, play rounds, close.
4. `GameAutomationApp`: play 20 rounds and print the log and summary.
5. Tests: the strategies and summary without a browser, then a real round, several rounds and the log against the real page.
6. Make it yours: give the page's computer a pattern and write a strategy that finds it, or point Selenium at a web app you built (Day 82 or 88) and test its forms.

## 🧪 Run it and test it

Needs JDK 21, Maven, and Google Chrome or Chromium. From the repository root:

```bash
cd day93
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **`SessionNotCreatedException`**: Chrome isn't installed, or Selenium couldn't download a matching driver (it needs the internet the first time).
- **`NoSuchElementException`**: the id is wrong, or the page hadn't finished loading. Check the HTML, and wait for the element.
- **Chrome processes left running after a crash**: call `driver.quit()` in `close()`, and use try-with-resources.
- **It works with a window but not headless**: give headless Chrome a window size (`--window-size=1280,800`); some layouts hide buttons on tiny screens.

## ✅ Checklist
- [ ] A bot that plays through a real browser, headless
- [ ] Strategies behind an interface, tested without a browser
- [ ] Waits instead of sleeps
- [ ] The browser always closed
- [ ] Beat a computer with a pattern, or tested one of my own web apps
- [ ] Committed code to Git

## ➡️ Next

Day 94 is another classic arcade game: Space Invaders.
