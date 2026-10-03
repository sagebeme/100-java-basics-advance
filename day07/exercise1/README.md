# Day 7 - Exercise 1: The menu

The clerk types fast and makes mistakes. Write `parseChoice(String input, int options)`: if `input` is a whole number from `1` to `options`, return it; otherwise return `-1`. Ignore spaces around it.

| Call | Result |
|---|---|
| `parseChoice("2", 4)` | `2` |
| `parseChoice(" 4 ", 4)` | `4` |
| `parseChoice("5", 4)` | `-1` |
| `parseChoice("two", 4)` | `-1` |
| `parseChoice("", 4)` | `-1` |

You can't call `Integer.parseInt("two")`, because it crashes the program. Check that every character is a digit first.

## Files

- `src/MenuChoice.java`: your code goes here.
- `src/MenuChoiceTest.java`: the tests.
- `solution/MenuChoice.java`: one answer.

## 📌 Stuck?

- `Character.isDigit(c)` is `true` for `'0'` to `'9'`.
- Loop over `text.toCharArray()`, and return `-1` the moment you see something that isn't a digit.
- An empty string has no characters, so the loop finds nothing wrong. Check for it separately.
