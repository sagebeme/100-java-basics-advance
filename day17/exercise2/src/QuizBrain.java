import java.util.List;

public class QuizBrain {
    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private Question lastQuestion;

    public QuizBrain(List<Question> questions) {
        this.questions = questions;
    }

    public boolean stillHasQuestions() {
        return currentIndex < questions.size();
    }

    /**
     * Advances to the next question and returns it. Call submitAnswer with the learner's
     * response to score it against this question.
     */
    public Question nextQuestion() {
        lastQuestion = questions.get(currentIndex);
        currentIndex++;
        return lastQuestion;
    }

    public boolean submitAnswer(String answer) {
        boolean correct = lastQuestion.checkAnswer(answer);
        if (correct) {
            score++;
        }
        return correct;
    }

    public int getScore() {
        return score;
    }
}
