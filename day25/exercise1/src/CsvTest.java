import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvTest {

    @TempDir
    Path folder;

    @Test
    void splitsAPlainLine() {
        assertEquals(List.of("Wanjiku Mwangi", "2026-11-02", "18", "A"),
                Csv.parseLine("Wanjiku Mwangi,2026-11-02,18,A", ','));
    }

    @Test
    void keepsEmptyFieldsIncludingTheLast() {
        assertEquals(List.of("a", "", "c", ""), Csv.parseLine("a,,c,", ','));
        assertEquals(List.of(""), Csv.parseLine("", ','));
    }

    @Test
    void aQuotedFieldMayContainTheDelimiter() {
        assertEquals(List.of("Kamau, John", "12", "late, truck broke down"),
                Csv.parseLine("\"Kamau, John\",12,\"late, truck broke down\"", ','));
    }

    @Test
    void twoQuotesInsideQuotesAreOneQuote() {
        assertEquals(List.of("Njoroge", "said \"asante\" twice"),
                Csv.parseLine("Njoroge,\"said \"\"asante\"\" twice\"", ','));
    }

    @Test
    void worksWithASemicolon() {
        assertEquals(List.of("Kamau, John", "12,5", "B"), Csv.parseLine("Kamau, John;12,5;B", ';'));
    }

    @Test
    void refusesAQuoteThatIsNeverClosed() {
        assertThrows(IllegalArgumentException.class, () -> Csv.parseLine("\"Kamau, John,12", ','));
    }

    @Test
    void readsRowsSkippingTheHeaderAndBlankLines() throws IOException {
        Path file = Files.write(folder.resolve("deliveries.csv"), List.of(
                "farmer,litres",
                "Wanjiku,18",
                "",
                "\"Kamau, John\",12"));
        assertEquals(List.of(List.of("Wanjiku", "18"), List.of("Kamau, John", "12")),
                Csv.readRows(file, ',', true));
    }

    @Test
    void keepsTheFirstRowWhenThereIsNoHeader() throws IOException {
        Path file = Files.write(folder.resolve("noheader.csv"), List.of("Wanjiku,18", "Kamau,12"));
        assertEquals(2, Csv.readRows(file, ',', false).size());
    }
}
