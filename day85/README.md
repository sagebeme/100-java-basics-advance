# Day 85 - Portfolio Project: Typing Speed Test App

## 📚 Learning Objectives
- Build typing test application
- Measure typing speed
- Calculate accuracy
- Create timer functionality
- Build interactive app

## 🎯 Project Requirements
Create Typing Speed Test:
1. Display text to type
2. Measure typing speed (WPM)
3. Calculate accuracy
4. Timer functionality
5. Results display

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** `mvn compile exec:java` runs the console version; a JavaFX window (Days 18–22) is an optional extra.

## ✅ Checklist
- [ ] Implemented typing test
- [ ] Added speed calculation
- [ ] Added accuracy tracking
- [ ] Created timer
- [ ] Completed app
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Stopwatch.java` | Times the attempt |
| `TypingResult.java` | The score |
| `TypingTest.java` | Scores an attempt: words per minute and accuracy |
| `TypingTestApp.java` | The console app |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day85
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day85
mvn test
```

Runs `StopwatchTest` and `TypingTestTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 86?** You'll build a Breakout Game Clone!
