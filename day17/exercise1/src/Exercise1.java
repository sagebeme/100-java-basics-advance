public class Exercise1 {

    public static void main(String[] args) {
        Question q = new Question("Java is platform-independent", "True");
        q.display();
        System.out.println("Correct: " + q.checkAnswer("true"));
    }
}
