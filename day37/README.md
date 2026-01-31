# Day 37 - Habit Tracking Application

## 📚 Learning Objectives
- Build habit tracking system
- Work with date tracking
- Create data visualization
- Implement persistence
- Build productivity tools

## 🎯 Topics Covered
- Habit tracking logic
- Date management
- Data persistence
- Visualization basics
- Progress tracking
- Statistics

## 📝 Step-by-Step Instructions

### Step 1: Habit Data Model
Design habit structure:

```java
public class Habit {
    private String name;
    private Map<LocalDate, Boolean> completions;
    
    public Habit(String name) {
        this.name = name;
        this.completions = new HashMap<>();
    }
    
    public void markComplete(LocalDate date) {
        completions.put(date, true);
    }
    
    public boolean isComplete(LocalDate date) {
        return completions.getOrDefault(date, false);
    }
    
    public int getStreak() {
        // Calculate current streak
    }
}
```

### Step 2: Persistence
Save habits to file:

```java
public class HabitManager {
    private List<Habit> habits;
    private Gson gson;
    
    public void saveHabits() {
        try {
            String json = gson.toJson(habits);
            FileWriter writer = new FileWriter("habits.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadHabits() {
        try {
            FileReader reader = new FileReader("habits.json");
            Type listType = new TypeToken<List<Habit>>(){}.getType();
            habits = gson.fromJson(reader, listType);
            reader.close();
        } catch (FileNotFoundException e) {
            habits = new ArrayList<>();
        }
    }
}
```

### Step 3: Statistics
Calculate habit statistics:

```java
public class HabitStats {
    public int getTotalDays(Habit habit) {
        return habit.getCompletions().size();
    }
    
    public int getCurrentStreak(Habit habit) {
        // Calculate streak logic
    }
    
    public double getCompletionRate(Habit habit) {
        // Calculate percentage
    }
}
```

## 💻 Exercises

### Exercise 1: Habit Tracking
Create systems for:
- Adding habits
- Marking completion
- Tracking streaks
- Viewing history

### Exercise 2: Statistics
Calculate:
- Completion rates
- Current streaks
- Longest streaks
- Weekly/monthly stats

### Exercise 3: Visualization
Create visual:
- Progress bars
- Calendar view
- Charts
- Reports

## 🎮 Project: Habit Tracker

### Requirements
Create habit tracking application:
1. Add/remove habits
2. Mark habits as complete
3. Track daily completions
4. Calculate streaks
5. Display statistics
6. Save to file
7. Calendar view of completions

### Example Output
```
Habit Tracker

Habits:
✓ Exercise (Streak: 5 days)
✓ Reading (Streak: 3 days)
✗ Meditation (Streak: 0 days)

Today's Progress: 2/3 habits completed

[Calendar view showing completion dates]
```

### Starter Code
```java
public class HabitTracker {
    private List<Habit> habits;
    private HabitManager manager;
    
    public void addHabit(String name) {
        habits.add(new Habit(name));
        manager.saveHabits();
    }
    
    public void markComplete(String habitName) {
        // Mark today as complete
    }
}
```

## 📚 Resources

### Official Documentation
- [LocalDate - Java API](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)
- [Map Interface - Oracle Docs](https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html)

### Tutorials
- [Habit Tracking Apps](https://www.geeksforgeeks.org/)
- [Date Calculations](https://www.baeldung.com/java-date-calculations)

### Video Resources
- [Habit Tracker Tutorial](https://www.youtube.com/results?search_query=habit+tracker+java)
- [Productivity Apps](https://www.youtube.com/results?search_query=java+productivity+app)

### Practice Platforms
- [Productivity Projects](https://github.com/karan/Projects#productivity)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Habit Tracking | Monitor activities | Daily completions |
| Streak | Consecutive days | Current streak count |
| Persistence | Save data | JSON file storage |
| Statistics | Analyze data | Completion rates |
| Visualization | Display data | Calendar, charts |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Habit data:** Re-read **Step 1** — model: name, dates completed; store in JSON or file — see **Step 2**
- **Streak:** Count consecutive days from today backward — see **Step 2**
- **Persistence:** Day 24 (file I/O) or Day 30 (Gson JSON) — see **Step 3**
- **Main day README** → **Step 1 & 2** for data model; **Key Concepts** for persistence

**Related days:** Day 30 (JSON); Day 24 (file I/O); Day 34 (GUI optional). **Quick reference:** LocalDate for dates; Gson for save/load

## ✅ Checklist
- [ ] Can track habits
- [ ] Can calculate streaks
- [ ] Can persist data
- [ ] Can display statistics
- [ ] Can visualize progress
- [ ] Completed Habit Tracker
- [ ] Committed code to Git

## 💻 How to Run Java Files

### Prerequisites
Make sure you have Java installed on your system:
- **Check installation**: Open terminal/command prompt and run `java -version`
- **If not installed**: Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

### Running Java Files

#### **Windows**

1. **Open Command Prompt or PowerShell**
   - Press `Win + R`, type `cmd` or `powershell`, press Enter

2. **Navigate to your project directory**
   ```cmd
   cd "path\to\100-java-basics-advance\day37\src"
   ```

3. **Compile** (if multiple files: `javac *.java`)
   ```cmd
   javac HabitTracker.java
   ```

4. **Run**
   ```cmd
   java HabitTracker
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to your project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day37/src
   ```

3. **Compile and run**
   ```bash
   javac HabitTracker.java
   java HabitTracker
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA / Eclipse / VS Code:**
1. Open the project
2. Right-click on the main class
3. Select "Run 'HabitTracker.main()'"

### Troubleshooting

- **"javac: command not found"** - Java is not installed or not in PATH
- **"Error: Could not find or load main class"** - Ensure you're in the correct directory and class name matches filename

## 🚀 Next Steps
After completing Day 37, you should be able to:
- Build tracking applications
- Work with date-based data
- Calculate statistics
- Create productivity tools

**Ready for Day 38?** You'll build a Workout Tracking App with database!







