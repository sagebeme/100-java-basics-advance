import java.util.List;

public class QuizLogic {

    private final List<Question> questions;
    private int index = 0;
    private int score = 0;

    public QuizLogic(List<Question> questions) {
        // TODO: refuse an empty list with IllegalArgumentException
        this.questions = List.copyOf(questions);
    }

    public Question current() {
        return questions.get(index);
    }

    // Records the answer to the current question, moves on, and says whether it was right.
    public boolean answer(boolean guess) {
        // TODO: throw IllegalStateException if the quiz is over
        return false;
    }

    public boolean isFinished() {
        // TODO
        return false;
    }

    public int score() {
        return score;
    }

    public int total() {
        return questions.size();
    }

    // "Question 3 of 10"
    public String progress() {
        // TODO
        return "";
    }

    public String verdict() {
        // TODO: all right "Perfect score. Wewe ni bingwa!", 70%+ "Great work!", 40%+ "Not bad. Play again?",
        //       otherwise "Tough round. Try again!"
        return "";
    }
}
