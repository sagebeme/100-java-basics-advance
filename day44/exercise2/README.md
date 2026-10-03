# Day 44 - Exercise 2: Reading robots.txt

Most websites publish a file at `/robots.txt` that tells robots, including your scrapers, which parts of the site to stay out of:

```
# Gikomba Online
User-agent: Googlebot
Disallow: /search

User-agent: *
Disallow: /admin
Disallow: /checkout
```

The rules under `User-agent: *` apply to every robot, including yours. Write `src/RobotsRules.java`:

- the constructor takes the file's text and keeps the `Disallow` paths from the `User-agent: *` group
- `isAllowed(String path)` is `false` when the path starts with any of them

Ignore comments (everything after `#`), blank lines, capitals in field names (`disallow:` counts), and empty `Disallow:` lines, which mean "nothing is disallowed".

## Files

- `src/RobotsRules.java`: your code goes here.
- `src/RobotsRulesTest.java`: the tests.
- `solution/RobotsRules.java`: one answer.

## 📌 Stuck?

Go through the lines keeping one `boolean`: "am I inside the `*` group?". A `User-agent:` line switches it on or off; a `Disallow:` line only counts while it's on. `line.split(":", 2)` splits only at the first colon.
