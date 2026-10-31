# Day 47 - Tamasha Bot: Driving a Browser with Selenium

> Some websites only work in a real browser: content loads late, forms check themselves with JavaScript. Selenium lets Java drive Chrome like a person would, but faster and without getting bored.

## 📖 The story

*Tamasha* is an events site: search for gigs, open one, fill in a registration form. Its results appear only after a slow network call, and the form is checked by JavaScript, so jsoup (which can't run JavaScript) sees an empty page. Today's bot opens a real Chrome, searches for jazz in Nairobi, picks the cheapest event within budget, and registers:

```
$ mvn -q compile exec:java
Found 2 jazz events in Nairobi:
  Jioni Jazz Night       2026-12-12  KES 1,800
  Sunday Jazz Brunch     2026-12-06  KES 1,200
Registering for Sunday Jazz Brunch…
Done! Confirmation code: TAM-102-2-WK
```

**Tamasha is a practice site** that starts on your own computer when the bot runs (`PracticeSite`). Every bot in Days 47–53 works on a practice site like this one. Automating real services, such as social media, job sites or shops, usually breaks their terms of use, and can get accounts banned or hurt other people's experience.

## 🎯 Learning Objectives
- Set up a Maven project with Selenium, your first `pom.xml` before Day 54's Spring Boot
- Open pages, find elements, type, click and choose from dropdowns with WebDriver
- Wait for conditions with `WebDriverWait` instead of guessing with `Thread.sleep`
- Organise a bot with **page objects**
- Keep decisions in plain Java you can test without a browser
- Run browser tests headless, and skip them cleanly where there's no browser

## 🧰 What you'll use

**Maven** downloads libraries for you. The `pom.xml` lists them:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.50.0</version>
</dependency>
```

**Where Maven puts things.** If you've used npm, you'll look for a `node_modules` folder. Maven doesn't make one. It downloads each library version once into `~/.m2/repository`, a cache every project on your computer shares, so there's nothing to install after a `git clone`: the first `mvn test` fetches what's missing. What Maven builds (compiled classes, test reports, the `.jar`) goes in `target/` inside the project. `target/` is gitignored and `mvn clean` deletes it.

| npm | Maven |
|---|---|
| `package.json` lists your dependencies | `pom.xml` |
| `package-lock.json` pins exact versions | the `pom.xml` already names exact versions |
| `node_modules/`, one per project, gitignored | `~/.m2/repository`, one per computer, outside the repo |
| `dist/` or `build/`, gitignored | `target/`, gitignored |

Days 1–46 didn't need any of this, because their few jars are committed in `lib/`. That's fine for a handful of libraries; past that, a build tool keeps the versions in one file instead of the jars in the repo.

Install [Maven](https://maven.apache.org/download.cgi) and Google Chrome. Selenium finds the right ChromeDriver by itself the first time it runs.

**Driving the browser**:

```java
WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
driver.get("http://127.0.0.1:8080/");
driver.findElement(By.id("q")).sendKeys("jazz");
new Select(driver.findElement(By.id("city"))).selectByValue("Nairobi");
driver.findElement(By.id("search")).click();
new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.attributeToBe(By.id("results"), "data-ready", "true"));
driver.quit();   // always, or Chrome processes pile up
```

**Page objects.** One class per page, holding its locators and actions. When the site changes, you fix one class, not every bot.

## 🪜 Steps

1. **Exercise 1: Locators** (`exercise1/`). Write robust CSS selectors, checked with jsoup.
2. **Exercise 2: How a wait works** (`exercise2/`). Write your own polling wait, tested with a fake clock.
3. **Project: Tamasha Bot** (`_start/`, a Maven project).
   1. `EventPicker.pick`: the cheapest event on or after a date, within budget.
   2. `SearchPage.search`: type, choose, click, **wait**, then read every card.
   3. `RegisterPage.register`: fill, tick, submit, wait for an error or a code.
   4. Run it with `--watch` and see Chrome do it.

## 🧪 Run it and test it

The project needs Maven and Chrome. From `day47/_end` (or `_start`):

```bash
mvn test                                   # all tests; browser tests skip without Chrome
mvn -q compile exec:java                   # run the bot headless
mvn -q compile exec:java -Dexec.args=--watch   # run it in a visible window
```

The exercises are plain Java. From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day47/exercise1/src day47/exercise1/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day47/exercise1/src:lib/*" --scan-classpath
```

## 🆘 Stuck?

- **`NoSuchElementException`** right after a click: the element isn't there *yet*. Wait for it.
- **`Cannot locate option with value`**: `selectByValue` needs `value="..."` on the `<option>`; `selectByVisibleText` uses the text.
- **`SessionNotCreatedException`**: Chrome isn't installed, or Selenium couldn't download a matching driver (it needs the internet the first time).
- **Chrome windows pile up**: call `driver.quit()` in a `finally` block.
- **Tests pass alone but fail together**: each test needs its own fresh page. `BrowserTest` starts a new browser for every test.

## ✅ Checklist
- [ ] Built and ran a Maven project
- [ ] Found elements by id and CSS selector, typed, clicked and selected
- [ ] Used `WebDriverWait`, not `Thread.sleep`
- [ ] Organised the bot into page objects
- [ ] Tested the decision logic without a browser
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git (not the `target/` folder)

## ➡️ Next

Day 48 gives the bot a goal and a strategy: play a clicker game on a practice site, and decide what to buy to grow fastest.
