package com.portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Drives a real, headless Chrome browser via Selenium against the bundled local game page - a
 * genuine end-to-end automation test, not a mock of the WebDriver.
 */
class RpsGameBotTest {

    @Test
    void playsARealRoundAgainstTheActualBrowserGame() throws Exception {
        ActivityLog log = new ActivityLog();
        try (RpsGameBot bot = new RpsGameBot(log)) {
            RoundResult round = bot.playRound(new CyclingStrategy());

            assertEquals(Move.ROCK, round.playerMove());
            assertTrue(java.util.List.of("rock", "paper", "scissors").contains(round.computerMove()));
            assertTrue(java.util.List.of("win", "loss", "tie").contains(round.outcome()));
        }
        assertFalse(log.getEntries().isEmpty());
    }

    @Test
    void playingMultipleRoundsProducesAConsistentPerformanceSummary() throws Exception {
        ActivityLog log = new ActivityLog();
        try (RpsGameBot bot = new RpsGameBot(log)) {
            PerformanceSummary summary = bot.playGames(new CyclingStrategy(), 10);

            assertEquals(10, summary.totalRounds());
            assertEquals(10, summary.wins() + summary.losses() + summary.ties());
            assertEquals(10, bot.getHistory().size());

            // Round numbers should be sequential starting at 1.
            for (int i = 0; i < bot.getHistory().size(); i++) {
                assertEquals(i + 1, bot.getHistory().get(i).roundNumber());
            }
        }
    }

    @Test
    void activityLogRecordsEveryRoundPlayed() throws Exception {
        ActivityLog log = new ActivityLog();
        try (RpsGameBot bot = new RpsGameBot(log)) {
            bot.playGames(new CyclingStrategy(), 3);
        }

        // "Round " (with the trailing space) matches only "Round 1: played..." lines, not the
        // final summary line, whose PerformanceSummary#toString() also happens to contain
        // "Round" as part of the field name "totalRounds".
        long roundEntries = log.getEntries().stream().filter(e -> e.contains("Round ")).count();
        assertEquals(3, roundEntries);
    }
}
