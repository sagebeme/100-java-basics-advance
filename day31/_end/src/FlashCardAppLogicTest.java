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

    @Test
    void studySkipsKnownCards() {
        // Cards 1 and 2 are known, so after card 0 comes card 3.
        assertEquals(3, FlashCardAppLogic.nextToStudy(0, new boolean[] {false, true, true, false}));
    }

    @Test
    void studyGoesRoundToTheStart() {
        assertEquals(0, FlashCardAppLogic.nextToStudy(3, new boolean[] {false, true, true, false}));
    }

    @Test
    void studyStaysOnTheOnlyCardLeftToLearn() {
        assertEquals(2, FlashCardAppLogic.nextToStudy(2, new boolean[] {true, true, false}));
    }

    @Test
    void studyReturnsMinusOneWhenEveryCardIsKnown() {
        assertEquals(-1, FlashCardAppLogic.nextToStudy(0, new boolean[] {true, true}));
    }
}
