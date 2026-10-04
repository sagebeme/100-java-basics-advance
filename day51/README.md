# Day 51 - Kasi Check: An Internet Speed Bot

> You pay for 20 Mbps. Is that what you get? Measure it properly, keep a record, and complain with evidence.

## 📖 The story

Brian's home internet feels slow every evening, and the provider's help desk always says "it looks fine on our side". Today's bot runs a speed test three times, takes the **median** download speed, compares it with the plan, and if it's under 70%, opens a help-desk ticket with all the numbers:

```
$ mvn -q compile exec:java
Median download: 6.2 Mbps on a 20 Mbps plan.
Under 70% of the plan. Opened ticket KC-517-271:
My plan is 20 Mbps, but I measured a median download speed of 6.2 Mbps (31% of the plan) over 3 tests:
  test 1: 6.2 Mbps down, 1.6 Mbps up, 18 ms ping
  test 2: 7.8 Mbps down, 1.9 Mbps up, 21 ms ping
  test 3: 5.9 Mbps down, 1.5 Mbps up, 24 ms ping
Please check my line.
```

The speed test and the help desk are **practice pages** with simulated results (`?profile=slow`, `typical` or `fast`). Run `mvn -q compile exec:java -Dexec.args=fast` to see a line that's fine.

## 🎯 Learning Objectives
- Wait for a long-running task in a page, ignoring the animation along the way
- Read final results from data attributes
- Use the median to make a fair judgement from noisy measurements
- Write a clear, evidence-based message from data
- Log every measurement to a CSV file

## 🧰 What you'll use

**Waiting for a finish line.** The dial's number moves for two seconds. Reading it then is a random snapshot. Wait for a clear signal instead:

```java
new WebDriverWait(driver, Duration.ofSeconds(15))
        .until(d -> "done".equals(results.getDomAttribute("data-state")));
```

**The median**: sort the numbers and take the middle one. One test that ran while someone was streaming can't drag the median down the way it drags an average down.

## 🪜 Steps

1. **Exercise 1: Middle values** (`exercise1/`). Median and trimmed mean.
2. **Exercise 2: Bits and bytes** (`exercise2/`). Why 20 Mbps isn't 20 MB a second.
3. **Project: Kasi Check** (`_start/`, a Maven project).
   1. `Verdict.median` and `shouldComplain`.
   2. `SpeedTestPage.runOnce`: click, wait for the finish, read the data attributes.
   3. Run it for all three profiles.

## 🧪 Run it and test it

From `day51/_end` (or `_start`), with Maven and Chrome:

```bash
mvn test
mvn -q compile exec:java                     # the slow line
mvn -q compile exec:java -Dexec.args=fast    # a line that's fine
```

## 🆘 Stuck?

- **Results are a random number between 0 and the real speed**: you read the dial during the animation. Wait for `data-state="done"`.
- **The second run reads the first run's result**: wait until GO is enabled again as well.
- **`1.9` instead of `2.0`**: JavaScript's `toFixed` rounds binary fractions, so 1.95 can show as 1.9. Test against what the page really shows.

## ✅ Checklist
- [ ] Waited for a long task to finish, not a fixed time
- [ ] Judged with the median of several runs
- [ ] Opened a ticket with the evidence
- [ ] Logged every result
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 52 automates follow-backs on a practice photo site, and learns how to back off when the site says "slow down".
