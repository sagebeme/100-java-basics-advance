import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileReaderTest {

    @Test
    void countsWordsSeparatedBySpaces() {
        assertEquals(3, FileReader.countWords("Java is fun"));
    }

    @Test
    void anEmptyLineHasNoWords() {
        assertEquals(0, FileReader.countWords("   "));
    }

    @Test
    void findsAWordRegardlessOfCase() {
        assertTrue(FileReader.containsWord("I love JAVA programming", "java"));
    }

    @Test
    void reportsWhenAWordIsMissing() {
        assertFalse(FileReader.containsWord("I love Python programming", "java"));
    }
}
