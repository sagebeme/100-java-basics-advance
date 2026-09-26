# Day 89 - Portfolio Project: Disappearing Text App

## 📚 Learning Objectives
- Build writing app
- Implement timer functionality
- Handle text input
- Create unique UX
- Build creative application

## 🎯 Project Requirements
Create Disappearing Text App:
1. Text input area
2. Timer countdown
3. Text disappears if idle
4. Save functionality
5. Creative UI

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`; JavaFX for GUI; Timer (Day 28).

## ✅ Checklist
- [ ] Implemented timer
- [ ] Added text handling
- [ ] Created UI
- [ ] Added features
- [ ] Completed app
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `DisappearingTextApp.java` | The JavaFX window |
| `DisappearingTextEditor.java` | The core rule: stop typing for too long and unsaved text disappears. Takes a clock, so it's testable |
| `SavedEntry.java` | A saved piece of writing |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day89
mvn javafx:run
```

A window opens. The JavaFX libraries come from Maven, so there's nothing extra to install.

## 🧪 How to Test

```bash
cd day89
mvn test
```

Runs `DisappearingTextEditorTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 90?** You'll build a PDF to Audio Converter!
