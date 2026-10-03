# Day 42 - Exercise 2: A weekly timetable (grid)

`src/timetable.html` is a week of a youth football club's sessions. Write `src/timetable.css` to lay it out with **CSS grid**:

- `.week`: seven equal columns, one per day: `grid-template-columns: repeat(7, 1fr)`, with a `gap`
- `.session.long`: a long session spans **two** days with `grid-column: span 2`
- `@media (max-width: 40rem)`: on a phone, `.week` becomes a single column, so the days stack

## Files

- `src/timetable.html`: the timetable (don't change it).
- `src/timetable.css`: your styles (it starts nearly empty).
- `src/TimetableStyleTest.java`: the checks.
- `solution/timetable.css`: one design.

## 📌 Stuck?

`1fr` means "one share of the free space", so seven `1fr` columns are always equal. Inside the `@media` block, write `.week { grid-template-columns: 1fr; }` and, because one column can't hold a two-column span, `.session.long { grid-column: auto; }`.
