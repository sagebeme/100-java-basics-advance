# Day 45 - Exercise 2: The login callback

After a user logs in, Spotify sends their browser back to your program as `...callback?code=...&state=...`. Write `codeFromCallback(rawQuery, expectedState)` in `src/SpotifyAuth.java`: URL-decode the query, throw an `IllegalStateException` if it contains `error`, if `state` isn't the one you sent (someone may be trying to slip you *their* login), or if there's no `code`. Otherwise return the code.

## 📌 Stuck?

Split on `&`, then each pair on the first `=`, and decode with `URLDecoder.decode(text, StandardCharsets.UTF_8)`. One answer is in `solution/SpotifyAuth.java`.
