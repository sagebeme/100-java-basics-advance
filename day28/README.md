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

## ✅ Checklist
- [ ] Understand Timeline
- [ ] Can implement countdown
- [ ] Can format time display
- [ ] Can manage timer state
- [ ] Can handle cycles
- [ ] Completed Pomodoro Timer
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 28, you should be able to:
- Build timer applications
- Use Timeline for timing
- Manage application state
- Create productivity tools

**Ready for Day 29?** You'll build a Password Manager GUI app!

