# How to Learn With This Course

This is a guide to actually working through the 100 days — the daily rhythm, what's in a day's folder, what tools you'll need and when, how to check your own work, and what to do when a day asks for something you don't have. For the day-by-day topic list, see the main [README](README.md); this file is about *how* to move through it.

## The daily rhythm

Do this for every day, in order:

1. **Read the whole day's `README.md` first**, including the "Notes & reference" section at the bottom, before writing any code. It's short by design — skimming it and jumping straight to code is the single most common way to get stuck on something the README already answered.
2. **Attempt it yourself before checking a solution.** Where a day has `exercise1/`/`exercise2/`, start there — they're small and focused, and a wrong attempt on a 20-line exercise teaches you more than reading 20 correct lines someone else wrote.
3. **Build the day's main project.** For Days 1–31 this starts from `_start/`; from Day 32 on, most days are project-only — you build directly from the brief, there's no scaffold to fill in.
4. **Check your work against the tests where they exist** (see "Verifying your work" below) rather than eyeballing it against `_end/` or a sample output. A test tells you *exactly* what's wrong; comparing files by eye tells you they're different.
5. **Commit at the end of the day**, even if it's incomplete. A daily commit is what makes the "100 days" framing real — you're building a visible trail of 100 days of actual work, not one commit at the end.
6. **Move on.** Don't wait for perfection on a portfolio project before starting the next day; the point of Days 81–100 in particular is breadth across many small, complete systems, not polishing any single one indefinitely.

## The five stages, and what changes at each one

| Stage | Days | What's different |
|---|---|---|
| Beginner | 1–14 | Plain `.java` files, `javac`/`java` only. No extra tools. |
| Intermediate | 15–31 | Same tooling, plus JavaFX for GUI days (18, 22–23, 27–31) — see the JavaFX note below before you hit Day 18. |
| Intermediate+ | 32–58 | APIs, credentials, scraping, and automation days start appearing — see "When a day needs something you don't have," below. Spring Boot and Maven arrive at Day 54. |
| Advanced | 59–80 | Everything is a self-contained Maven project from here on (own `pom.xml` per day) — plain `javac` no longer applies past Day 59. |
| Professional | 81–100 | "Portfolio Projects": each day is a complete, real, working system on its own (a game, a web app, a small ML pipeline), not a guided exercise. Treat these as practice building something from a one-paragraph brief, the way a real assignment or side project actually starts. |

## What's in a day's folder

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

- **`exercise1/`, `exercise2/`** (most days 1–40): small, focused practice on the day's concept. Starter code has `// TODO` comments; the README says what's expected.
- **`_start/`, `_end/`** (Days 1–31 only): the day's main project. `_start` is where you begin; use `_end` only to check your own work, not as a template to copy from.
- **From Day 32 on**, most days are project-only: read the `README.md` and build directly into the day's folder.
- **From Day 54 on**, every day/exercise is its own Maven project (its own `pom.xml`) instead of loose `.java` files, because that's what those days are teaching (Spring Boot, JPA, etc.).

## Verifying your work

Most early and late days ship with real JUnit tests you can run against your own solution, not just the bundled one — see the root README's **"How to Run Tests"** for the exact commands (plain JUnit for Days 1–17/24–26/exercises through Day 40; `mvn test` for every day from 54 onward). If a day has a test, get it green before moving on — that's a stronger signal than "it compiles" or "it looks right."

**Skipped on purpose, not by oversight:** a handful of exercises ask you to demonstrate a concept (explain variable scope, spot the bug in given code, draw some shapes) rather than compute a value with one right answer, so there's nothing a test could assert. Day 12's exercises, Day 13's exercise1, and Day 18/27's exercise1 fall in this group — read the README's own checklist for those instead.

## Setup you'll need, and when

