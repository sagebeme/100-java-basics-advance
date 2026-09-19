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
- **Day 54 has its own `pom.xml`** (a real Spring Boot/Maven project) rather than a `src/` of loose `.java` files, since that's what the day actually teaches. Days 55–59's exercises follow the same pattern, one `pom.xml` per exercise.
- **Days 60–96 now have full, tested projects** (see "Days 60–96 are now built out" below). **Days 97–100 are still README-only** — Day 97 is in progress; 98–100 remain the last gap in the repo.

Most of the repo compiles with plain `javac`, per the "How to Run Java Files" instructions in each day's README. Days 54–59 are the exception: they need Maven (see the root README's "How to Run Tests" for the exact commands), because that's what those days are teaching.

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
- **Days 54–59** — real Spring Boot projects, JUnit and MockMvc against a running application context: a REST API (54, 55), static resources and Thymeleaf templates (56, 57), and Bootstrap-styled pages including pagination, alerts, badges and a delete-confirmation modal (58, 59).
- **Days 60–96** — every day is its own self-contained Maven module (own `pom.xml`) with a real `mvn test` suite; see "Days 60–96 are now built out" below for what each day actually built and how it was verified.

See the root README's "How to Run Tests" to run any of them.

**Skipped on purpose, not by oversight:**
- **Day 12's exercises, and Day 13's exercise1.** They ask you to demonstrate a language concept (variable scope, access modifiers, spotting errors in given code) rather than compute something with one right answer, so there's nothing a test could assert.
- **Day 18's exercise1** (basic shapes) and **Day 27's exercise1** (basic GUI). Same reasoning: drawing shapes or laying out labels and buttons has no decision logic behind it to test.

## Days 18–23 and 27–31 are JavaFX, which isn't installed here

JavaFX hasn't been part of the JDK since Java 11, and it isn't set up in this repo, so the GUI classes themselves (`TurtleRace.java`, `PongGame.java`, and so on) can't be compiled or run yet. For Days 19–23 and 27–31, the actual game/UI logic (collision detection, ball physics, timers, password/flash-card file formats) has been pulled into a plain `*Logic.java` class next to each original file — no JavaFX needed, and it's fully tested. The original GUI file keeps its own copy of that logic inline; it hasn't been rewired to call the new class, since that edit can't be verified without JavaFX installed to compile it. Day 18 has no real decision logic to extract in the first place — it's just a grid of randomly colored dots.

## Maven and Spring Boot are now set up (Days 54–59)

