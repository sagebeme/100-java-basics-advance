# Day 4 - Exercise 1: The picking team

During the harvest, neighbours help each other pick. The team for the day is written down in order of arrival:

```java
String[] team = {"Wanjiru", "Brian", "Mama Njeri", "Otieno"};
```

Write four methods in `src/PickingTeam.java`:

| Method | Example with the team above |
|---|---|
| `firstToArrive(String[] team)` | `"Wanjiru"` |
| `lastToArrive(String[] team)` | `"Otieno"` |
| `teamSize(String[] team)` | `4` |
| `isOnTeam(String[] team, String name)` | `isOnTeam(team, "Brian")` is `true` |

## Files

- `src/PickingTeam.java`: your code goes here.
- `src/PickingTeamTest.java`: the tests.
- `solution/PickingTeam.java`: one answer.

## 📌 Stuck?

The last index is always `team.length - 1`. For `isOnTeam`, loop over every index and compare with `equals`. Return `true` as soon as you find a match, and `false` after the loop.
