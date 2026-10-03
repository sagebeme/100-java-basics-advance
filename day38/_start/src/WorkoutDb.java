import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDb implements AutoCloseable {

    private final Connection db;

    public WorkoutDb(String jdbcUrl) throws SQLException {
        this.db = DriverManager.getConnection(jdbcUrl);
    }

    public void createTable() throws SQLException {
        // TODO: CREATE TABLE IF NOT EXISTS workouts (id BIGINT AUTO_INCREMENT PRIMARY KEY, workout_date DATE NOT NULL,
        //       activity VARCHAR(40) NOT NULL, minutes INT NOT NULL CHECK (minutes > 0), kcal INT NOT NULL)
    }

    // Saves the workout and returns it with the id the database gave it.
    public Workout add(Workout w) throws SQLException {
        // TODO: INSERT with ? placeholders and Statement.RETURN_GENERATED_KEYS
        return w;
    }

    public List<Workout> forDay(LocalDate day) throws SQLException {
        // TODO: SELECT ... WHERE workout_date = ? ORDER BY id
        return new ArrayList<>();
    }

    // One row per activity between the two days (both included), sorted by activity name.
    public List<ActivityTotal> totalsBetween(LocalDate from, LocalDate to) throws SQLException {
        // TODO: SUM, WHERE workout_date BETWEEN ? AND ?, GROUP BY activity, ORDER BY activity
        return new ArrayList<>();
    }

    public boolean delete(long id) throws SQLException {
        // TODO: true if a row was deleted
        return false;
    }

    @Override
    public void close() throws SQLException {
        db.close();
    }
}
