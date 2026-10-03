# Day 33 - Exercise 2: Query strings

Gluing a URL together with `+` breaks as soon as a value has a space, an `&` or a `/` in it. Write `withQuery(String base, Map<String, String> params)` in `src/QueryString.java`. It adds `?` and the parameters in the map's order, joined by `&`, with every name and value **URL-encoded**:

```java
Map<String, String> params = new LinkedHashMap<>();
params.put("q", "nyama choma & chips");
params.put("timezone", "Africa/Nairobi");
withQuery("https://example.com/search", params)
// "https://example.com/search?q=nyama+choma+%26+chips&timezone=Africa%2FNairobi"
```

With an empty map, return `base` unchanged, with no `?`.

## Files

- `src/QueryString.java`: your code goes here.
- `src/QueryStringTest.java`: the tests.
- `solution/QueryString.java`: one answer.

## 📌 Stuck?

`URLEncoder.encode(text, StandardCharsets.UTF_8)` encodes one name or value. It turns a space into `+`, which is correct in a query string.
