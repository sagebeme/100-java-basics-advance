import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpFormTest {

    @Test
    void aGoodFormHasNoProblems() {
        assertTrue(SignUpForm.problems("Wanjiku", "wanjiku@example.com", " Wanjiku@Example.com ", true).isEmpty());
    }

    @Test
    void listsEveryProblemInOrder() {
        assertEquals(List.of("Please enter your name.", "Please enter a valid email address.", "Please agree to receive deal emails."),
                SignUpForm.problems(" ", "wanjiku@example", "wanjiku@example.com", false));
    }

    @Test
    void mismatchedEmailsAreReported() {
        assertEquals(List.of("The email addresses don't match."),
                SignUpForm.problems("Wanjiku", "wanjiku@example.com", "wanjiku@example.co", true));
    }

    @Test
    void handlesMissingValues() {
        assertEquals(List.of("Please enter your name.", "Please enter a valid email address."),
                SignUpForm.problems(null, null, null, true));
    }
}
