# Day 86 - Portfolio Project: Breakout Game Clone

## 📚 Learning Objectives
- Build classic arcade game
- Implement game physics
- Handle collisions
- Create game levels
- Build complete game

## 🎯 Project Requirements
Create Breakout Game:
1. Paddle control
2. Ball physics
3. Brick breaking
4. Score system
5. Multiple levels

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** JavaFX for game (Day 20–22); run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Implemented game mechanics
- [ ] Added physics
- [ ] Created levels
- [ ] Added scoring
- [ ] Completed game
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Ball.java` | Position and velocity |
| `BreakoutApp.java` | The JavaFX window and game loop |
| `BreakoutGame.java` | The game rules: ball, paddle, bricks and collisions |
| `Brick.java` | A brick, and its collision test |
| `Paddle.java` | The paddle |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day86
mvn javafx:run
```

A window opens. The JavaFX libraries come from Maven, so there's nothing extra to install.

## 🧪 How to Test

```bash
cd day86
mvn test
```

Runs `BreakoutGameTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 87?** You'll build a Cafe Finder Website!
