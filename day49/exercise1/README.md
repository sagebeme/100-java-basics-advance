# Day 49 - Exercise 1: A safe template

A cover letter that says "Dear {{company}}" is worse than no letter. Write `fill(String template, Map<String, String> values)` in `src/Template.java`: replace every `{{name}}` placeholder with its value, and if any placeholder has no value, throw an `IllegalArgumentException` that names **all** the missing ones.

```java
fill("Dear {{company}}, I'd like the {{title}} role.", Map.of("company", "Mfuko", "title", "Java Developer"))
// "Dear Mfuko, I'd like the Java Developer role."
fill("Hi {{name}} from {{town}}", Map.of())   // IllegalArgumentException: Missing values for: name, town
```

## 📌 Stuck?

`Pattern.compile("\\{\\{(\\w+)}}")` finds placeholders; `matcher.appendReplacement` and `appendTail` rebuild the text. Collect the missing names in a `LinkedHashSet`, so each is named once, in order. One answer is in `solution/Template.java`.
