# 100 Days of Java ☕

A 100-day learning path from your first Java variable to Spring Boot, databases and a machine learning model you build yourself. The Beginner stage follows a coffee bean from a farm in Nyeri to a cup in Nairobi, so you learn something real about coffee while you learn Java.

<p align="center">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
    <img src="https://img.shields.io/badge/Progress-0%2F100-blue?style=for-the-badge" alt="Progress">
</p>

## 📚 Course Structure

This course is divided into 5 main sections, progressing from beginner to professional level:

### 👨🏻‍🎓 Beginner (Days 1-14)
Foundation concepts: variables, data types, control flow, loops, methods, collections, scope and debugging. Every project is a step on coffee's journey from shamba to cup: weighing the harvest, drying, grading, paying the farmer, roasting, and finally reading a coffee shop menu.

### 🏋🏻‍♂️ Intermediate (Days 15-31)
Object-Oriented Programming, GUI basics, file handling, and intermediate projects.

### 💪 Intermediate+ (Days 32-58)
Real-world Java with a Kenyan setting: dates and email, HTTP APIs and JSON, secrets and configuration, a SQL database, HTML and CSS, web scraping, OAuth, and browser automation with Selenium against practice sites that run on your own computer. Then Spring Boot.

### 🚀 Advanced (Days 59-80)
Advanced frameworks, databases, REST APIs, authentication, data science, and advanced projects.

### 👨‍💻 Professional (Days 81-100)
Portfolio projects, enterprise patterns, microservices, and professional-level applications.

## 🛠️ Tools and Technologies

What the course actually uses, and from when:

