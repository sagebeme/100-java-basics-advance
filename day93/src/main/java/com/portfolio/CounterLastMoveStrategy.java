package com.portfolio;

import java.util.List;
import java.util.Map;

/**
 * Reads the opponent's last observed move from game state (history) and plays whatever beats it.
 * Against a genuinely random opponent this has no long-run edge over any other strategy (there is
 * no such thing as a winning strategy against uniform-random RPS), but it demonstrates reacting to
 * observed game state rather than playing blind, which is the actual point of this strategy.
 */
public class CounterLastMoveStrategy implements Strategy {

    private static final Map<String, Move> BEATS = Map.of(
            "rock", Move.PAPER,
            "paper", Move.SCISSORS,
            "scissors", Move.ROCK);

    @Override
    public Move nextMove(List<RoundResult> history) {
        if (history.isEmpty()) {
            return Move.ROCK;
        }
        String lastComputerMove = history.get(history.size() - 1).computerMove();
        return BEATS.getOrDefault(lastComputerMove, Move.ROCK);
    }
}
