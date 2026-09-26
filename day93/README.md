# Day 93 - Portfolio Project: Game Automation

## 📚 Learning Objectives
- Automate game playing
- Use Selenium/automation
- Implement game strategies
- Build automation tools
- Create game bots

## 🎯 Project Requirements
Create Game Automation:
1. Automate game actions
2. Implement strategy
3. Handle game state
4. Log activities
5. Performance tracking

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** Selenium WebDriver (already in `pom.xml`); needs Chrome installed; `mvn compile exec:java`.

## ✅ Checklist
- [ ] Can automate games
- [ ] Implements strategy
- [ ] Handles state
- [ ] Logs activities
- [ ] Completed automation
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `ActivityLog.java` | What the bot did |
| `CounterLastMoveStrategy.java` | Plays whatever beats the opponent's last move |
| `CyclingStrategy.java` | Rock, paper, scissors, in turn |
| `GameAutomationApp.java` | Runs the bot and prints its performance |
| `Move.java` | Rock, paper or scissors |
| `PerformanceSummary.java` | Wins, losses, ties and win rate |
| `RoundResult.java` | One round's outcome |
| `RpsGameBot.java` | Drives the bundled rock-paper-scissors page in a real, headless Chrome with Selenium |
| `Strategy.java` | How the bot chooses its next move |

Also in `src/main/resources/`: `rps-game.html`.

Needs **Google Chrome** installed: Selenium runs it in the background. The game it plays is the bundled `rps-game.html`, not a real website.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day93
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day93
mvn test
```

Runs `CounterLastMoveStrategyTest`, `CyclingStrategyTest`, `PerformanceSummaryTest` and `RpsGameBotTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 94?** You'll build Space Invaders!
