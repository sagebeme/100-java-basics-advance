public class Question {

    // TODO: private fields for the text and the right answer

    public Question(String text, String answer) {
        // TODO: refuse an answer that isn't "True" or "False" (any case), then store both
    }

    public String getText() {
        // TODO
        return "";
    }

    public boolean checkAnswer(String reply) {
        // TODO: true, TRUE, " t " all mean True; f means False; anything else is wrong
        return false;
    }

    @Override
    public String toString() {
        // TODO: "Q: Route 46 goes to Kawangware (True/False)"
        return "";
    }

    public static void main(String[] args) {
        // TODO: the Route 46 question (answer True). Print it, and whether " t " is right.
    }
}