- **Java**: JDK 21 (Days 1–46 also run on JDK 17+; see [Java versions](#-java-versions) below)
- **IDEs**: IntelliJ IDEA, Eclipse or VS Code
- **Build tool**: plain `javac`/`java` for Days 1–46 (with a few jars in `lib/`), then Maven: the Selenium projects on Days 47–53, and every project from Day 54
- **Frameworks**: Spring Boot 4 (web, Thymeleaf, Spring Data JPA with Hibernate, validation, Spring Security) and JavaFX
- **Testing**: JUnit 5 and Mockito, with JaCoCo for coverage (Day 79)
- **Databases**: H2 (in-memory) for most days; PostgreSQL with Docker on Day 70
- **Libraries**: org.json (JSON), H2 (an embedded SQL database), jsoup (scraping), Selenium (browser automation), Apache PDFBox (PDFs), JFreeChart (charts)
- **APIs**: REST, built with Spring and called with Java's HTTP client
- **DevOps**: Docker and Docker Compose (Day 70)
- **Version Control**: Git, GitHub

## 📖 Learning Path

### 👨🏻‍🎓 Beginner (Days 1-14)
- [Day 1](day01): The Harvest Log (variables, `Scanner`)
- [Day 2](day02): Cherry to Cup (`int` vs `double`, casting, `String.format`)
- [Day 3](day03): Cherry Sorter (`if`/`else`, logical operators)
- [Day 4](day04): Rainfall Week (arrays, `ArrayList`)
- [Day 5](day05): Drying Bed Countdown (`for`, `while`, enhanced `for`)
- [Day 6](day06): Grade Labeller (methods, overloading)
- [Day 7](day07): Weighing Station (project: a menu-driven program)
- [Day 8](day08): Farmer Payout (parameters, pass-by-value, varargs)
- [Day 9](day09): Flavour Map (`Map`, `Set`)
- [Day 10](day10): Roast Checker (return values, `switch`)
- [Day 11](day11): Season Simulator Capstone Project (`Random`, putting it all together)
- [Day 12](day12): Farmer Records (scope, `private`, `static`)
- [Day 13](day13): Fix the Weighbridge (stack traces, the debugger, classic bugs)
- [Day 14](day14): Coffee Shop Menu Decoder (project: everything from Days 1–13)

### 🏋🏻‍♂️ Intermediate (Days 15-31)
- [Day 15](day15): Local Development Setup & Coffee Machine Project
- [Day 16](day16): Object-Oriented Programming (OOP) Basics
- [Day 17](day17): Quiz Project & OOP Benefits
- [Day 18](day18): JavaFX Graphics and GUIs
- [Day 19](day19): Inheritance and Polymorphism
- [Day 20](day20): Build Snake Game Part 1: Animation & Coordinates
- [Day 21](day21): Build Snake Game Part 2: Inheritance & Sublists
- [Day 22](day22): Build Pong: The Famous Arcade Game
- [Day 23](day23): Turtle Crossing Capstone Project
- [Day 24](day24): File I/O and Paths
- [Day 25](day25): Working with CSV Data
- [Day 26](day26): Streams and Lambda Expressions
- [Day 27](day27): JavaFX GUI Programs
- [Day 28](day28): JavaFX: Pomodoro Timer Application
- [Day 29](day29): Building Password Manager GUI App with JavaFX
- [Day 30](day30): Exceptions and JSON Data: Improving the Password Manager
- [Day 31](day31): Capstone Project - Flash Card App

### 💪 Intermediate+ (Days 32-58)
- [Day 32](day32): Chama Reminders (dates, CSV records, email behind an interface)
- [Day 33](day33): Laundry Forecast (HttpClient, query strings, JSON, a live weather API)
- [Day 34](day34): Quiz Night (an API in a JavaFX window, background loading, an offline fallback)
- [Day 35](day35): SMS Alerts (environment variables, properties files, masked secrets)
- [Day 36](day36): NSE Price Alert (two feeds, BigDecimal percentages, headline matching)
- [Day 37](day37): Streak Garden (a habit tracker: streaks, rates, a terminal calendar)
- [Day 38](day38): Mazoezi Log (JDBC with H2, prepared statements, GROUP BY)
- [Day 39](day39): Capstone Part 1: Long Weekend Deal Finder (public holidays, a practice fare API)
- [Day 40](day40): Capstone Part 2: Safari Club (members, validated records, personal digests)
- [Day 41](day41): Mama Njeri's Kibanda (your first web page, and a Java page checker)
- [Day 42](day42): Zuhura's Kanga Gallery (flexbox, grid, responsive design, dark mode)
- [Day 43](day43): Headline Digest (scraping with jsoup, politely)
- [Day 44](day44): Mitumba Price Watch (following pages, de-duplicating, robots.txt)
- [Day 45](day45): Music Time Machine (scraping plus Spotify OAuth with PKCE)
- [Day 46](day46): Bei Alert (structured data, price history, fake-sale detection)
- [Day 47](day47): Tamasha Bot (Selenium, page objects, real waits)
- [Day 48](day48): Chapati Clicker Bot (a game-playing strategy, stale elements, simulation)
- [Day 49](day49): Kazi Bot (multi-step forms, a file upload, a ledger against repeats)
- [Day 50](day50): Mtaa Feed Bot (scheduled posts, like rules, a rate limiter)
- [Day 51](day51): Kasi Check (waiting out a live test, median verdicts, an evidence-based ticket)
- [Day 52](day52): Picha Bot (checked actions, spam filtering, exponential backoff)
- [Day 53](day53): Nyumba Data Entry (scrape, clean, type, and check your work)
- [Day 54](day54): Introduction to Spring Boot
- [Day 55](day55): Spring Boot REST APIs
- [Day 56](day56): Spring Boot: Static Files and Templates
- [Day 57](day57): Thymeleaf Templating
- [Day 58](day58): Bootstrap Integration

### 🚀 Advanced (Days 59-80)
- [Day 59](day59): Upgraded Blog with Bootstrap
- [Day 60](day60): HTML Forms with Spring Boot
- [Day 61](day61): Building Advanced Forms
- [Day 62](day62): Spring Boot, Forms, and CSV
- [Day 63](day63): Databases with JPA and Hibernate
- [Day 64](day64): My Top 10 Movies Website
- [Day 65](day65): Web Design Best Practices
- [Day 66](day66): Building RESTful APIs
- [Day 67](day67): Blog Capstone Part 3 - RESTful Routing
- [Day 68](day68): Authentication with Spring Security
- [Day 69](day69): Blog Capstone Part 4 - Adding Users
- [Day 70](day70): Deploying with Docker and Cloud
- [Day 71](day71): Data Analysis with Java
- [Day 72](day72): Data Visualization
- [Day 73](day73): Aggregate Data Operations
- [Day 74](day74): Time Series Analysis
- [Day 75](day75): Charts and Data Visualization
- [Day 76](day76): Collections and Data Structures
- [Day 77](day77): Algorithms and Data Structures
- [Day 78](day78): Advanced Algorithms
- [Day 79](day79): Testing and Quality Assurance (unit, integration and end-to-end tests with Selenium)
- [Day 80](day80): Capstone Project - Predict House Prices

### 👨‍💻 Professional (Days 81-100)
- [Day 81](day81): Portfolio Project - Text to Morse Code Converter
- [Day 82](day82): Portfolio Project - Personal Website
- [Day 83](day83): Portfolio Project - Tic Tac Toe Game
- [Day 84](day84): Portfolio Project - Image Watermark Tool
- [Day 85](day85): Portfolio Project - Typing Speed Test App
- [Day 86](day86): Portfolio Project - Breakout Game Clone
- [Day 87](day87): Portfolio Project - Cafe Finder Website
- [Day 88](day88): Portfolio Project - Todo List Application
- [Day 89](day89): Portfolio Project - Disappearing Text App
- [Day 90](day90): Portfolio Project - PDF to Audio Converter
- [Day 91](day91): Portfolio Project - Color Palette Generator
- [Day 92](day92): Portfolio Project - Web Scraper
- [Day 93](day93): Portfolio Project - Game Automation
- [Day 94](day94): Portfolio Project - Space Invaders
- [Day 95](day95): Portfolio Project - API Integration Website
- [Day 96](day96): Portfolio Project - E-commerce with Payment
- [Day 97](day97): Portfolio Project - WhatsApp Integration
- [Day 98](day98): Portfolio Project - Data Analysis Dashboard
- [Day 99](day99): Portfolio Project - Advanced Analytics
- [Day 100](day100): Portfolio Project - Machine Learning Integration

## 🎯 Learning Objectives

By the end of this course, you will:
- Master Java fundamentals and OOP principles
- Build desktop applications with JavaFX
- Create web applications with Spring Boot
- Work with databases using JPA/Hibernate
- Implement RESTful APIs
- Understand design patterns and best practices
- Deploy applications to cloud platforms
- Build a professional portfolio of projects

## 📌 When you're stuck

Each day is set up so you can find help quickly:

- **In each day's README** → Look for **📌 Notes & reference (use these when stuck)**. It points to which steps to re-read, common gotchas, related days, and quick syntax.
- **Days 1–14** → Each day's README has a **🆘 Stuck?** section with the errors you're most likely to see.
- **In each exercise** (`exercise1/`, `exercise2/`, Days 1–59) → The exercise README ends with **📌 Stuck?** hints, and the exercise keeps one answer in its `solution/` folder.
- **In `_start/` and `_end/`** (Days 15–31) → Use **📌 Refer to (when stuck)** to jump back to the main day README or to the solution for hints.

Use these notes to solve problems without leaving the repo.

## 🌐 The course website

The `site/` folder is a website for the course, built with Next.js: a landing page, the full syllabus, and a page for every day that shows its README with a checklist that saves your progress in your browser. It reads the day folders when it builds, so a README change shows up on the next build.

```bash
cd site
npm install
npm run dev
```

Then open http://localhost:3000. `npm run build` exports the whole site to `site/out/` as plain static files.

To put it online, import the repo into Netlify (`netlify.toml` is already set up) or Vercel (set the root directory to `site`).

## 📝 How to Use This Repository

1. **Clone the repository**
   ```bash
   git clone git@github.com:sagebeme/100-java-basics-advance.git
   cd 100-java-basics-advance
   ```

2. **Set up your development environment**
   - Install JDK 17 or higher (JDK 21 is what this repo is built and tested against)
   - Install IntelliJ IDEA or your preferred IDE
   - Days 1–46 compile with plain `javac`/`java`, with the jars in `lib/` on the classpath (`-cp "lib/*"`). Maven is needed for the Selenium projects on Days 47–53 and for every project from Day 54 (see `LEARNING_PATH.md`'s "Setup you'll need, and when")

3. **Follow the daily structure**
   - Each day has its own folder (day01, day02, etc.)
   - Read the README.md for each day
   - Complete the exercises and projects
   - Commit your progress daily

4. **Exercise Structure**
   - Most days include `exercise1/` and `exercise2/` folders
   - Each exercise folder contains:
     - `README.md` - Exercise requirements and instructions
     - `src/` - Starter code files with TODO comments
     - `solution/` - One answer, to check against once you've tried it yourself. In the Maven exercises (Days 55–59) it holds only the files you change, at the same paths as in the project
   - Navigate to `dayXX/exercise1/` or `dayXX/exercise2/` to start practicing
   - Read the exercise README for specific requirements
   - Implement solutions in the provided starter code files

5. **Required data files (by day)**
   Some days need data files to run; they are included where applicable:
   - **Day 24** (Mail Merge): `template.txt`, `names.txt` — in `day24/_start/src/` and `day24/_end/src/`. Run from that folder.
   - **Day 25** (Kenya counties game): `counties.csv` — in `day25/_start/src/` and `day25/_end/src/`. Run from that folder (e.g. `javac CountiesGame.java && java CountiesGame`).

6. **Track your progress**
   - Update the progress badge in README
   - Document your learning journey
   - Share your projects

## ✅ How to Run Tests

Most days ship with real JUnit tests next to the solution, in `_end/src/` or `exerciseN/src/`, so you can check your own version against the same checks. No Maven or Gradle needed for these: a JUnit runner, and the few libraries Days 32–46 use (org.json, H2 and jsoup), are already checked into `lib/`.

From Day 32 on, put the whole `lib/` folder on the classpath with `"lib/*"` (on Windows, separate classpath entries with `;` instead of `:`):

```bash
javac -cp "lib/*" -d day38/_end/src day38/_end/src/*.java
java -cp "day38/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

The test runner starts with `java -cp ... ConsoleLauncher` here, not `java -jar ...`, because only `java` itself turns `lib/*` into every jar in the folder. Handed to `java -jar` as `execute -cp "...:lib/*"`, the `*` is passed through untouched, the H2, org.json and jsoup jars never load, and correct code fails with errors like `No suitable driver found for jdbc:h2:...`.

**Run every test for one day's solution** (from the repo root):

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day08/_end/src day08/_end/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day08/_end/src --scan-classpath
```

**PowerShell**, same idea:

```powershell
javac -cp lib\junit-platform-console-standalone-6.1.3.jar -d day08\_end\src day08\_end\src\*.java
java -jar lib\junit-platform-console-standalone-6.1.3.jar execute -cp day08\_end\src --scan-classpath
```

Replace `day08\_end` with `dayNN\exercise1\src` or `dayNN\exercise2\src` to run an exercise's tests instead — the command is identical either way. An exercise's `src/` holds your starter code, so its tests fail until you've done the exercise; to check the bundled answer, compile `dayNN/exerciseN/solution/*.java` together with the test file (and any finished helper classes) from `src/`. A clean run ends with a line like `[  X tests successful ]` and `[  0 tests failed ]`. If you've written your own version of a day's code, drop your class next to the test and re-run the same two commands — the test doesn't care whether the code came from the day's own solution or from you, only that the method names and behavior match.

**Days 47–53 and Day 54 onward are different: their projects are Maven projects, each with its own `pom.xml`.** On Days 47–53 that's the `_end/` and `_start/` project, which uses Selenium against a practice site that starts on your computer (their exercises are still plain `javac`). From Day 54 it's every day and exercise: Spring Boot through Day 59, then a mix of Spring Boot and plain Maven projects through Day 100. Install [Apache Maven](https://maven.apache.org/download.cgi) (any recent 3.9.x), then from that day or exercise's own folder:

```bash
mvn test
```

To actually run one of these as a live app (Day 54, for instance) and try it in a browser:

```bash
mvn spring-boot:run
```

Then open `http://localhost:8080`. Stop it with Ctrl+C.

**Which days have tests today:** the main project's solution (`_end/`) on every day from 1 to 53 except 18; every exercise from Day 1 to Day 59 (Days 15, 31 and 54 have none), with a starter that fails its tests until it's done and a `solution/` that passes them; and every day from 54 to 100, each with its own `mvn test` suite. On Days 47–53 and in Day 79's end-to-end tests (`day79/e2e`), the browser tests need Google Chrome, and skip themselves (rather than fail) if it isn't installed. See `LEARNING_PATH.md` for the few things that are intentionally untested and why.

**Check every exercise at once.** `tools/check-exercises.sh` copies each exercise somewhere temporary and proves two things: its `solution/` passes the tests, and its untouched starter compiles but fails them (so the tests really check something). It needs a JDK, Maven, and `curl` for the JavaFX jars of Days 18 and 27, which it downloads once into `.cache/`. Pass exercise folders to check just those:

```bash
tools/check-exercises.sh                       # all of them
tools/check-exercises.sh day29/exercise2       # just one
```

GitHub Actions runs the same script, and builds the course website, on every push and pull request (`.github/workflows/ci.yml`).

## 🚀 Getting Started

### Prerequisites
- **Java JDK 21** (JDK 17+ is enough for Days 1–46; see [Java versions](#-java-versions) below)
- IDE (IntelliJ IDEA recommended)
- Git
- Maven 3.9+ (from Day 47)
- Google Chrome (for the browser automation on Days 47–53)

### ☕ Java versions
**Use JDK 21.** Every Maven project (Days 47–53, and Day 54 on) targets Java 21, and from Day 54 they use Spring Boot 4, so they won't build on an older JDK. Days 1–46 are plain Java and also run on JDK 17 or newer: the JUnit runner in `lib/` needs Java 17, so on JDK 11 the tests won't start (`UnsupportedClassVersionError`). Here's what matters when choosing a version:

| Version | Notes |
|--------|--------|
| **JDK 8** | Still common in older projects. Some examples may work, but later days use features from Java 11+ (e.g. `var`, HTTP Client). |
| **JDK 11 (LTS)** | First LTS with modules. Too old for this course: the JUnit runner in `lib/` needs Java 17. JavaFX is not included; use the [JavaFX SDK](https://gluonhq.com/products/javafx/) and `--module-path` / `--add-modules` when running GUI days (18–23, 27–31 and 34). |
| **JDK 17 (LTS)** | Enough for Days 1–46 only: Days 47–100 target Java 21. JavaFX is not included here either: use the JavaFX SDK on the GUI days. |
| **JDK 21 (LTS)** | **What this course is built and tested on.** Needed from Day 47. |

- **LTS** = Long Term Support: Oracle (and others) provide updates for several years.
- **Check your version:** `java -version` and `javac -version`.
- **Downloads:** [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [Eclipse Temurin (OpenJDK)](https://adoptium.net/).

### First Steps
1. Set up your Java development environment
2. Complete Day 1 exercises
3. Commit your code daily
4. Build projects progressively

## 📚 Resources

- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [JavaFX Documentation](https://openjfx.io/)
- [Effective Java by Joshua Bloch](https://www.oracle.com/java/technologies/effective-java.html)

## 🤝 Contributing

This is a personal learning repository. Feel free to fork and adapt for your own learning journey!

## 📄 License

This repository is for educational purposes.

---

**Happy Coding! ☕**
