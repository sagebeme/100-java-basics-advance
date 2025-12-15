# Day 34 - API Practice: Creating a GUI Quiz App

## 📚 Learning Objectives
- Build GUI application with API integration
- Fetch quiz questions from API
- Display questions in GUI
- Handle user interactions
- Create engaging quiz experience

## 🎯 Topics Covered
- API integration in GUI apps
- Question fetching
- GUI updates
- Score tracking
- Progress indication
- API error handling

## 📝 Step-by-Step Instructions

### Step 1: API Integration
Fetch questions from API:

```java
public class QuizAPI {
    private static final String API_URL = "https://opentdb.com/api.php?amount=10&type=boolean";
    
    public List<Question> fetchQuestions() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL))
            .GET()
            .build();
        
        try {
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
            
            Gson gson = new Gson();
            QuizResponse quizResponse = gson.fromJson(response.body(), QuizResponse.class);
            return quizResponse.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
```

### Step 2: GUI Design
Create quiz interface:

```java
Label questionLabel = new Label();
Button trueButton = new Button("True");
Button falseButton = new Button("False");
Label scoreLabel = new Label("Score: 0/0");
ProgressBar progressBar = new ProgressBar();

VBox layout = new VBox(20);
layout.getChildren().addAll(questionLabel, trueButton, falseButton, scoreLabel, progressBar);
```

### Step 3: Question Display
Update GUI with questions:

```java
private void displayQuestion(Question question) {
    // Decode HTML entities
    String questionText = HtmlUtils.decode(question.getQuestion());
    questionLabel.setText(questionText);
    
    // Update progress
    double progress = (double) currentQuestion / totalQuestions;
    progressBar.setProgress(progress);
}
```

### Step 4: Answer Handling
Check answers:

```java
trueButton.setOnAction(e -> checkAnswer(true));
falseButton.setOnAction(e -> checkAnswer(false));

private void checkAnswer(boolean userAnswer) {
    Question current = questions.get(currentQuestionIndex);
    boolean correct = current.getCorrectAnswer().equalsIgnoreCase(
        userAnswer ? "True" : "False");
    
    if (correct) {
        score++;
        showFeedback("Correct!", Color.GREEN);
    } else {
        showFeedback("Wrong!", Color.RED);
    }
    
    updateScore();
    nextQuestion();
}
```

## 💻 Exercises

### Exercise 1: API Integration
Practice with:
- Fetching data from APIs
- Parsing API responses
- Handling API errors
- Caching API data

### Exercise 2: GUI Updates
Create dynamic GUIs:
- Update labels
- Change colors
- Show progress
- Display feedback

### Exercise 3: Quiz Features
Add features:
- Multiple choice questions
- Timer
- Categories
- Difficulty levels

## 🎮 Project: GUI Quiz App

### Requirements
Create a quiz application:
1. Fetch questions from Open Trivia API
2. Display questions in GUI
3. True/False buttons
4. Score tracking
5. Progress bar
6. Next question button
7. Final score display

### Example Output
```
Quiz App

Question 1/10
What is the capital of France?

[True] [False]

Score: 3/5
[Progress: ████████░░ 80%]
```

### Starter Code
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuizApp extends Application {
    private List<Question> questions;
    private int currentQuestion = 0;
    private int score = 0;
    
    @Override
    public void start(Stage stage) {
        // Fetch questions from API
        // Create GUI
        // Set up event handlers
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

## 📚 Resources

### Official Documentation
- [HTTP Client - Java API](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html)
- [JavaFX Controls - JavaFX Docs](https://openjfx.io/javadoc/11/javafx.controls/)

### Tutorials
- [Open Trivia API](https://opentdb.com/api_config.php)
- [JavaFX API Integration](https://code.makery.ch/library/javafx-tutorial/)

### Video Resources
- [Quiz App Tutorial](https://www.youtube.com/results?search_query=java+quiz+app+tutorial)
- [API Integration JavaFX](https://www.youtube.com/results?search_query=javafx+api+integration)

### Practice Platforms
- [Open Trivia DB](https://opentdb.com/)
- [Public APIs](https://github.com/public-apis/public-apis)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| API Integration | Connect to web services | Fetch questions from API |
| GUI Updates | Change UI dynamically | Update labels, progress |
| Async Operations | Non-blocking calls | Fetch in background |
| Error Handling | Handle API failures | Try-catch, fallbacks |
| Progress Tracking | Show completion | ProgressBar |

## ✅ Checklist
- [ ] Can integrate APIs in GUI
- [ ] Can fetch and parse data
- [ ] Can update GUI dynamically
- [ ] Can handle API errors
- [ ] Can track progress
- [ ] Completed Quiz App
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 34, you should be able to:
- Build API-integrated GUIs
- Fetch data dynamically
- Update interfaces in real-time
- Create interactive applications

**Ready for Day 35?** You'll learn about environment variables and configuration!



