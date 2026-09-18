import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileWriterTest {

    @Test
    void formatsTheDataFileContentWithItsNumber() {
        assertEquals("This is data file 2" + System.lineSeparator(), FileWriter.formatDataFileContent(2));
    }
}
