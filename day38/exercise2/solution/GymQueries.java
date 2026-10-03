import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GymQueries {

    public static List<String> longSessions(Connection db, int minMinutes) throws SQLException {
        List<String> names = new ArrayList<>();
        try (PreparedStatement q = db.prepareStatement(
                "SELECT DISTINCT member FROM sessions WHERE minutes >= ? ORDER BY member")) {
            q.setInt(1, minMinutes);
            try (ResultSet rows = q.executeQuery()) {
                while (rows.next()) names.add(rows.getString("member"));
            }
        }
        return names;
    }

    public static Map<String, Integer> minutesByActivity(Connection db) throws SQLException {
        Map<String, Integer> totals = new LinkedHashMap<>();
        try (PreparedStatement q = db.prepareStatement(
                "SELECT activity, SUM(minutes) AS total FROM sessions GROUP BY activity ORDER BY total DESC");
             ResultSet rows = q.executeQuery()) {
            while (rows.next()) totals.put(rows.getString("activity"), rows.getInt("total"));
        }
        return totals;
    }

    public static int sessionsFor(Connection db, String member) throws SQLException {
        try (PreparedStatement q = db.prepareStatement("SELECT COUNT(*) FROM sessions WHERE member = ?")) {
            q.setString(1, member);
            try (ResultSet rows = q.executeQuery()) {
                rows.next();
                return rows.getInt(1);
            }
        }
    }
}
