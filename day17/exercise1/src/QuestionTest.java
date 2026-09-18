import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {

    @Test
    void acceptsTheCorrectAnswerRegardlessOfCase() {
        Question q = new Question("Java is platform-independent", "True");
        assertTrue(q.checkAnswer("true"));
    }

    @Test
    void rejectsTheWrongAnswer() {
        Question q = new Question("Java is platform-independent", "True");
        assertFalse(q.checkAnswer("False"));
    }
}
