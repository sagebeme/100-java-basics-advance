# Day 28 - JavaFX: Pomodoro Timer Application

## 📚 Learning Objectives
- Build a complete timer application
- Work with JavaFX Timeline for timing
- Implement countdown functionality
- Add visual feedback
- Create a practical productivity tool

## 🎯 Topics Covered
- Timeline for timers
- Countdown logic
- UI updates
- State management
- Visual feedback
- Application lifecycle

## 📝 Step-by-Step Instructions

### Step 1: Timer Logic
Implement countdown:

```java
private int timeLeft = 25 * 60; // 25 minutes in seconds
private boolean isRunning = false;

private void updateTimer() {
    if (isRunning && timeLeft > 0) {
        timeLeft--;
        updateDisplay();
        
        if (timeLeft == 0) {
            timerComplete();
        }
    }
}
```

### Step 2: Timeline for Updates
Use Timeline for regular updates:

```java
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
    updateTimer();
}));
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.play();
```

### Step 3: Format Time Display
Format seconds to MM:SS:

```java
private String formatTime(int seconds) {
    int minutes = seconds / 60;
    int secs = seconds % 60;
    return String.format("%02d:%02d", minutes, secs);
}
```

### Step 4: Pomodoro Cycles
Manage work/break cycles:

```java
private int workMinutes = 25;
private int shortBreak = 5;
private int longBreak = 15;
private int sessionsCompleted = 0;

private void startWork() {
    timeLeft = workMinutes * 60;
    isRunning = true;
}

private void startBreak() {
    if (sessionsCompleted % 4 == 0) {
        timeLeft = longBreak * 60;
    } else {
        timeLeft = shortBreak * 60;
    }
    isRunning = true;
}
```

## 💻 Exercises

### Exercise 1: Basic Timer
Create a timer that:
- Counts down from set time
- Displays time remaining
- Alerts when complete
- Can be paused/resumed

### Exercise 2: Multiple Timers
Create system with:
- Work timer
- Break timer
- Long break timer
- Cycle management

### Exercise 3: Visual Feedback
Add visual elements:
- Progress bar
- Color changes
- Sound alerts
- Notifications

## 🎮 Project: Pomodoro Timer

### Requirements
Create a Pomodoro timer application:
1. 25-minute work timer
2. 5-minute short break
3. 15-minute long break (every 4 sessions)
4. Start/pause/reset buttons
5. Visual countdown display
6. Session counter
7. Alert when timer completes

### Example Output
```
🍅 Pomodoro Timer

Work Time: 25:00
[Start] [Reset]

Sessions Completed: 0

[Progress bar showing time remaining]
```

### Starter Code
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class PomodoroTimer extends Application {
    private int timeLeft = 25 * 60;
    private boolean isRunning = false;
    private Timeline timeline;
    
    @Override
    public void start(Stage stage) {
        // Create UI
        // Set up timer
        // Add event handlers
    }
    
    private void updateTimer() {
        // Countdown logic
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

## 📚 Resources

### Official Documentation
- [JavaFX Timeline - JavaFX Docs](https://openjfx.io/javadoc/11/javafx.controls/javafx/animation/Timeline.html)
- [Animation - Oracle Docs](https://docs.oracle.com/javase/8/javafx/api/javafx/animation/package-summary.html)

### Tutorials
- [JavaFX Timer Tutorial](https://code.makery.ch/library/javafx-tutorial/)
- [Pomodoro Technique](https://en.wikipedia.org/wiki/Pomodoro_Technique)
- [JavaFX Animation](https://jenkov.com/tutorials/javafx/animation.html)

### Video Resources
- [Pomodoro Timer Tutorial](https://www.youtube.com/results?search_query=javafx+pomodoro+timer)
- [JavaFX Timer Application](https://www.youtube.com/results?search_query=javafx+timer+tutorial)

### Practice Platforms
- [JavaFX Projects](https://github.com/topics/javafx)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Timeline | Animation/timer | `new Timeline(KeyFrame)` |
| Countdown | Decrement timer | `timeLeft--` |
| State Management | Track timer state | `isRunning`, `timeLeft` |
| Formatting | Display time | `String.format("%02d:%02d")` |
| Cycle Management | Work/break cycles | Track sessions |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Timeline:** Re-read **Step 1** — `Timeline` with `KeyFrame(Duration.seconds(1), e -> update())` — see **Step 2**
- **Countdown:** Store `timeLeft`; in KeyFrame decrement and update label; stop when 0 — see **Step 2**
- **Format:** `String.format("%02d:%02d", minutes, seconds)` for MM:SS — see **Step 3**
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for cycle logic

**Related days:** Day 20 (Timeline for animation); Day 27 (JavaFX controls). **JavaFX:** See Troubleshooting.

**Quick reference:** `timeline.setCycleCount(Animation.INDEFINITE)` for repeating · `timeline.play()` / `pause()` / `stop()`

## ✅ Checklist
- [ ] Understand Timeline
- [ ] Can implement countdown
- [ ] Can format time display
- [ ] Can manage timer state
- [ ] Can handle cycles
- [ ] Completed Pomodoro Timer
- [ ] Committed code to Git

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
   cd "path\to\100-java-basics-advance\day28\_end\src"
   ```

3. **Compile the Java file**
   ```cmd
   javac PomodoroTimer.java
   ```

4. **Run the compiled program**
   ```cmd
   java PomodoroTimer
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day28/_end/src
   ```

3. **Compile the Java file**
   ```bash
   javac PomodoroTimer.java
   ```

4. **Run the compiled program**
   ```bash
   java PomodoroTimer
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'PomodoroTimer.main()'"

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
- **JavaFX apps**: If using Java 11+, add VM options: `--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml`

## 🚀 Next Steps
After completing Day 28, you should be able to:
- Build timer applications
- Use Timeline for timing
- Manage application state
- Create productivity tools

**Ready for Day 29?** You'll build a Password Manager GUI app!







