import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvTest {

    @Test
    void plainFieldsStayPlain() {
        assertEquals("Ruaka,1,28000", Csv.line(List.of("Ruaka", "1", "28000")));
        assertEquals(List.of("Ruaka", "1", "28000"), Csv.parse("Ruaka,1,28000"));
    }

    @Test
    void awkwardFieldsAreQuoted() {
        assertEquals("\"2BR, 3rd floor\",\"\"\"Mama\"\" Njeri\"", Csv.line(List.of("2BR, 3rd floor", "\"Mama\" Njeri")));
    }

    @Test
    void everythingSurvivesARoundTrip() {
        List<String> awkward = List.of("2BR, 3rd floor", "\"Mama\" Njeri", "", "plain", "a,\"b\",c");
        assertEquals(awkward, Csv.parse(Csv.line(awkward)));
    }
}
