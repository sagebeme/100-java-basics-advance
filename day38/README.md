# Day 38 - Mazoezi Log: A Workout Tracker with a Database

> "Mazoezi" means exercise. Log every run, every skipping session and every Sunday football match in a real SQL database, and ask it questions.

## 📖 The story

Brian plays five-a-side on Sundays, jogs in Karura Forest twice a week and skips rope when it rains. He wants to know: how much did I actually train this week? A JSON file (Day 37) gets awkward once you want to ask questions like that. A **database** is built for it.

```
$ java Mazoezi log "run 30, skipping 15"
Logged 2 workouts for Sat 3 Oct: run 30 min (343 kcal), skipping 15 min (215 kcal)

$ java Mazoezi week
This week (Mon 28 Sep – Sun 4 Oct)
  football     60 min   490 kcal
  run          90 min  1029 kcal
  skipping     15 min   215 kcal
  total       165 min  1734 kcal
```

The database is **H2**, a full SQL database written in Java. It runs inside your program and keeps its data in a file next to it, so there's no server to install. Its jar is already in `lib/`.

## 🎯 Learning Objectives
- Connect to a database with **JDBC**: `DriverManager`, `Connection`, `PreparedStatement`, `ResultSet`
- Write SQL: `CREATE TABLE`, `INSERT`, `SELECT` with `WHERE`, `GROUP BY` and `ORDER BY`, and `DELETE`
- Always pass values with `?` placeholders, and understand why (SQL injection)
- Close database resources with try-with-resources
- Test database code against a fresh in-memory database

## 🧰 Java you'll use

**Connecting.** A JDBC URL says which database and where:

```java
Connection db = DriverManager.getConnection("jdbc:h2:./mazoezi");            // a file: mazoezi.mv.db
Connection test = DriverManager.getConnection("jdbc:h2:mem:test");           // in memory, gone when closed
```

**Changing data** with a `PreparedStatement`. Each `?` is filled in separately, so the database never mixes your values up with the SQL itself:

```java
try (PreparedStatement insert = db.prepareStatement(
        "INSERT INTO workouts (workout_date, activity, minutes, kcal) VALUES (?, ?, ?, ?)")) {
    insert.setObject(1, LocalDate.of(2026, 10, 3));
    insert.setString(2, "run");
    insert.setInt(3, 30);
    insert.setInt(4, 343);
    insert.executeUpdate();
}
```

**Asking questions** returns a `ResultSet`, which you walk row by row:

```java
try (PreparedStatement q = db.prepareStatement(
        "SELECT activity, SUM(minutes) AS mins FROM workouts WHERE workout_date BETWEEN ? AND ? GROUP BY activity ORDER BY activity")) {
    q.setObject(1, monday);
    q.setObject(2, sunday);
    try (ResultSet rows = q.executeQuery()) {
        while (rows.next()) {
            System.out.println(rows.getString("activity") + " " + rows.getInt("mins"));
        }
    }
}
```

**Why `?` and never `+`.** If you build SQL by gluing in text a user typed, that text can *become* SQL. Someone types `run'); DROP TABLE workouts; --` and your table is gone. With `?` it's just an odd activity name. Exercise 2 proves it.

**Calories** use MET values ("metabolic equivalents", from published exercise research): `kcal = MET × body weight in kg × hours`. Running is about 9.8, walking 3.5, skipping 12.3.

## 🪜 Steps

1. **Exercise 1: Your first table** (`exercise1/`). Create a table, insert rows and count them.
2. **Exercise 2: Questions in SQL** (`exercise2/`). `WHERE`, `GROUP BY`, `ORDER BY`, and a failed injection attack.
3. **Project: Mazoezi Log** (`_start/src/`).
   1. `Activities.parse("run 30, skipping 15")`: a list of `Workout`s with calories worked out from the MET table. Unknown activities and bad minutes are errors with a clear message.
   2. `WorkoutDb.createTable()`: `IF NOT EXISTS`, so it's safe to run every time.
   3. `add`, `forDay`, `totalsBetween` (one row per activity: minutes and kcal, sorted by name) and `delete`.
   4. `Mazoezi.main`: the commands `log "<workouts>"`, `today` and `week`.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day38/_end/src day38/_end/src/*.java
cd day38/_end/src
java -cp ".:../../../lib/*" Mazoezi log "run 30, skipping 15"
java -cp ".:../../../lib/*" Mazoezi week
cd ../../..
java -cp "day38/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

The tests use `jdbc:h2:mem:...` databases, so they never touch your real log. Your log lives in `mazoezi.mv.db`, which `.gitignore` keeps out of Git (`*.db` files are personal data).

## 🆘 Stuck?

- **`No suitable driver found for jdbc:h2:...`**: the H2 jar isn't on the classpath. Check the `-cp` includes `lib/*`.
- **`Syntax error in SQL statement ... expected "identifier"`**: a column name is a reserved SQL word. `DAY`, `USER`, `YEAR`, `ORDER` and `VALUE` are common traps. That's why the date column is called `workout_date`.
- **`Table "WORKOUTS" not found`**: call `createTable()` before anything else. In-memory databases start empty every time.
- **`Database may be already in use`**: another program (or an H2 console) has the file open. Close it, or close your `Connection` properly.
- **Totals include last week**: `BETWEEN` includes both ends. Use the Monday and the Sunday of the week, with `with(DayOfWeek.MONDAY)` and `plusDays(6)`.

## ✅ Checklist
- [ ] Connected with JDBC and created a table
- [ ] Inserted rows with a `PreparedStatement` and `?` placeholders
- [ ] Queried with `WHERE`, `GROUP BY` and `ORDER BY`
- [ ] Saw an SQL injection attempt fail
- [ ] Closed every resource with try-with-resources
- [ ] The tests pass
- [ ] Committed code to Git (and not the `.mv.db` file)

## ➡️ Next

Day 39 starts a two-day capstone: find cheap flights out of Nairobi for the long weekend.
