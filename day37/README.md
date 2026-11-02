# Day 37 - Streak Garden: A Habit Tracker

> Read 10 pages, drink 2 litres of water, practise Java for an hour. Track your habits, count your streaks, and watch your garden grow.

## 📖 The story

You're 37 days into a 100-day course, so you already know a streak keeps you going. Today you'll build a habit tracker for the terminal. Every day you mark what you did, and it shows a calendar that "grows" like a garden: empty soil for days you missed, flowers for days you showed up.

```
$ java StreakGarden done "Java practice"
✔ Java practice done for Sat 3 Oct. Streak: 14 days 🔥

$ java StreakGarden show
Java practice    streak 14 · best 14 · 57% of the last 30 days
     Mon  · · ✿ ✿ ✿ ✿
     Tue  · ✿ ✿ · ✿ ✿
     Wed  · ✿ · · ✿ ✿
     Thu  · ✿ · · ✿ ✿
     Fri  · · · · ✿ ✿
     Sat  · · · · ✿ ✿
     Sun  · · · ✿ ✿
```

Your habits are saved in `habits.json`, so they're still there tomorrow.

## 🎯 Learning Objectives
- Model data with classes and records: a `Habit` holds a set of dates
- Count streaks by walking backwards through days
- Calculate completion rates over a window of time
- Draw a calendar grid in the terminal, one column per week
- Save to and load from a JSON file
- Build a small command-line interface from `args`

## 🧰 Java you'll use

**A `Set<LocalDate>`** is the right shape for "days I did it": each day counts once, and `contains` is fast.

**Walking backwards through days** is the core of a streak:

```java
LocalDate day = today;
int streak = 0;
while (done.contains(day)) {
    streak++;
    day = day.minusDays(1);
}
```

One subtlety: at 8am you haven't read your 10 pages *yet*, but your streak isn't broken. If today isn't done, start counting from yesterday.

**Weeks as columns.** To draw a calendar, find the Monday of the earliest week (`date.with(DayOfWeek.MONDAY)`), then fill rows Monday to Sunday and columns week by week. Days in the future show as blank.

**Saving to JSON** with `org.json`:

```java
JSONObject root = new JSONObject();
root.put("Java practice", new JSONArray(List.of("2026-10-01", "2026-10-02")));
Files.writeString(Path.of("habits.json"), root.toString(2));
```

## 🪜 Steps

1. **Exercise 1: Streaks** (`exercise1/`). Current and longest streaks from a set of dates.
2. **Exercise 2: Rates and best days** (`exercise2/`). Completion rate over the last *n* days, and which weekday you're best at.
3. **Project: Streak Garden** (`_start/src/`).
   1. `Habit`: `markDone(date)`, `currentStreak(today)`, `longestStreak()` and `rate(today, days)`.
   2. `Garden.render(habit, today, weeks)`: the grid in the example. `✿` for done, `·` for missed, and a blank for days after `today`.
   3. `HabitStore.save` and `load`: the habits as `{"name": ["2026-10-01", ...]}`. Loading a file that doesn't exist gives no habits, not an error.
   4. `StreakGarden.main`: the commands `add <name>`, `done <name> [yyyy-mm-dd]` and `show`.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day37/_end/src day37/_end/src/*.java
cd day37/_end/src
java -cp ".:../../../lib/*" StreakGarden add "Java practice"
java -cp ".:../../../lib/*" StreakGarden done "Java practice"
java -cp ".:../../../lib/*" StreakGarden done "Java practice" 2026-10-01
java -cp ".:../../../lib/*" StreakGarden show
cd ../../..
java -cp "day37/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 🆘 Stuck?

- **The streak is 0 every morning**: if today isn't done yet, count from yesterday.
- **The longest streak is too long**: sort the dates first, then count runs where each date is exactly one day after the one before.
- **The calendar's columns are off by one**: start from the Monday on or before the first day you show: `start.with(DayOfWeek.MONDAY)`. For a Sunday that goes *back* six days, which is what you want.
- **`habits.json` appears in a strange folder**: relative paths start from where you ran `java`. That's why the commands above `cd` into `src` first.

## ✅ Checklist
- [ ] Counted current and longest streaks, including "not done yet today"
- [ ] Calculated a completion rate
- [ ] Drew a week-by-week calendar in the terminal
- [ ] Saved and reloaded habits as JSON
- [ ] Built `add`, `done` and `show` commands
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 38 swaps a JSON file for a real database: log your workouts with SQL and JDBC.
