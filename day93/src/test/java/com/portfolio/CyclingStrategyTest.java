package com.portfolio;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CyclingStrategyTest {

    private final CyclingStrategy strategy = new CyclingStrategy();

    @Test
    void cyclesThroughRockPaperScissorsInOrder() {
        List<RoundResult> history = new ArrayList<>();

        assertEquals(Move.ROCK, strategy.nextMove(history));
        history.add(new RoundResult(1, Move.ROCK, "paper", "loss"));

        assertEquals(Move.PAPER, strategy.nextMove(history));
        history.add(new RoundResult(2, Move.PAPER, "rock", "win"));

        assertEquals(Move.SCISSORS, strategy.nextMove(history));
        history.add(new RoundResult(3, Move.SCISSORS, "paper", "win"));

        assertEquals(Move.ROCK, strategy.nextMove(history)); // wraps back around
    }
}
