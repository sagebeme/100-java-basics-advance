package com.portfolio;

import java.util.List;

/**
 * Rotates rock -> paper -> scissors -> rock regardless of history. Deterministic, so its exact
 * sequence of moves is directly testable.
 */
public class CyclingStrategy implements Strategy {

    private static final Move[] ORDER = {Move.ROCK, Move.PAPER, Move.SCISSORS};

    @Override
    public Move nextMove(List<RoundResult> history) {
        return ORDER[history.size() % ORDER.length];
    }
}
