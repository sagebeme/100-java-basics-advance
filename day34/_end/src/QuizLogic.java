import java.util.List;

// Everything the quiz knows, with no JavaFX in sight, so it can be tested on its own.
public class QuizLogic {

    private final List<Question> questions;
    private int index = 0;
    private int score = 0;

    public QuizLogic(List<Question> questions) {
        if (questions.isEmpty()) throw new IllegalArgumentException("A quiz needs at least one question");
        this.questions = List.copyOf(questions);
    }

    public Question current() {
        return questions.get(index);
    }

    // Records the answer to the current question, moves on, and says whether it was right.
    public boolean answer(boolean guess) {
        if (isFinished()) throw new IllegalStateException("The quiz is over");
        boolean right = guess == current().answer();
        if (right) score++;
        index++;
        return right;
    }

    public boolean isFinished() {
        return index >= questions.size();
    }

    public int score() {
        return score;
    }

    public int total() {
        return questions.size();
    }

    // "Question 3 of 10"
    public String progress() {
        return "Question " + Math.min(index + 1, total()) + " of " + total();
    }

    public String verdict() {
        double share = (double) score / total();
        if (share == 1) return "Perfect score. Wewe ni bingwa!";
        if (share >= 0.7) return "Great work!";
        if (share >= 0.4) return "Not bad. Play again?";
        return "Tough round. Try again!";
    }
}
