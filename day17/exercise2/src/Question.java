public class Question {

    private final String text;
    private final boolean answer;

    public Question(String text, String answer) {
        if (answer == null || !(answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false"))) {
            throw new IllegalArgumentException("The answer must be True or False, not " + answer);
        }
        this.text = text;
        this.answer = answer.equalsIgnoreCase("true");
    }

    public String getText() {
        return text;
    }

    public boolean checkAnswer(String reply) {
        if (reply == null) {
            return false;
        }
        String tidy = reply.strip().toLowerCase();
        if (tidy.equals("true") || tidy.equals("t")) {
            return answer;
        }
        if (tidy.equals("false") || tidy.equals("f")) {
            return !answer;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Q: " + text + " (True/False)";
    }

    public static void main(String[] args) {
        Question route46 = new Question("Route 46 goes to Kawangware", "True");
        System.out.println(route46);
        System.out.println("\" t \" is right: " + route46.checkAnswer(" t "));
    }
}
