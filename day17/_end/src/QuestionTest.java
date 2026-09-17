import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {

    @Test
    void acceptsTheCorrectAnswer() {
        Question question = new Question("Java is a compiled language", "True");
        assertTrue(question.checkAnswer("True"));
    }

    @Test
    void acceptsTheCorrectAnswerRegardlessOfCase() {
        Question question = new Question("Java is a compiled language", "True");
        assertTrue(question.checkAnswer("true"));
    }

    @Test
    void rejectsTheWrongAnswer() {
        Question question = new Question("Java is a compiled language", "True");
        assertFalse(question.checkAnswer("False"));
    }

    @Test
    void exposesItsOwnText() {
        Question question = new Question("Java supports multiple inheritance", "False");
        assertEquals("Java supports multiple inheritance", question.getText());
    }
}
