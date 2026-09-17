import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PasswordManagerLogicTest {

    @Test
    void generatesAPasswordOfTheRequestedLength() {
        assertEquals(16, PasswordManagerLogic.generatePassword(16, new Random()).length());
    }

    @Test
    void formatsAnEntryAsPipeSeparatedFields() {
        assertEquals("github.com|sage|hunter2", PasswordManagerLogic.formatEntry("github.com", "sage", "hunter2"));
    }

    @Test
    void parsesAWellFormedLineBackIntoItsFields() {
        assertArrayEquals(new String[]{"github.com", "sage", "hunter2"},
                PasswordManagerLogic.parseEntry("github.com|sage|hunter2"));
    }

    @Test
    void rejectsALineThatIsMissingAField() {
        assertNull(PasswordManagerLogic.parseEntry("github.com|sage"));
    }
}
