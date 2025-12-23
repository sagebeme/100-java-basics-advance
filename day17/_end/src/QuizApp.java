import java.util.ArrayList;
import java.util.List;

public class QuizApp {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Java is a compiled language", "True"));
        questions.add(new Question("Java supports multiple inheritance", "False"));
        questions.add(new Question("Java is platform-independent", "True"));
        questions.add(new Question("All Java classes inherit from Object", "True"));
        questions.add(new Question("Java uses garbage collection", "True"));
        
        QuizBrain quiz = new QuizBrain(questions);
        
        System.out.println("Welcome to the Java Quiz!");
        
        while (quiz.stillHasQuestions()) {
            quiz.nextQuestion();
        }
        
        quiz.showFinalScore();
    }
}