- **Days 1–53**: nothing beyond a JDK (11+, this repo is built/tested against 21) and `javac`/`java`.
- **JavaFX (Days 18, 22–23, 27–31)**: JavaFX hasn't shipped with the JDK since Java 11, so you'll need to install it yourself — grab the [JavaFX SDK](https://gluonhq.com/products/javafx/) and run with `--module-path`/`--add-modules` pointed at it. If you'd rather learn the logic before wrestling with a GUI toolchain, Days 19–23 and 27–31 each have the real decision logic (collision detection, ball physics, timers, file formats) pulled out into a plain `*Logic.java` class next to the GUI file — no JavaFX needed to compile or test that part, and it's fully covered by JUnit tests you can run today.
- **Maven (Day 54 onward)**: install [Apache Maven](https://maven.apache.org/download.cgi) (any recent 3.9.x). Every project from here on has its own `pom.xml` — run `mvn test` from that day's folder.
- **Docker (Day 70)**: optional for that day specifically — you can read and understand the Dockerfile/compose setup, and verify the app itself with `mvn test`, without a working Docker daemon.

## When a day needs something you don't have

A handful of days (sending real email, calling a paid or OAuth-gated API, driving a real browser, taking a real payment) describe integrating with something you may not have credentials or infrastructure for yet. Don't let that block you — it's a real, common situation in professional work too, and the standard move is:

1. **Design the integration behind an interface** (e.g. a `PaymentGateway` or `WhatsAppClient` interface) rather than calling the third-party SDK directly from your business logic.
2. **Write a fake/simulated implementation of that interface** that behaves like the real thing closely enough to test against — this course's own Day 96 does exactly this for payments (a fake gateway that follows the same test-card convention real processors use, so both "approved" and "declined" are genuinely reachable, tested code paths) and Day 90 does it for text-to-speech (falling back to the OS's own built-in speech engine instead of a paid cloud TTS API).
3. **Swap in the real SDK later**, once you have credentials, by writing a second implementation of the same interface — your business logic doesn't change.

This is a genuinely useful pattern to practice, not just a workaround: it's exactly how you'd want to structure real code that depends on a service you can't hit from your test suite (rate limits, cost, flakiness, secrets management). A few specific days where you'll want it:
- **Day 32** (email) — a real send needs a real SMTP account; swap in one once you have credentials.
- **Days 33, 34, 36, 39, 45, 46** (weather/news/Spotify/OAuth APIs) — need live network calls and real API keys.
- **Day 38** (databases) — needs a real database engine (or use an embedded one like H2 to get moving without installing a server).
- **Days 43, 44** (web scraping) — need a real page to scrape; test your parsing logic against a saved local copy of the HTML so your tests don't depend on the live page staying unchanged.
- **Days 47–53** (Selenium automation) — need a real browser plus a WebDriver. Modern Selenium (4.6+) auto-detects your installed Chrome and downloads a matching driver for you — no manual setup required, just add the dependency.
- **Day 96** (payments), **Day 97** (WhatsApp) — see above; both include a working fake you can extend.

## Known gaps worth knowing about upfront

- **Days 41–42's briefs are HTML/CSS** (flexbox, grid, basic pages), but their stub files are `.java` — a content mismatch in the course materials, not something you're doing wrong. Treat these two days as an HTML/CSS detour and build plain `.html`/`.css` files instead of forcing the topic into Java.
- **Days 60–100 are each a fully separate, real project** (own `pom.xml`, own tests) rather than exercises with a fixed right answer — if your approach to a portfolio-project day looks different from any reference you find, that's expected; judge it against the day's own checklist, not a diff.

## Gotchas you'll actually hit

If you're on a recent Spring Boot (4.x) for Days 54 onward, a few things moved from where older tutorials expect them:
- `@AutoConfigureMockMvc` lives in a separate module, `spring-boot-webmvc-test` (package `org.springframework.boot.webmvc.test.autoconfigure`) — `spring-boot-starter-test` alone doesn't pull it in anymore.
- Jackson's `ObjectMapper` resolves under Maven groupId `tools.jackson.core`, not the classic `com.fasterxml.jackson.core` — run `mvn dependency:tree | grep jackson` if an import doesn't resolve the way an older tutorial expects.
- `@MockBean` is gone; use `@org.springframework.test.context.bean.override.mockito.MockitoBean` instead.

A couple of database gotchas worth knowing before you hit a confusing SQL error with H2 (the embedded database used throughout Days 60+):
- `USER`, `YEAR`, `RANK`, and `ORDER` are all reserved SQL keywords — naming an entity or column any of these produces a syntax error. Rename the column (`@Column(name = "app_user")`) or the table (`@Table(name = "customer_order")`) instead.

And one modeling gotcha worth knowing if you're encoding categorical data (education level, region, etc.) as input to your own regression model, as Day 100 does: one-hot encode with one category *dropped* as the reference (rather than a column per category). Including every category creates perfect collinearity with the model's bias term — known as the "dummy variable trap" — and breaks the normal-equations math.

## Progress Tracking

- [ ] Day 1–14: Beginner
- [ ] Day 15–31: Intermediate
- [ ] Day 32–58: Intermediate+
- [ ] Day 59–80: Advanced
- [ ] Day 81–100: Professional
