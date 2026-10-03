# Day 40 - Exercise 1: The sign-up form

A form that complains about one problem at a time is maddening: fix the email, submit, and *now* it says the name is missing. Write `problems(String name, String email, String confirmEmail, boolean agreed)` in `src/SignUpForm.java`. It returns a list of **every** problem, in this order:

| Check | Message |
|---|---|
| name is blank | `Please enter your name.` |
| email isn't valid | `Please enter a valid email address.` |
| the two emails differ (ignoring capitals and spaces) | `The email addresses don't match.` |
| `agreed` is false | `Please agree to receive deal emails.` |

A perfect form gives an empty list. Don't say the emails differ when the first one is already invalid, because one message about the email is enough.

## Files

- `src/SignUpForm.java`: your code goes here.
- `src/SignUpFormTest.java`: the tests.
- `solution/SignUpForm.java`: one answer.

## 📌 Stuck?

Start with an empty `ArrayList<String>` and add to it with a separate `if` for each check, not `else if`, because you want every problem. A simple email check: `email.trim().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")`.
