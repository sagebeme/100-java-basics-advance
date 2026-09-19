package com.portfolio;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CounterLastMoveStrategyTest {

    private final CounterLastMoveStrategy strategy = new CounterLastMoveStrategy();

    @Test
    void playsRockWithNoHistory() {
        assertEquals(Move.ROCK, strategy.nextMove(List.of()));
    }

    @Test
    void countersTheOpponentsLastMove() {
        assertEquals(Move.PAPER, strategy.nextMove(List.of(new RoundResult(1, Move.ROCK, "rock", "tie"))));
        assertEquals(Move.SCISSORS, strategy.nextMove(List.of(new RoundResult(1, Move.ROCK, "paper", "loss"))));
        assertEquals(Move.ROCK, strategy.nextMove(List.of(new RoundResult(1, Move.PAPER, "scissors", "loss"))));
    }

    @Test
    void onlyLooksAtTheMostRecentRound() {
        List<RoundResult> history = List.of(
                new RoundResult(1, Move.ROCK, "paper", "loss"),
                new RoundResult(2, Move.PAPER, "rock", "win"));

        assertEquals(Move.PAPER, strategy.nextMove(history)); // counters "rock" from round 2
    }
}
