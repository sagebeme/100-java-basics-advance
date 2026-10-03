import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymQueriesTest {

    private Connection db;

    @BeforeEach
    void fillTheGym() throws SQLException {
        db = DriverManager.getConnection("jdbc:h2:mem:gym" + System.nanoTime());
        try (Statement s = db.createStatement()) {
            s.execute("CREATE TABLE sessions (id INT AUTO_INCREMENT PRIMARY KEY, member VARCHAR(40), activity VARCHAR(40), minutes INT)");
            s.execute("""
                    INSERT INTO sessions (member, activity, minutes) VALUES
                    ('Brian', 'run', 45), ('Brian', 'football', 60), ('Akinyi', 'swim', 30),
                    ('Akinyi', 'run', 20), ('Kamau', 'gym', 90), ('Brian', 'run', 30)""");
        }
    }

    @AfterEach
    void close() throws SQLException {
        db.close();
    }

    @Test
    void findsMembersWithLongSessionsOnceEach() throws SQLException {
        assertEquals(List.of("Brian", "Kamau"), GymQueries.longSessions(db, 45));
        assertEquals(List.of("Akinyi", "Brian", "Kamau"), GymQueries.longSessions(db, 0));
    }

    @Test
    void totalsMinutesPerActivityBiggestFirst() throws SQLException {
        Map<String, Integer> totals = GymQueries.minutesByActivity(db);
        assertEquals(List.of("run", "gym", "football", "swim"), List.copyOf(totals.keySet()));
        assertEquals(95, totals.get("run"));
    }

    @Test
    void countsOneMembersSessions() throws SQLException {
        assertEquals(3, GymQueries.sessionsFor(db, "Brian"));
        assertEquals(0, GymQueries.sessionsFor(db, "Otieno"));
    }

    @Test
    void injectionAttemptIsJustAStrangeName() throws SQLException {
        assertEquals(0, GymQueries.sessionsFor(db, "x' OR '1'='1"));
    }
}
