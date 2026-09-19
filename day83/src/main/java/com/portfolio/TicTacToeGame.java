package com.portfolio;

public class TicTacToeGame {

    public static final char EMPTY = ' ';

    private final char[][] board = new char[3][3];
    private char currentPlayer;
    private Character winner;

    public TicTacToeGame() {
        reset();
    }

    public void reset() {
        for (char[] row : board) {
            java.util.Arrays.fill(row, EMPTY);
        }
        currentPlayer = 'X';
        winner = null;
    }

    public void makeMove(int row, int col) {
        if (isGameOver()) {
            throw new IllegalStateException("The game is already over");
        }
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Row and column must be between 0 and 2");
        }
        if (board[row][col] != EMPTY) {
            throw new IllegalArgumentException("Cell (" + row + "," + col + ") is already occupied");
        }

        board[row][col] = currentPlayer;
        winner = computeWinner();
        if (winner == null) {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public char[][] getBoard() {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public Character getWinner() {
        return winner;
    }

    public boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isDraw() {
        return winner == null && isBoardFull();
    }

    public boolean isGameOver() {
        return winner != null || isBoardFull();
    }

    private Character computeWinner() {
        for (int i = 0; i < 3; i++) {
            if (lineWins(board[i][0], board[i][1], board[i][2])) return board[i][0];
            if (lineWins(board[0][i], board[1][i], board[2][i])) return board[0][i];
        }
        if (lineWins(board[0][0], board[1][1], board[2][2])) return board[0][0];
        if (lineWins(board[0][2], board[1][1], board[2][0])) return board[0][2];
        return null;
    }

    private boolean lineWins(char a, char b, char c) {
        return a != EMPTY && a == b && b == c;
    }
}
