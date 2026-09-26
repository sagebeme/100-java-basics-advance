# Day 83 - Portfolio Project: Tic Tac Toe Game

## 📚 Learning Objectives
- Build interactive game
- Implement game logic
- Create GUI
- Handle user input
- Add AI opponent (optional)

## 🎯 Project Requirements
Create Tic Tac Toe Game:
1. Game board
2. Two-player mode
3. Win detection
4. GUI interface
5. Reset functionality

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`; JavaFX for GUI (Day 18–22).

## ✅ Checklist
- [ ] Implemented game logic
- [ ] Created GUI
- [ ] Added win detection
- [ ] Tested game
- [ ] Completed project
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `TicTacToeApp.java` | The JavaFX window |
| `TicTacToeGame.java` | The rules: moves, turns, wins and draws, with no JavaFX in sight |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day83
mvn javafx:run
```

A window opens. The JavaFX libraries come from Maven, so there's nothing extra to install.

## 🧪 How to Test

```bash
cd day83
mvn test
```

Runs `TicTacToeGameTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 84?** You'll build an Image Watermark Tool!
