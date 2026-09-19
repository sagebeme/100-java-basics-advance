package com.portfolio;

import java.util.List;
import java.util.Scanner;

public class TypingTestApp {

    private static final List<String> SAMPLE_TEXTS = List.of(
            "The quick brown fox jumps over the lazy dog",
            "Practice makes perfect when learning to type quickly",
            "Java is a popular language for building portable applications"
    );

    public static void main(String[] args) {
        String targetText = SAMPLE_TEXTS.get((int) (Math.random() * SAMPLE_TEXTS.size()));
        TypingTest test = new TypingTest(targetText);
        Stopwatch stopwatch = new Stopwatch();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Type this sentence as quickly and accurately as you can:");
            System.out.println();
            System.out.println(targetText);
            System.out.println();
            System.out.print("> ");

            stopwatch.start();
            String typed = scanner.nextLine();
            stopwatch.stop();

            TypingResult result = test.evaluate(typed, stopwatch.elapsedMillis());

            System.out.println();
            System.out.printf("Time: %.1f seconds%n", result.elapsedSeconds());
            System.out.printf("Speed: %.1f WPM%n", result.wpm());
            System.out.printf("Accuracy: %.1f%% (%d/%d characters correct)%n",
                    result.accuracyPercent(), result.correctCharacters(), result.totalCharactersTyped());
        }
    }
}
