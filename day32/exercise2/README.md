# Day 32 - Exercise 2: Build an email

Before a program hands an email to a mail server, it has to check the address and write the message out as plain text. Write two methods in `src/EmailText.java`:

**`isValidAddress(String address)`**: a simple check, not a perfect one. `true` when there's exactly one `@`, at least one character before it, and a dot somewhere after it with characters on both sides, and no spaces anywhere.

| Address | Valid? |
|---|---|
| `wanjiku@example.com` | yes |
| `wanjiku.k@mail.co.ke` | yes |
| `wanjiku@localhost` | no (no dot after the `@`) |
| `@example.com` | no |
| `wan jiku@example.com` | no |
| `a@b@example.com` | no |

**`toMessageText(String to, String subject, String body)`**: the text a mail server receives, with each header on its own line, then a blank line, then the body:

```
To: wanjiku@example.com
Subject: Happy birthday!

Have a wonderful day.
```

If the address isn't valid, throw an `IllegalArgumentException` instead.

## Files

- `src/EmailText.java`: your code goes here.
- `src/EmailTextTest.java`: the tests.
- `solution/EmailText.java`: one answer.

## 📌 Stuck?

- `indexOf('@')` and `lastIndexOf('@')` are equal only when there's exactly one `@`.
- Split the address at the `@` and check each half separately; it's much easier than one long condition.
- A regular expression works too: `address.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")`. Try writing it with `indexOf` first.
