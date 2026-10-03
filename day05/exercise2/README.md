# Day 5 - Exercise 2: Ask until it makes sense

The moisture meter's reading is typed in by hand, and people make mistakes: `fifty`, `150`, `-3`. Write `readMoisture(Scanner in)`. It keeps reading until it gets a number from 0 to 100, then returns it. For every bad answer it prints `Please type a number from 0 to 100.`

```
fifty
Please type a number from 0 to 100.
150
Please type a number from 0 to 100.
52
```

returns `52.0`.

## Files

- `src/MoistureInput.java`: your code goes here.
- `src/MoistureInputTest.java`: the tests. They hand your method a `Scanner` that reads from a `String` instead of the keyboard, so nobody has to type.
- `solution/MoistureInput.java`: one answer.

## 📌 Stuck?

- `in.hasNextDouble()` is `true` when the next thing typed is a number. Then `in.nextDouble()` reads it.
- If it isn't a number, `in.next()` reads the bad word and throws it away. Without that line you'd check the same bad word forever.
- `while (true) { ... return value; }` is a common pattern: loop forever, and leave with `return` once you have a good answer.
