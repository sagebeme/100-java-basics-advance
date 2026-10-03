import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Everything that touches SQL lives here. The rest of the program only sees Workouts and totals.
public class WorkoutDb implements AutoCloseable {

    private final Connection db;

    public WorkoutDb(String jdbcUrl) throws SQLException {
        this.db = DriverManager.getConnection(jdbcUrl);
    }

    public void createTable() throws SQLException {
        try (Statement s = db.createStatement()) {
            s.execute("""
                    CREATE TABLE IF NOT EXISTS workouts (
                        id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                        workout_date DATE        NOT NULL,
                        activity     VARCHAR(40) NOT NULL,
                        minutes      INT         NOT NULL CHECK (minutes > 0),
                        kcal         INT         NOT NULL
                    )""");
        }
    }

    // Saves the workout and returns it with the id the database gave it.
    public Workout add(Workout w) throws SQLException {
        String sql = "INSERT INTO workouts (workout_date, activity, minutes, kcal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insert = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            insert.setObject(1, w.day());
            insert.setString(2, w.activity());
            insert.setInt(3, w.minutes());
            insert.setInt(4, w.kcal());
            insert.executeUpdate();
            try (ResultSet keys = insert.getGeneratedKeys()) {
                keys.next();
                return new Workout(keys.getLong(1), w.day(), w.activity(), w.minutes(), w.kcal());
            }
        }
    }

    public List<Workout> forDay(LocalDate day) throws SQLException {
        List<Workout> found = new ArrayList<>();
        try (PreparedStatement q = db.prepareStatement("SELECT * FROM workouts WHERE workout_date = ? ORDER BY id")) {
            q.setObject(1, day);
            try (ResultSet rows = q.executeQuery()) {
                while (rows.next()) {
                    found.add(new Workout(rows.getLong("id"), rows.getObject("workout_date", LocalDate.class),
                            rows.getString("activity"), rows.getInt("minutes"), rows.getInt("kcal")));
                }
            }
        }
        return found;
    }

    // One row per activity between the two days (both included), sorted by activity name.
    public List<ActivityTotal> totalsBetween(LocalDate from, LocalDate to) throws SQLException {
        String sql = """
                SELECT activity, SUM(minutes) AS minutes, SUM(kcal) AS kcal
                FROM workouts
                WHERE workout_date BETWEEN ? AND ?
                GROUP BY activity
                ORDER BY activity""";
        List<ActivityTotal> totals = new ArrayList<>();
        try (PreparedStatement q = db.prepareStatement(sql)) {
            q.setObject(1, from);
            q.setObject(2, to);
            try (ResultSet rows = q.executeQuery()) {
                while (rows.next()) {
                    totals.add(new ActivityTotal(rows.getString("activity"), rows.getInt("minutes"), rows.getInt("kcal")));
                }
            }
        }
        return totals;
    }

    public boolean delete(long id) throws SQLException {
        try (PreparedStatement d = db.prepareStatement("DELETE FROM workouts WHERE id = ?")) {
            d.setLong(1, id);
            return d.executeUpdate() == 1;
        }
    }

    @Override
    public void close() throws SQLException {
        db.close();
    }
}
