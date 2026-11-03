# Day 16 - Exercise 1: A chama member card

Wanjiru's chama keeps every member in a notebook, and the treasurer keeps getting phone numbers wrong. Write the class that replaces the notebook: `src/Member.java`.

| Part | What it does |
|---|---|
| `new Member(name, phone)` | A member with no savings yet. A bad phone number is refused (see below). |
| `getName()`, `getPhone()`, `getSavings()` | Read the fields. |
| `setPhone(phone)` | Changes the number, but only to a good one. |
| `contribute(shillings)` | Adds to the savings. `0` or less is refused. |
| `card()` | `"Wanjiru · 0712345678 · KES 1500 saved"` |

A good phone number is 10 digits starting with `07` or `01`. Anything else, in the constructor or in `setPhone`, throws `IllegalArgumentException`, and the old number stays.

There's deliberately **no** `setSavings`: the only way savings go up is a contribution. That's encapsulation. The fields are `private`, so nothing outside the class can skip the checks.

Then, in `main`, make Wanjiru, add contributions of 500 and 1000, and print her card.

## Files

- `src/Member.java`: your code goes here.
- `src/MemberTest.java`: the tests.
- `solution/Member.java`: one answer.

Run the tests from the repo root:

```bash
javac -cp "lib/*" -d day16/exercise1/src day16/exercise1/src/*.java
java -cp "day16/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Check the phone in one private method, `isValidPhone`, and call it from both the constructor and `setPhone`. `phone.matches("0[17]\\d{8}")` is the whole check.
- `throw new IllegalArgumentException("Not a phone number: " + phone);` stops the method, so the field is never changed.
