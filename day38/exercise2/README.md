# Day 38 - Exercise 2: Questions in SQL

`src/GymQueries.java` gets a `Connection` to a table that's already filled in:

```
sessions(id, member VARCHAR, activity VARCHAR, minutes INT)
```

Write three queries, each with `?` placeholders for its values:

| Method | Answers |
|---|---|
| `longSessions(db, minMinutes)` | members' names with a session of at least `minMinutes`, each name once, A to Z |
| `minutesByActivity(db)` | a `LinkedHashMap` of activity → total minutes, the biggest total first |
| `sessionsFor(db, member)` | how many sessions this member has |

Then look at the last test, `injectionAttemptIsJustAStrangeName`. It passes the name `x' OR '1'='1` to `sessionsFor`. If you had built the SQL with `+`, the `WHERE` would be true for every row and you'd get the total for the whole gym. With `?` the answer is `0`, because nobody has that name.

## Files

- `src/GymQueries.java`: your code goes here.
- `src/GymQueriesTest.java`: the tests (they create and fill the table).
- `solution/GymQueries.java`: one answer.

## 📌 Stuck?

- `SELECT DISTINCT member FROM sessions WHERE minutes >= ? ORDER BY member`
- `SELECT activity, SUM(minutes) AS total FROM sessions GROUP BY activity ORDER BY total DESC`
- `SELECT COUNT(*) FROM sessions WHERE member = ?`
