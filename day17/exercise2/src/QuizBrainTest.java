import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizBrainTest {

    private QuizBrain freshQuiz() {
        return new QuizBrain(List.of(
                new Question("2 + 2 = 4", "True"),
                new Question("The sky is green", "False")
        ));
    }

    @Test
    void hasQuestionsUntilTheListIsExhausted() {
        QuizBrain quiz = freshQuiz();
        assertTrue(quiz.stillHasQuestions());
        quiz.nextQuestion();
        quiz.nextQuestion();
        assertFalse(quiz.stillHasQuestions());
    }

    @Test
    void aCorrectAnswerIncreasesTheScore() {
        QuizBrain quiz = freshQuiz();
        quiz.nextQuestion();
        assertTrue(quiz.submitAnswer("True"));
        assertEquals(1, quiz.getScore());
    }

    @Test
    void aWrongAnswerDoesNotIncreaseTheScore() {
        QuizBrain quiz = freshQuiz();
        quiz.nextQuestion();
        assertFalse(quiz.submitAnswer("False"));
        assertEquals(0, quiz.getScore());
    }
}
