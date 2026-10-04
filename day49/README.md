# Day 49 - Kazi Bot: Automating a Multi-Step Application

> Applying for jobs means the same name, email and phone number typed into form after form. Automate the boring part, and keep the judgement part yours.

## 📖 The story

*Kazi Board* (kazi means work) is a practice jobs site with tech jobs from made-up companies. The bot reads every listing, scores each against your skills, and applies to the best matches through a three-step form with a CV upload:

```
$ mvn -q compile exec:java
Applied: Junior Java Developer              Mfuko Payments   100% match, reference K-201-9
Applied: Java Intern                        Kilimo Tech      100% match, reference K-204-9
Applied: QA Automation Tester               Tamasha Events   100% match, reference K-205-9
```

It remembers every application in `applied.csv`, so running it again applies only to new jobs.

**Why a practice site?** Real job sites forbid bots in their terms. More importantly, an employer can tell a mass-produced application from a real one. Use what you learn here for your *own* forms and tests, and write real applications yourself.

## 🎯 Learning Objectives
- Score and filter jobs with plain, tested Java
- Fill a multi-step form, checking each step before moving on
- Upload a file with Selenium
- Tick exactly the right checkboxes, and choose from a dropdown
- Remember what the bot has done, so it never repeats itself
- Limit how much a bot does in one run

## 🧰 What you'll use

**Uploading a file**: type the file's full path into the file input:

```java
driver.findElement(By.id("cv")).sendKeys(Path.of("my-cv.txt").toAbsolutePath().toString());
```

**Checkboxes** are clicks that toggle. Click only when the current state is wrong:

```java
if (wanted != box.isSelected()) box.click();
```

**A step at a time.** After each "Next", read the page's error messages, and stop with them if there are any, before waiting for the next step to appear.

## 🪜 Steps

1. **Exercise 1: A safe template** (`exercise1/`). Fill `{{placeholders}}`, and refuse to send a letter with a gap in it.
2. **Exercise 2: A daily limit** (`exercise2/`). How many more is the bot allowed to send today?
3. **Project: Kazi Bot** (`_start/`, a Maven project).
   1. `JobMatcher.score` and `worthApplying`: skills, places, remote, already applied, best first.
   2. `ApplyPage.apply`: three steps, a dropdown, checkboxes and an upload.
   3. Run it twice and see the second run skip the jobs from the first.

## 🧪 Run it and test it

From `day49/_end` (or `_start`), with Maven and Chrome:

```bash
mvn test
mvn -q compile exec:java            # creates my-cv.txt and applied.csv; delete applied.csv to start over
```

The exercises are plain Java: compile and test `day49/exercise1/src` as on earlier days, with `lib/*` on the classpath.

## 🆘 Stuck?

- **`ElementNotInteractableException`**: you're typing into a step that's still hidden. Wait for the step to be visible.
- **The upload does nothing**: send an absolute path, and to the `<input type="file">` itself, not its label.
- **A skill is ticked that shouldn't be**: checkboxes remember their state. Compare with `isSelected()` first.

## ✅ Checklist
- [ ] Scored and filtered jobs in plain Java, with tests
- [ ] Filled a three-step form, checking each step
- [ ] Uploaded a file and set checkboxes and a dropdown
- [ ] Kept a ledger so the bot never repeats itself
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 50 schedules posts and replies on a practice social site, with rules that keep a bot polite.
