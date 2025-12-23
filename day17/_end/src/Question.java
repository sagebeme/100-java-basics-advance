public class Question {
    private String text;
    private String answer;
    
    public Question(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }
    
    public boolean checkAnswer(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer);
    }
    
    public String getText() {
        return text;
    }
}

