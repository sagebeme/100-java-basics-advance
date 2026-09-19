package com.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreedyAlgorithms {

    public record Activity(int start, int end) {
    }

    /**
     * Activity selection: greedily pick the activity that finishes earliest, then the next
     * one that starts after it finishes, and so on. This greedy rule provably maximizes the
     * number of non-overlapping activities selected.
     */
    public List<Activity> selectActivities(List<Activity> activities) {
        List<Activity> sorted = new ArrayList<>(activities);
        sorted.sort((a, b) -> Integer.compare(a.end(), b.end()));

        List<Activity> selected = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;
        for (Activity activity : sorted) {
            if (activity.start() >= lastEnd) {
                selected.add(activity);
                lastEnd = activity.end();
            }
        }
        return selected;
    }

    /**
     * Greedy coin change: always take as many of the largest coin as possible, then move to
     * the next. This is optimal for "canonical" coin systems like US currency, but is NOT
     * guaranteed optimal for arbitrary denominations - see GreedyAlgorithmsTest for a case
     * where it fails, which is the actual point of calling this "greedy" rather than "correct".
     */
    public List<Integer> greedyCoinChange(int[] denominationsDescending, int amount) {
        List<Integer> coinsUsed = new ArrayList<>();
        int remaining = amount;
        for (int coin : denominationsDescending) {
            while (remaining >= coin) {
                coinsUsed.add(coin);
                remaining -= coin;
            }
        }
        return coinsUsed;
    }

    public static void main(String[] args) {
        GreedyAlgorithms greedy = new GreedyAlgorithms();

        List<Activity> activities = List.of(
                new Activity(1, 4), new Activity(3, 5), new Activity(0, 6),
                new Activity(5, 7), new Activity(3, 9), new Activity(5, 9),
                new Activity(6, 10), new Activity(8, 11), new Activity(8, 12),
                new Activity(2, 14), new Activity(12, 16)
        );
        System.out.println("Selected activities: " + greedy.selectActivities(activities));

        int[] usCoins = {25, 10, 5, 1};
        System.out.println("Change for 41 cents: " + greedy.greedyCoinChange(usCoins, 41));
    }
}
