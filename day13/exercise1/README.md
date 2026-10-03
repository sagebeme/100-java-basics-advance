# Day 13 - Exercise 1: The missing phone number

The cooperative sends each farmer an SMS when their payout is ready. `src/PhoneBook.java` looks up a farmer's number, and it crashes for anyone who isn't in the book:

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.trim()" because the return value of "java.util.Map.get(Object)" is null
	at PhoneBook.phoneFor(PhoneBook.java:6)
	at PhoneBook.main(PhoneBook.java:14)
```

1. Run `main` and read the stack trace. Which line, and which value, was `null`?
2. Fix `phoneFor` so a farmer who isn't in the book gets `"no number on file"` instead of a crash.
3. While you're there: the clerk sometimes types names in lower case. `phoneFor(book, "wanjiru")` should still find `Wanjiru`.

## Files

- `src/PhoneBook.java`: the buggy code.
- `src/PhoneBookTest.java`: the tests.
- `solution/PhoneBook.java`: the fixed version.

## 📌 Stuck?

- `map.get(key)` returns `null` when the key isn't there, and calling any method on `null` throws a `NullPointerException`.
- Check `containsKey` first, or use `getOrDefault`.
- For the capitals, loop over `book.keySet()` and compare with `equalsIgnoreCase`.
