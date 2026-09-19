package com.learning;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GreedyAlgorithmsTest {

    private final GreedyAlgorithms greedy = new GreedyAlgorithms();

    @Test
    void selectActivitiesMatchesTheClassicTextbookExample() {
        // The classic CLRS activity-selection example (11 activities).
        List<GreedyAlgorithms.Activity> activities = List.of(
                new GreedyAlgorithms.Activity(1, 4), new GreedyAlgorithms.Activity(3, 5),
                new GreedyAlgorithms.Activity(0, 6), new GreedyAlgorithms.Activity(5, 7),
                new GreedyAlgorithms.Activity(3, 9), new GreedyAlgorithms.Activity(5, 9),
                new GreedyAlgorithms.Activity(6, 10), new GreedyAlgorithms.Activity(8, 11),
                new GreedyAlgorithms.Activity(8, 12), new GreedyAlgorithms.Activity(2, 14),
                new GreedyAlgorithms.Activity(12, 16)
        );

        List<GreedyAlgorithms.Activity> selected = greedy.selectActivities(activities);

        assertEquals(
                List.of(
                        new GreedyAlgorithms.Activity(1, 4),
                        new GreedyAlgorithms.Activity(5, 7),
                        new GreedyAlgorithms.Activity(8, 11),
                        new GreedyAlgorithms.Activity(12, 16)
                ),
                selected
        );
    }

    @Test
    void selectActivitiesReturnsNothingForAnEmptyList() {
        assertTrue(greedy.selectActivities(List.of()).isEmpty());
    }

    @Test
    void selectActivitiesTakesTheOnlyOptionWhenThereIsNoOverlap() {
        List<GreedyAlgorithms.Activity> activities = List.of(
                new GreedyAlgorithms.Activity(1, 2), new GreedyAlgorithms.Activity(3, 4)
        );
        assertEquals(2, greedy.selectActivities(activities).size());
    }

    @Test
    void greedyCoinChangeIsOptimalForCanonicalUsDenominations() {
        int[] usCoins = {25, 10, 5, 1};
        assertEquals(List.of(25, 10, 5, 1), greedy.greedyCoinChange(usCoins, 41));
    }

    @Test
    void greedyCoinChangeIsNotOptimalForNonCanonicalDenominations() {
        // With coins {4, 3, 1} and amount 6: greedy takes 4 first, leaving 2, which it can only
        // make as 1+1, for 3 coins total [4, 1, 1]. The true optimum is 2 coins: [3, 3].
        // This demonstrates that the greedy "largest coin first" rule is NOT guaranteed optimal
        // for arbitrary denominations, unlike canonical systems such as US currency.
        int[] nonCanonicalCoins = {4, 3, 1};
        List<Integer> greedyResult = greedy.greedyCoinChange(nonCanonicalCoins, 6);

        assertEquals(List.of(4, 1, 1), greedyResult);
        assertEquals(3, greedyResult.size());

        // The true optimal solution uses only 2 coins (3 + 3), proving greedy is suboptimal here.
        int optimalCoinCount = 2;
        assertTrue(greedyResult.size() > optimalCoinCount,
                "Greedy should use more coins than the true optimum for this denomination set");
    }
}
