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
        // TODO
        return new ArrayList<>();
    }

    public static Map<String, Integer> minutesByActivity(Connection db) throws SQLException {
        // TODO
        return new LinkedHashMap<>();
    }

    public static int sessionsFor(Connection db, String member) throws SQLException {
        // TODO: use ? for the member, never +
        return 0;
    }
}
