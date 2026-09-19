package com.portfolio;

public class GameAutomationApp {

    public static void main(String[] args) throws Exception {
        ActivityLog activityLog = new ActivityLog();

        try (RpsGameBot bot = new RpsGameBot(activityLog)) {
            PerformanceSummary summary = bot.playGames(new CounterLastMoveStrategy(), 20);

            activityLog.getEntries().forEach(System.out::println);
            System.out.println();
            System.out.printf("Wins: %d, Losses: %d, Ties: %d, Win rate: %.1f%%%n",
                    summary.wins(), summary.losses(), summary.ties(), summary.winRate() * 100);
        }
    }
}
