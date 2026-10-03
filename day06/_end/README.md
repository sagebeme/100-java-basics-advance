# Day 6 - Solution

`src/GradeLabeller.java` is one finished version, checked by `src/GradeLabellerTest.java`.

Things worth noticing:

- **The size rules live in one place.** `grade(screen, peaberry)` only adds the peaberry rule and hands everything else to `grade(screen)`. If the AA line ever moves to screen 18, you change one line.
- **`label` builds on `grade`** instead of working the grade out again.
- **`main` is short** because the methods do the work. It only asks questions and prints answers, and that's the part a test can't easily check.
