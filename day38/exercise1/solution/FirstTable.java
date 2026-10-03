import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class FirstTable {

    public static void createTable(Connection db) throws SQLException {
        try (Statement s = db.createStatement()) {
            s.execute("CREATE TABLE IF NOT EXISTS members ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(60) NOT NULL, joined DATE NOT NULL)");
        }
    }

    public static void addMember(Connection db, String name, LocalDate joined) throws SQLException {
        try (PreparedStatement insert = db.prepareStatement("INSERT INTO members (name, joined) VALUES (?, ?)")) {
            insert.setString(1, name);
            insert.setObject(2, joined);
            insert.executeUpdate();
        }
    }

    public static int countMembers(Connection db) throws SQLException {
        try (Statement s = db.createStatement(); ResultSet rows = s.executeQuery("SELECT COUNT(*) FROM members")) {
            rows.next();
            return rows.getInt(1);
        }
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
