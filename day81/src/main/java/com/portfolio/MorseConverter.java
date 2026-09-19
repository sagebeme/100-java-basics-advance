package com.portfolio;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MorseConverter {

    private static final Map<Character, String> TEXT_TO_MORSE = new HashMap<>();
    private static final Map<String, Character> MORSE_TO_TEXT = new HashMap<>();

    static {
        put('A', ".-");
        put('B', "-...");
        put('C', "-.-.");
        put('D', "-..");
        put('E', ".");
        put('F', "..-.");
        put('G', "--.");
        put('H', "....");
        put('I', "..");
        put('J', ".---");
        put('K', "-.-");
        put('L', ".-..");
        put('M', "--");
        put('N', "-.");
        put('O', "---");
        put('P', ".--.");
        put('Q', "--.-");
        put('R', ".-.");
        put('S', "...");
        put('T', "-");
        put('U', "..-");
        put('V', "...-");
        put('W', ".--");
        put('X', "-..-");
        put('Y', "-.--");
        put('Z', "--..");
        put('0', "-----");
        put('1', ".----");
        put('2', "..---");
        put('3', "...--");
        put('4', "....-");
        put('5', ".....");
        put('6', "-....");
        put('7', "--...");
        put('8', "---..");
        put('9', "----.");
        put('.', ".-.-.-");
        put(',', "--..--");
        put('?', "..--..");
        put('\'', ".----.");
        put('!', "-.-.--");
        put('/', "-..-.");
        put('-', "-....-");
        put('(', "-.--.");
        put(')', "-.--.-");
    }

    private static void put(char letter, String morse) {
        TEXT_TO_MORSE.put(letter, morse);
        MORSE_TO_TEXT.put(morse, letter);
    }

    /**
     * Converts text to Morse code. Letters within a word are separated by a single space, and
     * words are separated by " / " (the standard Morse convention). Unknown characters (other
     * than whitespace) are dropped rather than emitting garbage tokens that morseToText couldn't
     * reverse anyway.
     */
    public String textToMorse(String text) {
        String[] words = text.trim().toUpperCase().split("\\s+");
        return java.util.Arrays.stream(words)
                .map(this::wordToMorse)
                .collect(Collectors.joining(" / "));
    }

    private String wordToMorse(String word) {
        return word.chars()
                .mapToObj(c -> TEXT_TO_MORSE.get((char) c))
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.joining(" "));
    }

    /**
     * Converts Morse code back to text. Expects letters separated by a single space and words
     * separated by " / ", matching textToMorse's own output format.
     */
    public String morseToText(String morse) {
        String[] words = morse.trim().split(" / ");
        return java.util.Arrays.stream(words)
                .map(this::morseWordToText)
                .collect(Collectors.joining(" "));
    }

    private String morseWordToText(String morseWord) {
        StringBuilder builder = new StringBuilder();
        for (String token : morseWord.trim().split("\\s+")) {
            Character letter = MORSE_TO_TEXT.get(token);
            if (letter != null) {
                builder.append(letter);
            }
        }
        return builder.toString();
    }
}
