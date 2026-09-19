package com.portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypingTestTest {

    @Test
    void perfectTypingScores100PercentAccuracy() {
        TypingTest test = new TypingTest("hello world");
        TypingResult result = test.evaluate("hello world", 60_000); // 1 minute

        assertEquals(100.0, result.accuracyPercent(), 0.001);
        assertEquals(11, result.correctCharacters());
    }

    @Test
    void wpmMatchesTheStandardFiveCharactersPerWordConvention() {
        // 50 characters typed in exactly 1 minute = 10 "words" (5 chars each) = 10 WPM.
        String fiftyChars = "a".repeat(50);
        TypingTest test = new TypingTest(fiftyChars);
        TypingResult result = test.evaluate(fiftyChars, 60_000);

        assertEquals(10.0, result.wpm(), 0.001);
    }

    @Test
    void typingFasterProducesAHigherWpm() {
        String text = "a".repeat(50);
        TypingTest test = new TypingTest(text);

        TypingResult slow = test.evaluate(text, 60_000);
        TypingResult fast = test.evaluate(text, 30_000);

        assertTrue(fast.wpm() > slow.wpm());
    }

    @Test
    void completelyWrongTypingScoresZeroAccuracyButStillCountsCharacters() {
        TypingTest test = new TypingTest("abcde");
        TypingResult result = test.evaluate("fghij", 60_000);

        assertEquals(0.0, result.accuracyPercent(), 0.001);
        assertEquals(0, result.correctCharacters());
        assertEquals(5, result.totalCharactersTyped());
    }

    @Test
    void partiallyCorrectTypingScoresProportionalAccuracy() {
        TypingTest test = new TypingTest("abcde");
        // "abcXX": first 3 characters match, last 2 don't -> 3/5 = 60%
        TypingResult result = test.evaluate("abcXX", 60_000);

        assertEquals(60.0, result.accuracyPercent(), 0.001);
        assertEquals(3, result.correctCharacters());
    }

    @Test
    void typingLessThanTheTargetIsPenalizedByTheLongerLength() {
        // Target is 10 chars, typed only 5 (all correct) -> accuracy is measured against the
        // longer of the two lengths, so an incomplete attempt can't score 100%.
        TypingTest test = new TypingTest("abcdefghij");
        TypingResult result = test.evaluate("abcde", 60_000);

        assertEquals(50.0, result.accuracyPercent(), 0.001);
    }

    @Test
    void typingMoreThanTheTargetIsAlsoPenalizedByTheLongerLength() {
        TypingTest test = new TypingTest("abcde");
        TypingResult result = test.evaluate("abcdeXXXXX", 60_000);

        assertEquals(50.0, result.accuracyPercent(), 0.001);
    }

    @Test
    void constructingWithAnEmptyTargetThrows() {
        assertThrows(IllegalArgumentException.class, () -> new TypingTest(""));
    }

    @Test
    void evaluatingWithNonPositiveElapsedTimeThrows() {
        TypingTest test = new TypingTest("hello");
        assertThrows(IllegalArgumentException.class, () -> test.evaluate("hello", 0));
        assertThrows(IllegalArgumentException.class, () -> test.evaluate("hello", -100));
    }
}
