import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizLogicTest {

    private static String fixture(String name) throws IOException {
        try (InputStream in = QuizLogicTest.class.getResourceAsStream("/" + name)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    @Test
    void decodesHtmlEntities() {
        assertEquals("A cheetah can't roar.", Html.decode("A cheetah can&#039;t roar."));
        assertEquals("\"Nairobi\" & Mombasa", Html.decode("&quot;Nairobi&quot; &amp; Mombasa"));
        assertEquals("café", Html.decode("caf&#xe9;"));
        assertEquals("&unknown; stays", Html.decode("&unknown; stays"));
    }

    @Test
    void parsesTriviaQuestionsAndDecodesThem() throws IOException {
        List<Question> questions = TriviaSource.parse(fixture("opentdb-sample.json"));
        assertEquals(3, questions.size());
        assertEquals("The capital of Kenya is \"Nairobi\".", questions.get(0).text());
        assertTrue(questions.get(0).answer());
        assertFalse(questions.get(2).answer());
        assertEquals("Science & Nature", questions.get(1).category());
    }

    @Test
    void aBadResponseCodeIsAnError() {
        assertThrows(IllegalStateException.class, () -> TriviaSource.parse("{\"response_code\":1,\"results\":[]}"));
    }

    @Test
    void theBundledKenyaQuestionsLoadOffline() {
        assertEquals(12, TriviaSource.bundled().size());
    }

    @Test
    void scoresRightAnswersAndMovesOn() {
        QuizLogic quiz = new QuizLogic(List.of(
                new Question("Q1", true, "Kenya"), new Question("Q2", false, "Kenya")));
        assertEquals("Question 1 of 2", quiz.progress());
        assertTrue(quiz.answer(true));
        assertEquals("Question 2 of 2", quiz.progress());
        assertFalse(quiz.answer(true));
        assertTrue(quiz.isFinished());
        assertEquals(1, quiz.score());
        assertThrows(IllegalStateException.class, () -> quiz.answer(true));
    }

    @Test
    void theVerdictDependsOnTheShareRight() {
        QuizLogic perfect = new QuizLogic(List.of(new Question("Q", true, "x")));
        perfect.answer(true);
        assertEquals("Perfect score. Wewe ni bingwa!", perfect.verdict());
        QuizLogic none = new QuizLogic(List.of(new Question("Q", true, "x")));
        none.answer(false);
        assertEquals("Tough round. Try again!", none.verdict());
    }

    @Test
    void anEmptyQuizIsRefused() {
        assertThrows(IllegalArgumentException.class, () -> new QuizLogic(List.of()));
    }
}
