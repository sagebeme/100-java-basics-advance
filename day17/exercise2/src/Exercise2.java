import java.util.ArrayList;
import java.util.List;

public class Exercise2 {

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Java is platform-independent", "True"));
        questions.add(new Question("Java supports multiple inheritance", "False"));

        QuizBrain quiz = new QuizBrain(questions);
        while (quiz.stillHasQuestions()) {
            Question current = quiz.nextQuestion();
            System.out.println(current.getText());
            quiz.submitAnswer("True");
        }
        System.out.println("Score: " + quiz.getScore() + "/" + questions.size());
    }
}
