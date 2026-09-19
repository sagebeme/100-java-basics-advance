package com.portfolio;

import java.util.List;

public record PerformanceSummary(int wins, int losses, int ties, int totalRounds) {

    public static PerformanceSummary from(List<RoundResult> history) {
        int wins = 0, losses = 0, ties = 0;
        for (RoundResult round : history) {
            switch (round.outcome()) {
                case "win" -> wins++;
                case "loss" -> losses++;
                case "tie" -> ties++;
                default -> throw new IllegalStateException("Unknown outcome: " + round.outcome());
            }
        }
        return new PerformanceSummary(wins, losses, ties, history.size());
    }

    public double winRate() {
        return totalRounds == 0 ? 0.0 : (double) wins / totalRounds;
    }
}
