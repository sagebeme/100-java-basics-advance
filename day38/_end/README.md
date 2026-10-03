# Day 38 - Solution

The finished Mazoezi Log, checked by `MazoeziTest.java`.

Things worth noticing:

- **All the SQL is in `WorkoutDb`.** If you moved to PostgreSQL one day, that's the only class you'd touch.
- **Every value goes through `?`.** There isn't a single `+` gluing user input into SQL.
- **`RETURN_GENERATED_KEYS`** gets back the id the database chose, so `add` returns a complete `Workout`.
- **The table checks `minutes > 0` too.** Java validates first, and the database refuses bad data even if some other program skips that check.
- **Each test gets its own `jdbc:h2:mem:` database**, named with `System.nanoTime()`, so no test can see another's rows.
