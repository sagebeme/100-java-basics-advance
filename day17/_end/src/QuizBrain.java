import java.util.List;
import java.util.Scanner;

public class QuizBrain {
    private List<Question> questions;
    private int currentQuestion;
    private int score;
    private Scanner scanner;
    
    public QuizBrain(List<Question> questions) {
        this.questions = questions;
        this.currentQuestion = 0;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }
    
    public boolean stillHasQuestions() {
        return currentQuestion < questions.size();
    }
    
    public void nextQuestion() {
        Question question = questions.get(currentQuestion);
        System.out.println("\nQ" + (currentQuestion + 1) + ". " + question.getText());
        System.out.print("Your answer (True/False): ");
        String userAnswer = scanner.nextLine();
        
        if (question.checkAnswer(userAnswer)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Wrong!");
        }
        
        currentQuestion++;
    }
    
    public void showFinalScore() {
        System.out.println("\n=== Quiz Complete ===");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }
}

