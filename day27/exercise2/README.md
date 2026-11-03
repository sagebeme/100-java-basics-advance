# Day 27 - Exercise 2: A sign-up form that says everything at once

A boda boda riders' insurance scheme in Kakamega signs people up on a laptop at the stage. The worst forms show one error, you fix it, press the button, and get the next one. This one checks every field and shows each problem next to its field, all at once. The window, `src/SignupApp.java`, is written. Write the rules it uses, in `src/SignupForm.java`.

`validate(name, phone, idNumber, age)` returns a `Map` of field name to message, in the order the fields appear on the form. An empty map means the form is fine.

| Field | Key | Rule | Message when it's broken |
|---|---|---|---|
| Full name | `name` | At least 2 characters, not counting spaces at the ends | `Type your full name.` |
| Phone | `phone` | A Kenyan mobile, typed any usual way (see below) | `Use a number like 0712 345 678 or +254 712 345 678.` |
| National ID | `idNumber` | 7 or 8 digits | `A national ID number is 7 or 8 digits.` |
| Age | `age` | Whole years from 18 to 100 | Not a number: `Type your age in years, like 34.` Under 18: `You must be 18 or over to sign up.` Over 100: `Check your age: over 100?` |

`normalisePhone(phone)` turns the ways people type a number into one form, `0712345678`: spaces and dashes are dropped, and `+254` or `254` at the start becomes `0`. The result must be 10 digits starting `07` or `01`; anything else gives `null`.

A `null` in any field is a problem with that field, never a `NullPointerException`.

## Files

- `src/SignupForm.java`: your code goes here.
- `src/SignupFormTest.java`: the tests.
- `src/SignupApp.java`: the window, already written.
- `solution/SignupForm.java`: one answer.

`SignupFormTest` needs no JavaFX:

```bash
javac -cp "lib/*" -d day27/exercise2/src day27/exercise2/src/SignupForm.java day27/exercise2/src/SignupFormTest.java
java -cp "day27/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

Run `SignupApp` with the JavaFX SDK (see LEARNING_PATH.md) and try a form with every field wrong.

## 📌 Stuck?

- A `LinkedHashMap` remembers the order things were put in, so put the checks in form order.
- `phone.replaceAll("[\\s-]", "")` removes spaces and dashes. Then check `startsWith("+254")` before `startsWith("254")`.
- Check the age is digits (`matches("\\d{1,3}")`) before `Integer.parseInt`, or "seventeen" throws.
