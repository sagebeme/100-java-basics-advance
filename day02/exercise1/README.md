# Day 2 - Exercise 1: Member codes

Every farmer in the cooperative has a member code made from their initials and their member number, padded to four digits. The clerk types names in any case and with extra spaces, so clean them up first.

| Method | Example |
|---|---|
| `cleanName("  wanjiru KAMAU ")` | `"Wanjiru Kamau"` |
| `initials("Wanjiru Kamau")` | `"WK"` |
| `memberCode("wanjiru kamau", 42)` | `"WK-0042"` |

Names always have two parts: a first name and a surname.

## Files

- `src/MemberCodes.java`: your code goes here.
- `src/MemberCodesTest.java`: the tests.
- `solution/MemberCodes.java`: one answer.

## 📌 Stuck?

- `trim()` removes spaces at both ends, and `toLowerCase()` / `toUpperCase()` change the case.
- `indexOf(' ')` finds the space between the two names, and `substring(start, end)` cuts out a piece.
- `charAt(0)` is the first letter. `String.valueOf(c)` turns a `char` back into a `String`.
- `String.format("%04d", 42)` gives `"0042"`.
