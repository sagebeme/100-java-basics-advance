import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FirstTableTest {

    private Connection fresh() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:first" + System.nanoTime());
    }

    @Test
    void aNewTableIsEmpty() throws SQLException {
        try (Connection db = fresh()) {
            FirstTable.createTable(db);
            assertEquals(0, FirstTable.countMembers(db));
        }
    }

    @Test
    void countsWhatWasAdded() throws SQLException {
        try (Connection db = fresh()) {
            FirstTable.createTable(db);
            FirstTable.addMember(db, "Brian", LocalDate.of(2026, 1, 10));
            FirstTable.addMember(db, "Akinyi", LocalDate.of(2026, 3, 2));
            assertEquals(2, FirstTable.countMembers(db));
        }
    }

    @Test
    void creatingTwiceIsHarmless() throws SQLException {
        try (Connection db = fresh()) {
            FirstTable.createTable(db);
            FirstTable.addMember(db, "Brian", LocalDate.of(2026, 1, 10));
            FirstTable.createTable(db);
            assertEquals(1, FirstTable.countMembers(db));
        }
    }

    @Test
    void aMemberNeedsAName() throws SQLException {
        try (Connection db = fresh()) {
            FirstTable.createTable(db);
            assertThrows(SQLException.class, () -> FirstTable.addMember(db, null, LocalDate.of(2026, 1, 10)));
        }
    }
}
