import java.util.List;

public class QuizBrain {

    // TODO: private fields: the questions, how many have been asked, how many answered, the score

    public QuizBrain(List<Question> questions) {
        // TODO: refuse an empty list; keep a copy
    }

    public boolean hasMore() {
        // TODO
        return false;
    }

    public Question next() {
        // TODO: none left? throw IllegalStateException
        return null;
    }

    public boolean answer(String reply) {
        // TODO: nothing asked, or already answered? throw IllegalStateException.
        // Otherwise mark it, count it, and say whether it was right.
        return false;
    }

    public int score() {
        // TODO
        return -1;
    }

    public String scoreLine() {
        // TODO: "2/3 (67%)", or "0/0" before any answer
        return "";
    }

    public static void main(String[] args) {
        // TODO: three questions, replies t, false, t. Print the score line after each.
    }
}
