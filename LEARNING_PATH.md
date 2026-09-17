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

## Known gaps (tracked for future work)

- **Days 54, 60–100 have no code yet** — README only.
- **No tests.** The main READ ME lists JUnit/Mockito as tools, but nothing uses them yet.
- **No build file.** Everything runs with `javac`/`java` directly; Maven/Gradle are mentioned but not set up.
