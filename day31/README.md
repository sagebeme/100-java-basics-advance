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

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **MVC:** Re-read **Code Structure** — Model (data), View (FXML/UI), Controller (logic) — see **Code Structure**
- **JavaFX + FXML:** Load FXML with `FXMLLoader`; controller in FXML or set in loader — see **Key Concepts**
- **JSON:** Load/save flashcards: Gson or Jackson; structure as in **Example Data Structure** above
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for full app flow

**Related days:** Day 27 (JavaFX); Day 30 (JSON, Gson); Day 24 (file I/O). **Quick reference:** Multi-file: compile all or use IDE/Maven · JavaFX: see How to Run

## 📂 Code Examples

This day includes starter and completed code examples:
- **`_start/`** - Starting code with TODO comments and learning notes
- **`_end/`** - Completed solutions with detailed explanations

Check these folders to see the progression from start to finish and learn from the learning curve notes!

## 💻 How to Run Java Files

### Prerequisites
Make sure you have Java installed on your system:
- **Check installation**: Open terminal/command prompt and run `java -version`
- **If not installed**: Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

### Running Java Files

#### **Windows**

1. **Open Command Prompt or PowerShell**
   - Press `Win + R`, type `cmd` or `powershell`, press Enter

2. **Navigate to the project directory**
   ```cmd
   cd "path\to\100-java-basics-advance\day31\_end\src"
   ```

3. **Compile** (if multiple files: `javac *.java`; if Maven: use IDE or `mvn compile exec:java`)
   ```cmd
   javac FlashCardApp.java
   ```

4. **Run the compiled program**
   ```cmd
   java FlashCardApp
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day31/_end/src
   ```

3. **Compile** (if multiple files: `javac *.java`)
   ```bash
   javac FlashCardApp.java
   ```

4. **Run the compiled program**
   ```bash
   java FlashCardApp
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'FlashCardApp.main()'"

**VS Code:**
1. Install "Extension Pack for Java"
2. Open the Java file
3. Click "Run" button above `main` method or press `F5`

**Eclipse:**
1. Import project into Eclipse
2. Right-click on the Java file
3. Select "Run As" → "Java Application"

### Troubleshooting

- **"javac: command not found"** - Java is not installed or not in PATH
- **"Error: Could not find or load main class"** - Make sure you're in the correct directory and class name matches filename
- **JavaFX / FXML**: If using Java 11+, add VM options for JavaFX; multi-class projects are easier to run from an IDE

## 🚀 Next Steps
After completing Day 31, you should be able to:
- Build GUI applications
- Work with file I/O
- Use JSON for data storage
- Implement MVC pattern
- Create user-friendly interfaces







