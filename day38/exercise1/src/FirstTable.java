import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class FirstTable {

    public static void createTable(Connection db) throws SQLException {
        // TODO
    }

    public static void addMember(Connection db, String name, LocalDate joined) throws SQLException {
        // TODO
    }

    public static int countMembers(Connection db) throws SQLException {
        // TODO
        return 0;
    }

    public static void main(String[] args) throws SQLException {
        try (Connection db = DriverManager.getConnection("jdbc:h2:mem:club")) {
            createTable(db);
            addMember(db, "Brian", LocalDate.of(2026, 1, 10));
            addMember(db, "Akinyi", LocalDate.of(2026, 3, 2));
            addMember(db, "Kamau", LocalDate.of(2026, 9, 21));
            System.out.println(countMembers(db) + " members");
        }
    }
}
