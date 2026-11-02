# Day 45 - Music Time Machine: Scraping Meets an OAuth API

> What was everyone dancing to the week you finished school? Scrape the chart for that week and turn it into a playlist, with a proper, safe login.

## 📖 The story

A radio station's website keeps its old Top 10 charts. Your program reads the chart for any week, looks each song up in a music service, and builds a playlist:

```
$ java TimeMachine 2016-12-03
Using the practice catalogue (set SPOTIFY_CLIENT_ID to use Spotify)
Created playlist "Time Machine: 2016-12-03" with 8 of 10 songs.
  not found: #4 Sunsets in Lamu by Neema
  not found: #8 Mvua by Wanjiru Lee
```

The chart and its artists are made up and saved in the project. With no settings, the program uses a **practice catalogue** in memory. Set `SPOTIFY_CLIENT_ID` (from a free app at developer.spotify.com, with the redirect URI `http://127.0.0.1:8888/callback`) and the same code logs in to your real Spotify account and makes a private playlist.

## 🎯 Learning Objectives
- Combine scraping (Days 43–44) with an API (Days 33–39)
- Understand **OAuth**: letting a program act for you without ever seeing your password
- Use **PKCE** and a **state** value to make the login safe
- Hash with SHA-256 and encode with base64url
- Match the same song across two services by normalising titles
- Keep a real service and a practice one behind the same interface

## 🧰 What you'll use

**OAuth with PKCE, in three steps:**

1. Your program makes a random secret, the **verifier**, and sends only its SHA-256 hash, the **challenge**, in a link the user opens. They log in *on Spotify's site*.
2. Spotify sends the browser back to `http://127.0.0.1:8888/callback?code=...&state=...`. A tiny `HttpServer` in your program catches it and checks the `state` is the one it sent.
3. Your program swaps the `code` plus the original verifier for an **access token**. Only the program that made the verifier can do this, so a stolen code is useless.

```java
String verifier = Pkce.newVerifier();
URI login = SpotifyAuth.authorizeUrl(clientId, redirect, "playlist-modify-private", state, Pkce.challenge(verifier));
```

## 🪜 Steps

1. **Exercise 1: PKCE** (`exercise1/`). Verifier and challenge, checked against the standard's own example.
2. **Exercise 2: The login callback** (`exercise2/`). Decode it, check the state, return the code.
3. **Project: Music Time Machine** (`_start/src/`).
   1. `ChartScraper.read`: the chart in order, skipping the sponsored entry.
   2. `ChartScraper.normalise`: lower case, no "(feat. ...)", no punctuation.
   3. `TimeMachine.build`: find each song, create the playlist, add what was found, report what wasn't.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day45/_end/src day45/_end/src/*.java
cd day45/_end/src && java -cp ".:../../../lib/*" TimeMachine 2016-12-03 && cd ../../..
java -cp "day45/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 🆘 Stuck?

- **The PKCE test fails**: use `Base64.getUrlEncoder().withoutPadding()`. The ordinary encoder uses `+`, `/` and `=`.
- **"The state doesn't match"**: you started a second login; use the newest link.
- **Spotify says `INVALID_CLIENT: Invalid redirect URI`**: add exactly `http://127.0.0.1:8888/callback` to your app's settings.
- **A song isn't found**: compare the normalised titles of both sides. Real catalogues spell things differently.

## ✅ Checklist
- [ ] Scraped the chart and skipped the advert
- [ ] Built and tested PKCE and the callback check
- [ ] Built a playlist through the `MusicService` interface
- [ ] Can explain why OAuth never shows your program the password
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 46 watches prices over time and sends an alert when something you want gets cheaper.
