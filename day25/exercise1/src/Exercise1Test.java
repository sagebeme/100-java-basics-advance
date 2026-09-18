import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void parsesASingleLineIntoFields() {
        assertEquals(List.of("Amina", "30"), Exercise1.parseLine("Amina, 30", ","));
    }

    @Test
    void skipsTheHeaderRow() {
        String csv = "name,age\nAmina,30\nKip,25";
        List<List<String>> rows = Exercise1.parseCsv(csv, ",");

        assertEquals(2, rows.size());
        assertEquals(List.of("Amina", "30"), rows.get(0));
    }

    @Test
    void handlesADifferentDelimiter() {
        assertEquals(List.of("Amina", "30"), Exercise1.parseLine("Amina;30", ";"));
    }
}
