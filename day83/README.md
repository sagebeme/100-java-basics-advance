# Day 83 - Portfolio Project: Tic Tac Toe Game

> Everyone knows the rules, which makes it the perfect game to build properly: rules in one class with tests, a JavaFX window on top that only draws and forwards clicks.

## 📖 The story

On long bus rides to Mombasa, Amani and her little brother play Tic Tac Toe (they call it "X na O") in the margins of a newspaper, and every game ends in an argument about whose turn it was. Amani decides to build it on her laptop, with the computer keeping score of whose turn it is, who won, and whether a square is already taken.

A JavaFX window with a 3×3 wooden board, a status line ("Player X's turn", "Player O wins!", "It's a draw!") and a Reset button. All the rules live in `TicTacToeGame`, which knows nothing about windows, so every rule can be tested without clicking anything.

## 🎯 Learning Objectives
- Keep game rules separate from the user interface
- Represent a board as a 2D array, and hand out copies rather than the original
- Check every way to win: three rows, three columns, two diagonals
- Refuse illegal moves with exceptions: an occupied cell, off the board, or after the game is over
- Build a grid of buttons in JavaFX and connect each to a move
- Style a JavaFX app with a CSS file

## 🧰 What you'll use

**The rules class:**

```java
TicTacToeGame game = new TicTacToeGame();
game.makeMove(1, 1);           // X takes the centre
game.makeMove(0, 0);           // O takes a corner
game.getCurrentPlayer();       // 'X'
game.getWinner();              // null until someone has three in a line
game.isDraw();                 // a full board with no winner
```

**Checking for a win** is eight lines of three:

```java
for (int i = 0; i < 3; i++) {
    if (lineWins(board[i][0], board[i][1], board[i][2])) return board[i][0];   // row i
    if (lineWins(board[0][i], board[1][i], board[2][i])) return board[0][i];   // column i
}
// then the two diagonals
```

**Defensive copies.** `getBoard()` returns a copy, so a caller can't change the board without going through `makeMove` and its checks.

**A grid of buttons:**

```java
Button cell = new Button(" ");
int r = row, c = col;                  // copies the lambda can capture
cell.setOnAction(e -> handleMove(r, c));
grid.add(cell, col, row);              // GridPane takes the column first
```

## 🪜 Steps

**Your brief** (the `day83/` folder is one finished version: build your own first, then compare).

1. `TicTacToeGame`: the board, whose turn, `makeMove`, the winner, a draw, `reset`.
2. Tests for each rule: alternating turns, each kind of win, a draw, and each illegal move.
3. `TicTacToeApp`: the window, the buttons, the status line and Reset.
4. `tictactoe.css` in `src/main/resources` to make it look like a board.
5. Make it yours: a score across games, or a computer opponent. (Start with "win if you can, block if you must, otherwise take the centre"; for a perfect player, look up *minimax*.)

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day83
mvn test            # the rules, no window needed
mvn javafx:run      # play
```

## 🆘 Stuck?

- **`GridPane` puts the buttons in the wrong place**: `grid.add(node, column, row)`: column first.
- **"Local variables referenced from a lambda must be final"**: copy `row` and `col` into new variables inside the loop.
- **Clicking a taken square crashes**: catch the `IllegalArgumentException` in the app and ignore the click; the rule stays in the game class.
- **A win on the last square is reported as a draw**: check for a winner before checking for a full board.

## ✅ Checklist
- [ ] Rules in a class with no JavaFX in it
- [ ] Every win, the draw and every illegal move tested
- [ ] A playable window with status and Reset
- [ ] Styled with CSS
- [ ] Added a score or a computer opponent
- [ ] Committed code to Git

## ➡️ Next

Day 84 works with images: a tool that stamps a watermark on photos, one at a time or a whole folder at once.
