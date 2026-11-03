import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {

    private final Question route46 = new Question("Route 46 goes to Kawangware", "True");
    private final Question route111 = new Question("Route 111 goes to Kawangware", "false");

    @Test
    void keepsTheText() {
        assertEquals("Route 46 goes to Kawangware", route46.getText());
    }

    @Test
    void acceptsTheRightAnswerHoweverItIsTyped() {
        assertTrue(route46.checkAnswer("True"));
        assertTrue(route46.checkAnswer("true"));
        assertTrue(route46.checkAnswer("  TRUE "));
        assertTrue(route46.checkAnswer("t"));
        assertTrue(route46.checkAnswer("T"));
        assertTrue(route111.checkAnswer("False"));
        assertTrue(route111.checkAnswer(" f"));
    }

    @Test
    void rejectsTheWrongAnswer() {
        assertFalse(route46.checkAnswer("False"));
        assertFalse(route46.checkAnswer("f"));
        assertFalse(route111.checkAnswer("true"));
    }

    @Test
    void aBlankOrUnknownReplyIsWrongNotAnError() {
        assertFalse(route46.checkAnswer(""));
        assertFalse(route46.checkAnswer("   "));
        assertFalse(route46.checkAnswer("yes"));
        assertFalse(route46.checkAnswer("tru"));
        assertFalse(route46.checkAnswer(null));
    }

    @Test
    void refusesAQuestionNobodyCanGetRight() {
        assertThrows(IllegalArgumentException.class, () -> new Question("Is it raining?", "Maybe"));
        assertThrows(IllegalArgumentException.class, () -> new Question("Is it raining?", "t"));
        assertThrows(IllegalArgumentException.class, () -> new Question("Is it raining?", null));
    }

    @Test
    void printsAsAQuestion() {
        assertEquals("Q: Route 46 goes to Kawangware (True/False)", route46.toString());
    }
}
