# Day 17 - Quiz Project & OOP Benefits

## 📚 Learning Objectives
- Build a complete quiz application using OOP
- Understand OOP benefits (reusability, maintainability)
- Practice with multiple classes
- Learn about data modeling
- Create clean, organized code

## 🎯 Topics Covered
- Multiple class design
- Class relationships
- Data modeling
- Code organization
- OOP best practices
- Project structure

## 📝 Step-by-Step Instructions

### Step 1: Design Your Classes
Plan your class structure:

```java
// Question.java - Represents a single question
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
}

// QuizBrain.java - Manages quiz logic
public class QuizBrain {
    private List<Question> questions;
    private int currentQuestion;
    private int score;
    
    public QuizBrain(List<Question> questions) {
        this.questions = questions;
        this.currentQuestion = 0;
        this.score = 0;
    }
    
    public boolean stillHasQuestions() {
        return currentQuestion < questions.size();
    }
    
    public void nextQuestion() {
        // Display question and get answer
    }
}
```

### Step 2: Create Data Model
Organize question data:

```java
// QuestionData.java or use JSON/CSV
public class QuestionData {
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Java is a compiled language", "True"));
        questions.add(new Question("Java supports multiple inheritance", "False"));
        // Add more questions
        return questions;
    }
}
```

### Step 3: Build Main Application
Tie everything together:

```java
public class QuizApp {
    public static void main(String[] args) {
        List<Question> questions = QuestionData.getQuestions();
        QuizBrain quiz = new QuizBrain(questions);
        
        while (quiz.stillHasQuestions()) {
            quiz.nextQuestion();
        }
        
        quiz.showFinalScore();
    }
}
```

## 💻 Exercises

### Exercise 1: Question Class
Create a Question class with:
- Question text
- Answer
- Check answer method
- Display method

### Exercise 2: Quiz Manager
Create a QuizBrain class with:
- Question list management
- Score tracking
- Current question tracking
- Next question method

### Exercise 3: Data Management
Create systems for:
- Loading questions from file
- Different question types
- Question categories
- Difficulty levels

## 🎮 Project: Quiz Application

### Requirements
Create a complete quiz application:
1. Create Question class
2. Create QuizBrain class
3. Load questions from data source
4. Display questions one by one
5. Get user answers
6. Track score
7. Display final results
8. Support multiple question types

### Example Output
```
Q.1: Java is a compiled language. (True/False): True
You got it right!
Your current score is: 1/1

Q.2: Java supports multiple inheritance. (True/False): False
You got it right!
Your current score is: 2/2

Q.3: What is the extension of Java files? .java
You got it right!
Your current score is: 3/3

You've completed the quiz
Your final score was: 3/3
```

### Starter Code Structure
```
day17/
├── README.md
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── learning/
│                   ├── Main.java
│                   ├── Question.java
│                   ├── QuizBrain.java
│                   └── QuestionData.java
```

## 📚 Resources

### Official Documentation
- [Java Classes - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/concepts/class.html)
- [Collections - Oracle Docs](https://docs.oracle.com/javase/tutorial/collections/)

### Tutorials
- [Java OOP Projects](https://www.geeksforgeeks.org/java-projects/)
- [Class Design Best Practices](https://www.baeldung.com/java-class-design)

### Video Resources
- [Java Quiz App Tutorial](https://www.youtube.com/results?search_query=java+quiz+application+tutorial)
- [OOP Project Building](https://www.youtube.com/results?search_query=java+oop+project)

### Practice Platforms
- [OOP Design Exercises](https://www.w3resource.com/java-exercises/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Class Design | Planning class structure | Question, QuizBrain classes |
| Data Modeling | Organizing data | Question data structure |
| Code Organization | Structuring project | Separate classes for concerns |
| Reusability | Using classes multiple times | Question class reused |
| Maintainability | Easy to update | Changes isolated to classes |

## ✅ Checklist
- [ ] Understand OOP benefits
- [ ] Can design multiple classes
- [ ] Can model data with classes
- [ ] Can organize code properly
- [ ] Completed Quiz Application
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 17, you should understand:
- OOP design principles
- Class relationships
- Code organization
- Building multi-class applications

**Ready for Day 18?** You'll learn about JavaFX and GUI programming!



