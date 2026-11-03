import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizBrainTest {

    private final Question q1 = new Question("Route 46 goes to Kawangware", "True");
    private final Question q2 = new Question("Nairobi is on the coast", "False");
    private final Question q3 = new Question("Ugali is made from maize flour", "True");

    private QuizBrain quiz() {
        return new QuizBrain(List.of(q1, q2, q3));
    }

    @Test
    void asksEveryQuestionInOrderThenStops() {
        QuizBrain quiz = quiz();
        assertTrue(quiz.hasMore());
        assertSame(q1, quiz.next());
        assertSame(q2, quiz.next());
        assertSame(q3, quiz.next());
        assertFalse(quiz.hasMore());
        assertThrows(IllegalStateException.class, quiz::next);
    }

    @Test
    void countsOnlyRightAnswers() {
        QuizBrain quiz = quiz();
        quiz.next();
        assertTrue(quiz.answer("t"));
        quiz.next();
        assertFalse(quiz.answer("true"));
        quiz.next();
        assertTrue(quiz.answer(" TRUE "));
        assertEquals(2, quiz.score());
    }

    @Test
    void cannotAnswerBeforeTheFirstQuestion() {
        assertThrows(IllegalStateException.class, () -> quiz().answer("t"));
    }

    @Test
    void cannotAnswerTheSameQuestionTwice() {
        QuizBrain quiz = quiz();
        quiz.next();
        quiz.answer("t");
        assertThrows(IllegalStateException.class, () -> quiz.answer("t"));
        assertEquals(1, quiz.score());
    }

    @Test
    void theScoreLineCountsAnsweredQuestions() {
        QuizBrain quiz = quiz();
        assertEquals("0/0", quiz.scoreLine());
        quiz.next();
        quiz.answer("t");
        assertEquals("1/1 (100%)", quiz.scoreLine());
        quiz.next();
        quiz.answer("t");
        assertEquals("1/2 (50%)", quiz.scoreLine());
        quiz.next();
        quiz.answer("t");
        assertEquals("2/3 (67%)", quiz.scoreLine());
    }

    @Test
    void refusesAnEmptyQuiz() {
        assertThrows(IllegalArgumentException.class, () -> new QuizBrain(List.of()));
    }

    @Test
    void changingTheOriginalListDoesNotChangeTheQuiz() {
        List<Question> questions = new ArrayList<>(List.of(q1));
        QuizBrain quiz = new QuizBrain(questions);
        questions.add(q2);
        quiz.next();
        assertFalse(quiz.hasMore());
    }
}
