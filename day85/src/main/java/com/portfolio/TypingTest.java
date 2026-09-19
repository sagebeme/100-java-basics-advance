package com.portfolio;

public class TypingTest {

    private final String targetText;

    public TypingTest(String targetText) {
        if (targetText == null || targetText.isEmpty()) {
            throw new IllegalArgumentException("targetText must not be empty");
        }
        this.targetText = targetText;
    }

    public String getTargetText() {
        return targetText;
    }

    /**
     * Scores a completed attempt. Timing is passed in as elapsedMillis rather than measured here,
     * so the scoring math itself stays deterministic and testable independent of a real clock -
     * see Stopwatch for the actual timing used by TypingTestApp.
     */
    public TypingResult evaluate(String typedText, long elapsedMillis) {
        if (elapsedMillis <= 0) {
            throw new IllegalArgumentException("elapsedMillis must be positive");
        }

        int correct = countCorrectCharacters(typedText);
        int comparedLength = Math.max(targetText.length(), typedText.length());
        double accuracy = comparedLength == 0 ? 100.0 : (correct * 100.0) / comparedLength;

        double minutes = elapsedMillis / 60000.0;
        double wpm = (typedText.length() / 5.0) / minutes;

        return new TypingResult(wpm, accuracy, correct, typedText.length(), elapsedMillis / 1000.0);
    }

    private int countCorrectCharacters(String typedText) {
        int limit = Math.min(targetText.length(), typedText.length());
        int correct = 0;
        for (int i = 0; i < limit; i++) {
            if (targetText.charAt(i) == typedText.charAt(i)) {
                correct++;
            }
        }
        return correct;
    }
}
