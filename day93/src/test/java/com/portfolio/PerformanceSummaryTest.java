package com.portfolio;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PerformanceSummaryTest {

    @Test
    void tallysWinsLossesAndTiesFromHistory() {
        List<RoundResult> history = List.of(
                new RoundResult(1, Move.ROCK, "scissors", "win"),
                new RoundResult(2, Move.ROCK, "paper", "loss"),
                new RoundResult(3, Move.ROCK, "rock", "tie"),
                new RoundResult(4, Move.PAPER, "rock", "win"));

        PerformanceSummary summary = PerformanceSummary.from(history);

        assertEquals(2, summary.wins());
        assertEquals(1, summary.losses());
        assertEquals(1, summary.ties());
        assertEquals(4, summary.totalRounds());
        assertEquals(0.5, summary.winRate(), 0.001);
    }

    @Test
    void winRateIsZeroForNoRounds() {
        assertEquals(0.0, PerformanceSummary.from(List.of()).winRate());
    }

    @Test
    void rejectsAnUnknownOutcome() {
        List<RoundResult> history = List.of(new RoundResult(1, Move.ROCK, "rock", "draw"));
        assertThrows(IllegalStateException.class, () -> PerformanceSummary.from(history));
    }
}
