# Day 38 - Exercise 1: Your first table

Write three methods in `src/FirstTable.java`, each taking an open `Connection`:

| Method | SQL it runs |
|---|---|
| `createTable(db)` | `CREATE TABLE IF NOT EXISTS members (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(60) NOT NULL, joined DATE NOT NULL)` |
| `addMember(db, name, joined)` | an `INSERT` with `?` placeholders |
| `countMembers(db)` | `SELECT COUNT(*) FROM members` |

`main` connects to `jdbc:h2:mem:club`, creates the table, adds three gym members and prints the count. The tests use their own in-memory database.

## Files

- `src/FirstTable.java`: your code goes here.
- `src/FirstTableTest.java`: the tests.
- `solution/FirstTable.java`: one answer.

## 📌 Stuck?

- `db.createStatement().execute(sql)` is fine for SQL with no values in it, like `CREATE TABLE`.
- For values, use `db.prepareStatement(sql)`, then `setString(1, ...)`, `setObject(2, localDate)` and `executeUpdate()`.
- `executeQuery()` gives a `ResultSet`; call `next()` once, then `getInt(1)` for the count.
- Wrap every statement in try-with-resources so it's closed.
