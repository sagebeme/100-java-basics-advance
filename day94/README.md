# Day 94 - Portfolio Project: Space Invaders

## 📚 Learning Objectives
- Build classic arcade game
- Implement game mechanics
- Create enemies and shooting
- Add levels and scoring
- Build complete game

## 🎯 Project Requirements
Create Space Invaders:
1. Player ship
2. Enemy invaders
3. Shooting mechanics
4. Collision detection
5. Score system

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** JavaFX for game (Day 20–22); run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Implemented game
- [ ] Added mechanics
- [ ] Created enemies
- [ ] Added scoring
- [ ] Completed game
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Bullet.java` | A bullet |
| `Enemy.java` | One invader |
| `Player.java` | The player's ship |
| `SpaceInvadersApp.java` | The JavaFX window and game loop |
| `SpaceInvadersGame.java` | The game rules: player, enemies, bullets and collisions |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day94
mvn javafx:run
```

A window opens. The JavaFX libraries come from Maven, so there's nothing extra to install.

## 🧪 How to Test

```bash
cd day94
mvn test
```

Runs `SpaceInvadersGameTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 95?** You'll build an API Integration Website!
