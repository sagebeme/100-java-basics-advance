package com.portfolio;

public enum Move {
    ROCK, PAPER, SCISSORS;

    public String toButtonId() {
        return name().toLowerCase();
    }
}
