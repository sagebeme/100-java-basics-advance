import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NATOPhoneticTest {

    @Test
    void spellsOutEachLetterOfAWord() {
        assertEquals("J for Juliet\nA for Alpha\nV for Victor\nA for Alpha", NATOPhonetic.toNato("java"));
    }

    @Test
    void isCaseInsensitive() {
        assertEquals(NATOPhonetic.toNato("java"), NATOPhonetic.toNato("JAVA"));
    }

    @Test
    void skipsNonLetterCharacters() {
        assertEquals("A for Alpha\nB for Bravo", NATOPhonetic.toNato("a-b!"));
    }
}
