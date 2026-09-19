package com.portfolio;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    private final TicTacToeGame game = new TicTacToeGame();
    private final Button[][] cells = new Button[3][3];
    private Label statusLabel;

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button cell = new Button(" ");
                cell.setMinSize(80, 80);
                cell.setStyle("-fx-font-size: 28px;");
                int r = row;
                int c = col;
                cell.setOnAction(e -> handleMove(r, c));
                cells[row][col] = cell;
                grid.add(cell, col, row);
            }
        }

        statusLabel = new Label("Player X's turn");
        statusLabel.setStyle("-fx-font-size: 16px;");

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> handleReset());

        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setTop(statusLabel);
        root.setBottom(resetButton);
        BorderPane.setAlignment(statusLabel, Pos.CENTER);
        BorderPane.setAlignment(resetButton, Pos.CENTER);

        stage.setScene(new Scene(root, 320, 400));
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    private void handleMove(int row, int col) {
        if (game.isGameOver()) {
            return;
        }
        try {
            game.makeMove(row, col);
        } catch (IllegalArgumentException ex) {
            return; // cell already occupied - ignore the click
        }
        cells[row][col].setText(String.valueOf(game.getCell(row, col)));
        updateStatus();
    }

    private void updateStatus() {
        if (game.getWinner() != null) {
            statusLabel.setText("Player " + game.getWinner() + " wins!");
        } else if (game.isDraw()) {
            statusLabel.setText("It's a draw!");
        } else {
            statusLabel.setText("Player " + game.getCurrentPlayer() + "'s turn");
        }
    }

    private void handleReset() {
        game.reset();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setText(" ");
            }
        }
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
