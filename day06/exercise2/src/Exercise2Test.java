import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void reversesAString() {
        assertEquals("avaJ", Exercise2.reverse("Java"));
    }

    @Test
    void recognisesASimplePalindrome() {
        assertTrue(Exercise2.isPalindrome("madam"));
    }

    @Test
    void ignoresCaseAndSpacesWhenCheckingPalindromes() {
        assertTrue(Exercise2.isPalindrome("Was it a car or a cat I saw"));
    }

    @Test
    void recognisesANonPalindrome() {
        assertFalse(Exercise2.isPalindrome("hello"));
    }

    @Test
    void countsVowelsCaseInsensitively() {
        assertEquals(3, Exercise2.countVowels("Programming"));
    }

    @Test
    void capitalisesTheFirstLetterOfEachWord() {
        assertEquals("Habari Za Asubuhi", Exercise2.toTitleCase("habari za asubuhi"));
    }
}
