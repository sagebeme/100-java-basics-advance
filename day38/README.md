# Day 38 - Workout Tracking App with Database

## 📚 Learning Objectives
- Work with databases
- Use SQLite for local storage
- Perform CRUD operations
- Build data-driven applications
- Integrate database with GUI

## 🎯 Topics Covered
- SQLite database
- JDBC (Java Database Connectivity)
- SQL queries (SELECT, INSERT, UPDATE, DELETE)
- Database connections
- Data modeling
- CRUD operations

## 📝 Step-by-Step Instructions

### Step 1: SQLite Setup
Add SQLite dependency:

**pom.xml:**
```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.44.1.0</version>
</dependency>
```

### Step 2: Database Connection
Connect to SQLite:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:workouts.db";
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS workouts (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                date TEXT NOT NULL,
                exercise TEXT NOT NULL,
                duration INTEGER,
                calories INTEGER
            )
            """;
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### Step 3: CRUD Operations
Perform database operations:

```java
// INSERT
public void addWorkout(String date, String exercise, int duration, int calories) {
    String sql = "INSERT INTO workouts (date, exercise, duration, calories) VALUES (?, ?, ?, ?)";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, date);
        pstmt.setString(2, exercise);
        pstmt.setInt(3, duration);
        pstmt.setInt(4, calories);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// SELECT
public List<Workout> getAllWorkouts() {
    List<Workout> workouts = new ArrayList<>();
    String sql = "SELECT * FROM workouts";
    
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            Workout workout = new Workout(
                rs.getInt("id"),
                rs.getString("date"),
                rs.getString("exercise"),
                rs.getInt("duration"),
                rs.getInt("calories")
            );
            workouts.add(workout);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return workouts;
}
```

### Step 4: Database Integration
Use with application:

```java
public class WorkoutTracker {
    private DatabaseManager dbManager;
    
    public WorkoutTracker() {
        dbManager = new DatabaseManager();
        dbManager.createTable();
    }
    
    public void logWorkout(String exercise, int duration, int calories) {
        String date = LocalDate.now().toString();
        dbManager.addWorkout(date, exercise, duration, calories);
    }
}
```

## 💻 Exercises

### Exercise 1: Database Basics
Practice with:
- Creating tables
- Inserting data
- Querying data
- Updating data
- Deleting data

### Exercise 2: SQL Queries
Write queries for:
- Filtering data
- Sorting results
- Aggregating data
- Joining tables

### Exercise 3: Database Integration
Integrate databases with:
- GUI applications
- API endpoints
- Data processing
- Reports

## 🎮 Project: Workout Tracking App

### Requirements
Create workout tracking application:
1. Log workouts (exercise, duration, calories)
2. View workout history
3. Calculate statistics (total calories, time)
4. Filter by date range
5. Store in SQLite database
6. GUI for input and display

### Example Database Schema
```sql
CREATE TABLE workouts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date TEXT NOT NULL,
    exercise TEXT NOT NULL,
    duration INTEGER,
    calories INTEGER
);
```

### Example Output
```
Workout Tracker

Log Workout:
Exercise: [Running        ]
Duration (min): [30]
Calories: [300]
[Log Workout]

Workout History:
2024-12-01 - Running - 30 min - 300 cal
2024-12-02 - Cycling - 45 min - 400 cal

Total: 2 workouts, 75 minutes, 700 calories
```

### Starter Code
```java
public class WorkoutTracker extends Application {
    private DatabaseManager dbManager;
    
    @Override
    public void start(Stage stage) {
        dbManager = new DatabaseManager();
        dbManager.createTable();
        
        // Create GUI
        // Add event handlers
    }
}
```

## 📚 Resources

### Official Documentation
- [JDBC Tutorial - Oracle Docs](https://docs.oracle.com/javase/tutorial/jdbc/)
- [SQLite Documentation](https://www.sqlite.org/docs.html)
- [SQLite JDBC](https://github.com/xerial/sqlite-jdbc)

### Tutorials
- [SQLite Java - Baeldung](https://www.baeldung.com/java-sqlite)
- [JDBC Tutorial - GeeksforGeeks](https://www.geeksforgeeks.org/jdbc-tutorial/)
- [SQLite with Java](https://www.tutorialspoint.com/sqlite/sqlite_java.htm)

### Video Resources
- [SQLite Java Tutorial](https://www.youtube.com/results?search_query=sqlite+java+tutorial)
- [JDBC Tutorial](https://www.youtube.com/results?search_query=java+jdbc+tutorial)

### Practice Platforms
- [SQL Practice](https://www.sql-practice.com/)
- [Database Exercises](https://www.w3resource.com/sql-exercises/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| SQLite | Lightweight database | Local file database |
| JDBC | Database connectivity | `DriverManager.getConnection()` |
| SQL | Database queries | `SELECT * FROM table` |
| CRUD | Create, Read, Update, Delete | Database operations |
| PreparedStatement | Parameterized queries | `pstmt.setString(1, value)` |

## ✅ Checklist
- [ ] Understand databases
- [ ] Can use SQLite
- [ ] Can perform CRUD operations
- [ ] Can write SQL queries
- [ ] Can integrate with applications
- [ ] Completed Workout Tracker
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 38, you should be able to:
- Work with databases
- Perform CRUD operations
- Build data-driven apps
- Store and retrieve data

**Ready for Day 39?** You'll start the Flight Deal Finder capstone project!

