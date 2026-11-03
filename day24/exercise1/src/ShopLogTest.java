import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShopLogTest {

    @TempDir
    Path folder;

    // The same day as duka.log, written fresh for each test.
    private Path log() throws IOException {
        return Files.write(folder.resolve("duka.log"), List.of(
                "OPEN 07:00 Mama Njeri",
                "SALE 120 sugar",
                "SALE 60 bread",
                "",
                "SALE 250 cooking oil",
                "RESTOCK 12 Sugar 2kg",
                "SALE 45 sugar sachet",
                "CLOSE 21:00"));
    }

    @Test
    void countsLinesButNotBlankOnes() throws IOException {
        assertEquals(7, ShopLog.lineCount(log()));
    }

    @Test
    void countsWords() throws IOException {
        assertEquals(24, ShopLog.wordCount(log()));
    }

    @Test
    void findsAWordInAnyCaseWithItsLineNumber() throws IOException {
        assertEquals(List.of("2: SALE 120 sugar", "6: RESTOCK 12 Sugar 2kg", "7: SALE 45 sugar sachet"),
                ShopLog.find(log(), "SUGAR"));
        assertEquals(List.of(), ShopLog.find(log(), "milk"));
    }

    @Test
    void addsUpTheSales() throws IOException {
        assertEquals(475, ShopLog.totalSales(log()));
    }

    @Test
    void anEmptyLogIsAllZeroes() throws IOException {
        Path empty = Files.createFile(folder.resolve("empty.log"));
        assertEquals(0, ShopLog.lineCount(empty));
        assertEquals(0, ShopLog.wordCount(empty));
        assertEquals(0, ShopLog.totalSales(empty));
    }

    @Test
    void aMissingFileIsAnErrorNotAnEmptyLog() {
        assertThrows(NoSuchFileException.class, () -> ShopLog.lineCount(folder.resolve("nope.log")));
    }
}
