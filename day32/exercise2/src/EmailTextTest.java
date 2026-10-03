import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailTextTest {

    @Test
    void acceptsOrdinaryAddresses() {
        assertTrue(EmailText.isValidAddress("wanjiku@example.com"));
        assertTrue(EmailText.isValidAddress("wanjiku.k@mail.co.ke"));
    }

    @Test
    void refusesBrokenAddresses() {
        assertFalse(EmailText.isValidAddress("wanjiku@localhost"));
        assertFalse(EmailText.isValidAddress("@example.com"));
        assertFalse(EmailText.isValidAddress("wan jiku@example.com"));
        assertFalse(EmailText.isValidAddress("a@b@example.com"));
        assertFalse(EmailText.isValidAddress("wanjiku@example."));
        assertFalse(EmailText.isValidAddress("wanjiku@.com"));
    }

    @Test
    void writesHeadersABlankLineAndTheBody() {
        assertEquals("To: wanjiku@example.com\nSubject: Happy birthday!\n\nHave a wonderful day.",
                EmailText.toMessageText("wanjiku@example.com", "Happy birthday!", "Have a wonderful day."));
    }

    @Test
    void refusesToBuildAnEmailToABadAddress() {
        assertThrows(IllegalArgumentException.class, () -> EmailText.toMessageText("nobody", "Hi", "Hello"));
    }
}
