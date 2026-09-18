# Java Learning Path Structure

## Overview
This repository follows a structured 100-day learning path, organized into 5 difficulty levels.

## Directory Structure

This is how the days are actually laid out. Not every folder appears in every day — see the notes below.

```
100-java-basics-advance/
├── README.md                 # Main course overview
├── LEARNING_PATH.md          # This file
├── day01/
│   ├── README.md              # Learning objectives, topics, and the day's project
│   ├── exercise1/
│   │   ├── README.md          # What to build, and where to look when stuck
│   │   └── src/                # Starter code with TODOs
│   ├── exercise2/
│   │   ├── README.md
│   │   └── src/
│   ├── _start/
│   │   ├── README.md
│   │   └── src/                # The day's main project, as a starting point
│   └── _end/
│       ├── README.md
│       └── src/                # The finished version of the day's main project
...
└── day100/
    └── README.md               # Portfolio project brief
```

## What's in each folder

- **`exercise1/`, `exercise2/`** (most days): small, focused practice on the day's concept. Starter code has `// TODO` comments; the README says what's expected.
- **`_start/`, `_end/`** (Days 1–31): the day's main project. `_start` is where you begin, `_end` is the finished version, to check your own work against.
- **From Day 32 on**, most days are project-only: read the day's `README.md` and build directly, there's no separate `_start`/`_end` split.
- **Days 54, 60–100** are README-only right now: the brief and the concepts, without starter or solution code. This is the biggest gap in the repo — see the "Improvements" section below if you want to help close it.

There's no `pom.xml` or `build.gradle` yet. Every file compiles with plain `javac`, per the "How to Run Java Files" instructions in each day's README.

## Progress Tracking
- [ ] Day 1-14: Beginner
- [ ] Day 15-31: Intermediate
- [ ] Day 32-58: Intermediate+
- [ ] Day 59-80: Advanced
- [ ] Day 81-100: Professional

## Learning Approach
1. Read the day's README.md
2. Understand the concepts
3. Complete exercises
4. Build the project
5. Commit your code
6. Move to next day

## Test coverage

Tests exist for:
- **Days 1–17 and 24–26's `_end/` solutions** (30 of 31 days in that range — Day 13's is a fixed literal demo with nothing to vary, so it has no test).
- **The testable logic behind Days 19–23 and 27–31** (see the JavaFX note below).
- **The `exercise1`/`exercise2` stubs for every day from 1 to 30, and Days 35, 37, and 40** — these were empty `// TODO` files, so each got a real reference implementation plus a test.

See the root README's "How to Run Tests" to run any of them.

**Skipped on purpose, not by oversight:**
- **Day 12's exercises, and Day 13's exercise1.** They ask you to demonstrate a language concept (variable scope, access modifiers, spotting errors in given code) rather than compute something with one right answer, so there's nothing a test could assert.
- **Day 18's exercise1** (basic shapes) and **Day 27's exercise1** (basic GUI). Same reasoning: drawing shapes or laying out labels and buttons has no decision logic behind it to test.

## Days 18–23 and 27–31 are JavaFX, which isn't installed here

JavaFX hasn't been part of the JDK since Java 11, and it isn't set up in this repo, so the GUI classes themselves (`TurtleRace.java`, `PongGame.java`, and so on) can't be compiled or run yet. For Days 19–23 and 27–31, the actual game/UI logic (collision detection, ball physics, timers, password/flash-card file formats) has been pulled into a plain `*Logic.java` class next to each original file — no JavaFX needed, and it's fully tested. The original GUI file keeps its own copy of that logic inline; it hasn't been rewired to call the new class, since that edit can't be verified without JavaFX installed to compile it. Day 18 has no real decision logic to extract in the first place — it's just a grid of randomly colored dots.

## Days 32–53's exercises, and 41–42, 55–59 in full: waiting on a decision

Days 35, 37, and 40's exercises are done (see above) — they're pure logic, same as everything before them. The remaining exercises in the 32–53 range, plus all of 41–42 and 55–59, fall into two groups that a "write a reference solution and test it" pass can't honestly cover without a decision first:

- **Days 41–42 aren't Java problems.** Their brief is HTML/CSS (flexbox, grid, basic pages), but the stub is a `.java` file. There's a content mismatch here worth fixing at the source rather than forcing Java to stand in for markup.
- **Everything else needs a real external system this repo doesn't have, and a live version can't be safely faked:**
  - **Day 32** (email sending) would need to actually send email through a real SMTP account.
  - **Days 33, 34, 36, 39, 45, 46** (API calls, OAuth, Spotify, weather/news APIs) need live network calls and real credentials.
  - **Day 38** (databases/SQL) needs a database engine, not installed here.
  - **Days 43, 44** (web scraping) need a real page to scrape.
  - **Days 47–53** (Selenium browser automation: form filling, game playing, job-application bots, social-media posting/liking/following) need a real browser plus WebDriver, not installed here — and several of these describe automating real third-party sites and platforms, which is its own decision to make deliberately, not default into.
  - **Days 55–59** need Spring Boot (a REST API, static files, Thymeleaf templates, Bootstrap), which needs Maven or Gradle to pull in — the same dependency Day 54 needs from scratch.

The honest options for the external-system group are: (a) install the real infrastructure (a JDBC-compatible database, Selenium plus a browser driver, real API credentials) and test against it for real, or (b) write each one against a fake/in-memory stand-in for the external part (a fake HTTP client, an in-memory table instead of a real database) so the logic is still genuinely tested, clearly labelled as a substitute. Either way, it's a bigger and more infrastructure-heavy piece of work than the rest of this list, and worth its own pass.
