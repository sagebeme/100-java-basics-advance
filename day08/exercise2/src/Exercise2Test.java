import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void convertsToUppercase() {
        assertEquals('A', Exercise2.toUpperChar('a'));
    }

    @Test
    void convertsToLowercase() {
        assertEquals('a', Exercise2.toLowerChar('A'));
    }

    @Test
    void shiftsALetterForward() {
        assertEquals('c', Exercise2.shift('a', 2));
    }

    @Test
    void shiftingWrapsAroundTheAlphabet() {
        assertEquals('a', Exercise2.shift('y', 2));
    }

    @Test
    void shiftingPreservesNonLetters() {
        assertEquals('5', Exercise2.shift('5', 3));
    }

    @Test
    void recognisesLetters() {
        assertTrue(Exercise2.isLetterChar('m'));
        assertFalse(Exercise2.isLetterChar('7'));
    }

    @Test
    void findsAlphabetPosition() {
        assertEquals(1, Exercise2.alphabetPosition('a'));
        assertEquals(3, Exercise2.alphabetPosition('C'));
        assertEquals(-1, Exercise2.alphabetPosition('9'));
    }
}
