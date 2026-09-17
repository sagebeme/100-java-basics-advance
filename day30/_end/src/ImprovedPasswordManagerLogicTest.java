import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImprovedPasswordManagerLogicTest {

    @Test
    void matchesOnTheWebsite() {
        assertTrue(ImprovedPasswordManagerLogic.matches("github.com", "sage", "github"));
    }

    @Test
    void matchesOnTheUsername() {
        assertTrue(ImprovedPasswordManagerLogic.matches("github.com", "sage", "SAGE"));
    }

    @Test
    void noMatchWhenNeitherFieldContainsTheTerm() {
        assertFalse(ImprovedPasswordManagerLogic.matches("github.com", "sage", "gitlab"));
    }
}
