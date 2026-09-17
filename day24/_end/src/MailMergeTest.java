import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailMergeTest {

    @Test
    void replacesThePlaceholderWithTheName() {
        String template = "Dear [name],\nThank you for coming.\n";
        assertEquals("Dear Wanjiru,\nThank you for coming.\n", MailMerge.merge(template, "Wanjiru"));
    }

    @Test
    void replacesEveryOccurrenceOfThePlaceholder() {
        String template = "Hi [name], is this [name] again?";
        assertEquals("Hi Kip, is this Kip again?", MailMerge.merge(template, "Kip"));
    }
}
