package com.portfolio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameTest {

    private TicTacToeGame game;

    @BeforeEach
    void setUp() {
        game = new TicTacToeGame();
    }

    @Test
    void startsEmptyWithPlayerXFirst() {
        assertEquals('X', game.getCurrentPlayer());
        assertFalse(game.isGameOver());
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertEquals(TicTacToeGame.EMPTY, game.getCell(r, c));
            }
        }
    }

    @Test
    void playersAlternateAfterEachMove() {
        game.makeMove(0, 0); // X
        assertEquals('O', game.getCurrentPlayer());
        game.makeMove(0, 1); // O
        assertEquals('X', game.getCurrentPlayer());
    }

    @Test
    void detectsARowWin() {
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(0, 2); // X wins top row

        assertEquals(Character.valueOf('X'), game.getWinner());
        assertTrue(game.isGameOver());
    }

    @Test
    void detectsAColumnWin() {
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(0, 2); // O
        game.makeMove(2, 0); // X wins left column

        assertEquals(Character.valueOf('X'), game.getWinner());
    }

    @Test
    void detectsADiagonalWin() {
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 1); // X
        game.makeMove(0, 2); // O
        game.makeMove(2, 2); // X wins the diagonal

        assertEquals(Character.valueOf('X'), game.getWinner());
    }

    @Test
    void detectsTheAntiDiagonalWin() {
        game.makeMove(0, 2); // X
        game.makeMove(0, 0); // O
        game.makeMove(1, 1); // X
        game.makeMove(0, 1); // O
        game.makeMove(2, 0); // X wins the anti-diagonal

        assertEquals(Character.valueOf('X'), game.getWinner());
    }

    @Test
    void detectsADrawWhenTheBoardFillsWithNoWinner() {
        // X O X
        // X O O
        // O X X
        int[][] xMoves = {{0, 0}, {0, 2}, {1, 0}, {2, 1}, {2, 2}};
        int[][] oMoves = {{0, 1}, {1, 1}, {1, 2}, {2, 0}};

        game.makeMove(xMoves[0][0], xMoves[0][1]);
        game.makeMove(oMoves[0][0], oMoves[0][1]);
        game.makeMove(xMoves[1][0], xMoves[1][1]);
        game.makeMove(oMoves[1][0], oMoves[1][1]);
        game.makeMove(xMoves[2][0], xMoves[2][1]);
        game.makeMove(oMoves[2][0], oMoves[2][1]);
        game.makeMove(xMoves[3][0], xMoves[3][1]);
        game.makeMove(oMoves[3][0], oMoves[3][1]);
        game.makeMove(xMoves[4][0], xMoves[4][1]);

        assertTrue(game.isDraw());
        assertNull(game.getWinner());
        assertTrue(game.isGameOver());
    }

    @Test
    void movingOnAnOccupiedCellThrows() {
        game.makeMove(0, 0);
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(0, 0));
    }

    @Test
    void movingOutOfBoundsThrows() {
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(3, 0));
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(0, -1));
    }

    @Test
    void movingAfterTheGameIsOverThrows() {
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(0, 2); // X wins

        assertThrows(IllegalStateException.class, () -> game.makeMove(2, 2));
    }

    @Test
    void resetClearsTheBoardAndRestoresPlayerX() {
        game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.reset();

        assertEquals('X', game.getCurrentPlayer());
        assertNull(game.getWinner());
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertEquals(TicTacToeGame.EMPTY, game.getCell(r, c));
            }
        }
    }
}
