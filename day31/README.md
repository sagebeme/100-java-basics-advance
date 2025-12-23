# Day 31 - Capstone Project: Flash Card App

## Learning Objectives
- Build a complete GUI application with JavaFX
- Implement file I/O for data persistence
- Use JSON for data storage
- Create a user-friendly interface
- Practice OOP design patterns

## Topics Covered
- JavaFX Scene Builder
- File I/O operations
- JSON parsing (Jackson library)
- Event handling
- MVC pattern

## Project: Flash Card Application

Build a flashcard application that helps users learn new words or concepts.

### Requirements
- Display flashcards with questions
- Show answers on click
- Mark cards as known/unknown
- Save progress to JSON file
- Load existing flashcards
- Add new flashcards
- Remove flashcards

### Features
- **Study Mode**: Flip cards to see answers
- **Progress Tracking**: Track which cards you know
- **Data Persistence**: Save flashcards to JSON
- **Custom Cards**: Add your own flashcards

## Code Structure
```
day31/
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── learning/
│       │           ├── Main.java
│       │           ├── FlashCard.java
│       │           ├── FlashCardController.java
│       │           └── FlashCardService.java
│       └── resources/
│           └── flashcard-view.fxml
└── pom.xml
```

## Key Concepts
- **JavaFX**: GUI framework
- **FXML**: XML-based UI definition
- **JSON**: Data serialization format
- **File I/O**: Reading and writing files
- **MVC Pattern**: Model-View-Controller architecture

## Example Data Structure
```json
{
  "flashcards": [
    {
      "question": "What is Java?",
      "answer": "A programming language",
      "known": false
    }
  ]
}
```

## 📂 Code Examples

This day includes starter and completed code examples:
- **`_start/`** - Starting code with TODO comments and learning notes
- **`_end/`** - Completed solutions with detailed explanations

Check these folders to see the progression from start to finish and learn from the learning curve notes!

## Next Steps
After completing Day 31, you should be able to:
- Build GUI applications
- Work with file I/O
- Use JSON for data storage
- Implement MVC pattern
- Create user-friendly interfaces