Days 54–59 needed real infrastructure this repo didn't have, so it's now installed under `F:\Code\clients\tools`: Apache Maven 3.9.16 (checksum-verified against the official Apache download). Each of Days 54–59's projects is its own self-contained Maven project (its own `pom.xml`), targeting Spring Boot 4.1.1. Two non-obvious Spring Boot 4 changes worth knowing if you extend these:
- `@AutoConfigureMockMvc` moved to a new module, `spring-boot-webmvc-test` (package `org.springframework.boot.webmvc.test.autoconfigure`) — `spring-boot-starter-test` alone no longer pulls it in.
- Jackson's `ObjectMapper` now resolves under Maven groupId `tools.jackson.core`, not the classic `com.fasterxml.jackson.core` — check `mvn dependency:tree | grep jackson` before assuming the old import path.
- `@MockBean` was replaced by `@org.springframework.test.context.bean.override.mockito.MockitoBean` (used in Day 59's exercise2, to isolate each test from the shared in-memory post list).

## Days 60–96 are now built out

Days 60–96 previously had README-only briefs with no code. Each is now its own self-contained Maven module (own `pom.xml`, package `com.learning` for Spring Boot days and `com.portfolio` for the standalone "Portfolio Project" days from Day 81 on), built by reading that day's own brief, implementing exactly what it asks, writing real JUnit tests, and verifying with a real `mvn test` run before committing — never claiming a test suite passed without seeing the actual output. Days 60–96 all pass `mvn test`; commit messages document the verified test counts and any real bugs found along the way.

**What each block of days covers:**
- **60–69**: Spring Boot forms, file upload, CSV import/export, JPA/Hibernate CRUD, a Top-10-movies app, accessibility passes, a RESTful API with proper status codes and error handling, Spring Security (form login, BCrypt, CSRF), and a blog capstone with user/post relationships.
- **70–77**: Docker/Postgres deployment config, plain-Java data analysis (CSV parsing, statistics, trend detection), JFreeChart visualization, aggregate/grouping operations, time-series analysis (moving average, linear regression), custom data structures (stack/queue/BST), and classic algorithms (sorting, searching, graph BFS/DFS/shortest-path).
- **78–80**: dynamic programming and greedy algorithms (with a deliberate test showing greedy coin-change is *not* optimal for non-canonical denominations), a testing/QA day (Mockito unit tests + JaCoCo coverage), and a from-scratch linear regression house-price predictor (Gaussian elimination on the normal equations — no ML library needed).
- **81–96 ("Portfolio Projects")**: a Morse code converter, a personal website, Tic Tac Toe and Breakout and Space Invaders (JavaFX GUIs, with all game logic split into a plain-Java class so it's unit-testable independent of the UI), an image watermark tool (BufferedImage/Graphics2D), a typing speed test, a PDF-to-audio converter, a color palette generator (real HSL math), a web scraper (Jsoup), Selenium-based game automation, an API integration dashboard (three real live keyless APIs), a Todo list app, a disappearing-text editor (java.time.Clock-driven, testable without real sleeps), a cafe finder (Leaflet/OpenStreetMap, no API key needed), and a full e-commerce flow with cart/checkout/order management.

**Infrastructure decisions worth knowing if you extend these:**
- **JavaFX games (83, 86, 89, 94)** use `org.openjfx:javafx-controls` (classifier `win`) plus the `org.openjfx:javafx-maven-plugin` (`mvn javafx:run`) — there's no JDK-bundled JavaFX, and this setup downloads a real, matching JavaFX runtime rather than assuming one is installed.
- **Selenium (93)** uses Selenium 4.6+'s built-in Selenium Manager, which auto-detects the installed Chrome version and downloads a matching chromedriver — no manual WebDriver setup needed, and this was verified by actually launching real headless Chrome, not mocked.
- **Payment (96)** uses a `PaymentGateway` interface with a `SimulatedPaymentGateway` implementation instead of real Stripe/PayPal, since those need a merchant account and API credentials this environment doesn't have. It follows the same test-card convention real sandboxes use (Stripe's own `4242...4242` approves, `4000...0002` always declines), so both the approve and decline paths are genuinely tested, not just a stub that always succeeds.
- **PDF-to-audio (90)** uses Apache PDFBox for real text extraction, then shells out to Windows' built-in SAPI speech engine via PowerShell for text-to-speech (there's no actively-maintained, license-clean, cross-platform pure-Java TTS library) — genuinely Windows-only, documented as such rather than implied to be portable.
- **The Cafe Finder (87) and API Integration dashboard (95)** deliberately picked free, keyless, live APIs (OpenStreetMap/Leaflet for maps; Open-Meteo, CoinGecko, and Open Notify's ISS endpoint for the dashboard) specifically so the integration could be tested for real against a live external service, without needing an API key this environment doesn't have.
- Continues the same H2 reserved-word gotcha found in Days 60–69: Day 96's order entity is named `CustomerOrder`, not `Order`, since `ORDER` is reserved SQL.

**Honestly flagged, not faked:**
- **Day 70**'s live Docker build/run was never verified — Docker Desktop's engine was unresponsive on this machine for hours even after a full restart. `mvn test` and `docker compose config` (a client-only, daemon-free command) were both verified for real; the live container path is disclosed as unverified rather than assumed to work.
- **Day 66** deliberately skipped adding Swagger/OpenAPI due to a real compatibility risk with the very-new Spring Boot 4 at the time.
- **Days 83, 86, 89, 94**'s JavaFX GUIs were verified by actually running `mvn javafx:run` and confirming via the OS process list that the app launched, stayed alive and responding, with no exceptions in stderr — a real runtime check, not just a successful compile. A pixel-level screenshot check wasn't possible in the session that built these, since computer-use screen access was declined; that gap is disclosed rather than implied to have been checked.
- **Day 96**'s payment gateway is explicitly simulated, not a real Stripe/PayPal integration — see above.

## Days 32–53's remaining exercises, and 41–42: waiting on a decision

Days 35, 37, and 40's exercises are done (see above) — they're pure logic, same as everything before them. The rest of the 32–53 range, plus all of 41–42, fall into two groups that a "write a reference solution and test it" pass can't honestly cover without a decision first:

- **Days 41–42 aren't Java problems.** Their brief is HTML/CSS (flexbox, grid, basic pages), but the stub is a `.java` file. There's a content mismatch here worth fixing at the source rather than forcing Java to stand in for markup.
- **Everything else needs a real external system this repo doesn't have, and a live version can't be safely faked:**
  - **Day 32** (email sending) would need to actually send email through a real SMTP account.
  - **Days 33, 34, 36, 39, 45, 46** (API calls, OAuth, Spotify, weather/news APIs) need live network calls and real credentials.
  - **Day 38** (databases/SQL) needs a database engine, not installed here.
  - **Days 43, 44** (web scraping) need a real page to scrape.
  - **Days 47–53** (Selenium browser automation: form filling, game playing, job-application bots, social-media posting/liking/following) need a real browser plus WebDriver, not installed here — and several of these describe automating real third-party sites and platforms, which is its own decision to make deliberately, not default into.

The honest options for this group are: (a) install the real infrastructure (a JDBC-compatible database, Selenium plus a browser driver, real API credentials) and test against it for real, or (b) write each one against a fake/in-memory stand-in for the external part (a fake HTTP client, an in-memory table instead of a real database) so the logic is still genuinely tested, clearly labelled as a substitute. Either way, it's a bigger and more infrastructure-heavy piece of work than the rest of this list, and worth its own pass.
