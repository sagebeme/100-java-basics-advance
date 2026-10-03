import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CherrySorterTest {

    @Test
    void acceptsARipeHealthyCherry() {
        assertEquals("ACCEPT", CherrySorter.verdict("red", false, false));
    }

    @Test
    void ignoresCapitalsAndSpacesInTheColour() {
        assertEquals("ACCEPT", CherrySorter.verdict("  RED ", false, false));
    }

    @Test
    void insectDamageBeatsAGoodColour() {
        assertEquals("REJECT: insect damage", CherrySorter.verdict("red", false, true));
    }

    @Test
    void aFloaterIsRejectedEvenWhenRed() {
        assertEquals("REJECT: floater", CherrySorter.verdict("red", true, false));
    }

    @Test
    void greenAndYellowAreUnripe() {
        assertEquals("REJECT: unripe", CherrySorter.verdict("green", false, false));
        assertEquals("REJECT: unripe", CherrySorter.verdict("Yellow", false, false));
    }

    @Test
    void blackAndBrownAreOverripe() {
        assertEquals("REJECT: overripe", CherrySorter.verdict("black", false, false));
        assertEquals("REJECT: overripe", CherrySorter.verdict("brown", false, false));
    }

    @Test
    void anUnknownColourGoesToAPerson() {
        assertEquals("CHECK BY HAND", CherrySorter.verdict("purple", false, false));
    }
}
