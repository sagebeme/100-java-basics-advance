# Day 36 - Exercise 2: Matching headlines

Searching headlines for a company is harder than it looks. `"KCB"` must match *"KCB Group falls"* and *"kcb shares"*, but not *"KCBX launches"*. Write two methods in `src/HeadlineSearch.java`:

**`mentions(String title, String word)`**: `true` when `word` appears as a whole word, ignoring capitals.

**`about(List<String> titles, List<String> words, int limit)`**: the titles that mention **any** of the words, in their original order, at most `limit` of them.

```java
List<String> titles = List.of(
    "KCB Group falls as investors weigh provisions",
    "KCBX launches new fund",
    "Safaricom and kcb lead volumes");
about(titles, List.of("KCB"), 5)
// ["KCB Group falls as investors weigh provisions", "Safaricom and kcb lead volumes"]
```

## Files

- `src/HeadlineSearch.java`: your code goes here.
- `src/HeadlineSearchTest.java`: the tests.
- `solution/HeadlineSearch.java`: one answer.

## 📌 Stuck?

- In a regular expression, `\b` is a word boundary: the point between a letter and a non-letter. In a Java string you write it as `"\\b"`.
- `\b` has a trap: a word that *ends* in punctuation, like `E.A.`, has no boundary after its last dot. The sturdier version is `(?<!\w)` before the word and `(?!\w)` after it: "no letter or digit right before, and none right after".
- `Pattern.quote(word)` stops characters like `.` or `(` in the word from being treated as regex symbols.
- `Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(title).find()`.
