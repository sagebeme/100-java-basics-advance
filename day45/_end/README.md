# Day 45 - Solution

The finished Music Time Machine, checked by `TimeMachineTest.java` (including PKCE against the example in RFC 7636).

Things worth noticing:

- **`MusicService` is an interface**, so `build` works the same against the practice catalogue and Spotify.
- **The password never touches your program.** The user logs in on Spotify's own page; you only ever see a one-time code.
- **The `state` check and the PKCE verifier** stop someone else's login being slipped into yours.
- **`normalise`** makes "Nairobi Nights (feat. DJ Kito)" match "Nairobi Nights", which is most of the work of matching songs between services.
