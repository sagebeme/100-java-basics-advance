import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReceiptsTest {

    @TempDir
    Path folder;

    private Map<String, Integer> otienosShopping() {
        Map<String, Integer> items = new LinkedHashMap<>();
        items.put("Sugar 2kg", 240);
        items.put("Bread", 60);
        return items;
    }

    @Test
    void linesLineUp() {
        assertEquals("Bread             60", Receipts.line("Bread", 60));
        assertEquals("TOTAL            300", Receipts.line("TOTAL", 300));
        assertEquals("Cooking oil 1 l 1250", Receipts.line("Cooking oil 1 litre", 1250));
        assertEquals(20, Receipts.line("x", 5).length());
    }

    @Test
    void writesTheReceipt() throws IOException {
        Path file = Receipts.write(folder, 7, "Otieno", otienosShopping());
        assertEquals(folder.resolve("receipt-0007.txt"), file);
        assertEquals(List.of(
                "MAMA NJERI'S DUKA",
                "Receipt 0007 for Otieno",
                "Sugar 2kg        240",
                "Bread             60",
                "--------------------",
                "TOTAL            300"), Files.readAllLines(file));
    }

    @Test
    void makesTheFolderIfItIsMissing() throws IOException {
        Path file = Receipts.write(folder.resolve("2026").resolve("november"), 12, "Akinyi", otienosShopping());
        assertTrue(Files.exists(file));
    }

    @Test
    void neverOverwritesAReceipt() throws IOException {
        Receipts.write(folder, 7, "Otieno", otienosShopping());
        Map<String, Integer> other = new LinkedHashMap<>();
        other.put("Milk", 60);
        assertThrows(FileAlreadyExistsException.class, () -> Receipts.write(folder, 7, "Someone else", other));
        assertTrue(Files.readString(folder.resolve("receipt-0007.txt")).contains("Otieno"));
    }

    @Test
    void theDayBookGrowsOneLineAtATime() throws IOException {
        Path dayBook = folder.resolve("daybook.txt");
        Receipts.addToDayBook(dayBook, 7, 300);
        Receipts.addToDayBook(dayBook, 8, 1250);
        assertEquals(List.of("0007 300", "0008 1250"), Files.readAllLines(dayBook));
    }
}
