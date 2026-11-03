import java.util.List;

public class QuizBrain {

    private final List<Question> questions;
    private int asked;
    private int answered;
    private int score;

    public QuizBrain(List<Question> questions) {
        if (questions.isEmpty()) {
            throw new IllegalArgumentException("A quiz needs at least one question");
        }
        this.questions = List.copyOf(questions);
    }

    public boolean hasMore() {
        return asked < questions.size();
    }

    public Question next() {
        if (!hasMore()) {
            throw new IllegalStateException("No questions left");
        }
        asked++;
        return questions.get(asked - 1);
    }

    public boolean answer(String reply) {
        if (answered == asked) {
            throw new IllegalStateException(asked == 0 ? "Ask a question first" : "That question is already answered");
        }
        answered++;
        boolean right = questions.get(asked - 1).checkAnswer(reply);
        if (right) {
            score++;
        }
        return right;
    }

    public int score() {
        return score;
    }

    public String scoreLine() {
        if (answered == 0) {
            return "0/0";
        }
        return score + "/" + answered + " (" + Math.round(100.0 * score / answered) + "%)";
    }

    public static void main(String[] args) {
        QuizBrain quiz = new QuizBrain(List.of(
                new Question("Route 46 goes to Kawangware", "True"),
                new Question("Nairobi is on the coast", "False"),
                new Question("Ugali is made from maize flour", "True")));
        for (String reply : List.of("t", "false", "t")) {
            System.out.println(quiz.next());
            quiz.answer(reply);
            System.out.println("  " + reply + " -> " + quiz.scoreLine());
        }
    }
}
