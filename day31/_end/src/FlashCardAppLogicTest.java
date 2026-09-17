import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FlashCardAppLogicTest {

    @Test
    void formatsAQuestionAnswerAndKnownFlag() {
        assertEquals("2+2|4|false", FlashCardAppLogic.formatCard("2+2", "4", false));
    }

    @Test
    void parsesAWellFormedLine() {
        assertArrayEquals(new String[]{"2+2", "4", "true"}, FlashCardAppLogic.parseCard("2+2|4|true"));
    }

    @Test
    void rejectsALineWithOnlyOneField() {
        assertNull(FlashCardAppLogic.parseCard("2+2"));
    }

    @Test
    void wrapsAroundToTheFirstCardAfterTheLast() {
        assertEquals(0, FlashCardAppLogic.nextIndex(2, 3));
    }

    @Test
    void movesToTheNextCardOtherwise() {
        assertEquals(1, FlashCardAppLogic.nextIndex(0, 3));
    }
}
