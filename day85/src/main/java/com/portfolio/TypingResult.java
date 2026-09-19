package com.portfolio;

public record TypingResult(double wpm, double accuracyPercent, int correctCharacters, int totalCharactersTyped, double elapsedSeconds) {
}
