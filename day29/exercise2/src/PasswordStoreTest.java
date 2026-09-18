import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordStoreTest {

    @Test
    void savingThenLoadingReturnsTheSameEntries() throws IOException {
        String path = Files.createTempFile("passwords", ".txt").toString();
        List<String> entries = List.of("github.com|sage|hunter2", "gmail.com|sage|letmein");

        PasswordStore.save(path, entries);
        assertEquals(entries, PasswordStore.load(path));
    }

    @Test
    void loadingAMissingFileReturnsAnEmptyList() throws IOException {
        assertTrue(PasswordStore.load("does-not-exist.txt").isEmpty());
    }

    @Test
    void searchFindsEntriesContainingTheTerm() {
        List<String> entries = List.of("github.com|sage|hunter2", "gmail.com|sage|letmein");
        assertEquals(List.of("github.com|sage|hunter2"), PasswordStore.search(entries, "github"));
    }

    @Test
    void deleteRemovesOnlyTheMatchingWebsite() {
        List<String> entries = List.of("github.com|sage|hunter2", "gmail.com|sage|letmein");
        assertEquals(List.of("gmail.com|sage|letmein"), PasswordStore.delete(entries, "github.com"));
    }
}
