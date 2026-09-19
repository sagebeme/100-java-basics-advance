package com.portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MorseConverterTest {

    private final MorseConverter converter = new MorseConverter();

    @Test
    void convertsASingleWordToMorse() {
        assertEquals(".... . .-.. .-.. ---", converter.textToMorse("HELLO"));
    }

    @Test
    void convertsIsCaseInsensitive() {
        assertEquals(".... . .-.. .-.. ---", converter.textToMorse("hello"));
    }

    @Test
    void separatesWordsWithASlash() {
        assertEquals(".... . .-.. .-.. --- / .-- --- .-. .-.. -..", converter.textToMorse("HELLO WORLD"));
    }

    @Test
    void convertsDigitsAndPunctuation() {
        assertEquals("-.... ----- / -.-.--", converter.textToMorse("60 !"));
    }

    @Test
    void dropsCharactersThatHaveNoMorseMapping() {
        // '@' has no mapping, so it should simply be omitted rather than corrupting the output.
        assertEquals(".- -...", converter.textToMorse("A@B"));
    }

    @Test
    void convertsMorseBackToText() {
        assertEquals("HELLO", converter.morseToText(".... . .-.. .-.. ---"));
    }

    @Test
    void convertsMorseWithMultipleWordsBackToText() {
        assertEquals("HELLO WORLD", converter.morseToText(".... . .-.. .-.. --- / .-- --- .-. .-.. -.."));
    }

    @Test
    void roundTripsThroughBothConversionsForAVarietyOfInputs() {
        for (String text : new String[]{"HELLO WORLD", "JAVA 101", "GOOD LUCK"}) {
            assertEquals(text, converter.morseToText(converter.textToMorse(text)));
        }
    }

    @Test
    void anUnrecognizedMorseTokenIsSkippedRatherThanCrashing() {
        // "......." isn't a valid Morse letter, so it's dropped from the decoded word.
        assertEquals("HI", converter.morseToText(".... .. ......."));
    }
}
