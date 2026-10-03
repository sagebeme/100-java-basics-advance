# Day 40 - Exercise 2: Unsubscribe links

Every newsletter needs a one-click "unsubscribe" link. If the link were just `.../leave?email=wanjiku@example.com`, anyone could unsubscribe anyone. So the link carries a **token** that only the club can make:

```
token = first 16 hex characters of SHA-256( secret + ":" + lower-cased email )
https://safari.example/leave?email=wanjiku%40example.com&token=3f9a...
```

The `secret` lives in an environment variable (Day 35), so without it nobody can work out a valid token.

Write three methods in `src/Unsubscribe.java`:

| Method | What it does |
|---|---|
| `token(String email, String secret)` | the 16-character token above; the same for `Wanjiku@Example.com` and `wanjiku@example.com` |
| `link(String base, String email, String secret)` | `base + "?email=" + encoded email + "&token=" + token` |
| `isValid(String email, String token, String secret)` | `true` only if the token matches |

## Files

- `src/Unsubscribe.java`: your code goes here.
- `src/UnsubscribeTest.java`: the tests.
- `solution/Unsubscribe.java`: one answer.

## 📌 Stuck?

- `MessageDigest.getInstance("SHA-256").digest(text.getBytes(StandardCharsets.UTF_8))` gives 32 bytes.
- `HexFormat.of().formatHex(bytes)` turns them into 64 hex characters; keep the first 16 with `substring(0, 16)`.
- `URLEncoder.encode(email, StandardCharsets.UTF_8)` turns `@` into `%40`.
- For `isValid`, compare with `MessageDigest.isEqual(a.getBytes(), b.getBytes())`. It takes the same time whether the first character is wrong or the last, so an attacker can't guess a token a character at a time by timing it.
