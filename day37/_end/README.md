# Day 37 - Solution

The finished Streak Garden, checked by `StreakGardenTest.java`.

Things worth noticing:

- **A `TreeSet` keeps dates sorted**, so `longestStreak` just walks them in order and never needs to sort.
- **"Not done yet today"** is a single line at the start of `currentStreak`. Without it, the tracker would tell everyone their streak was broken every morning.
- **`Garden.render` only needs the Monday of the first week.** Every cell is `firstMonday + week weeks + row days`.
- **`HabitStore.load` returns an empty map for a missing file**, so the first ever run just works.
- **`main` refuses dates in the future**, because a habit tracker you can cheat isn't much use.
