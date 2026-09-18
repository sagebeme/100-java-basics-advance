import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void aFilledFieldPassesTheRequiredCheck() {
        assertTrue(Exercise2.isRequiredFieldFilled("Amina"));
    }

    @Test
    void aBlankFieldFailsTheRequiredCheck() {
        assertFalse(Exercise2.isRequiredFieldFilled("   "));
    }

    @Test
    void recognisesAWellFormedEmail() {
        assertTrue(Exercise2.isValidEmail("amina@example.com"));
    }

    @Test
    void rejectsTextWithoutAnAtSign() {
        assertFalse(Exercise2.isValidEmail("not-an-email"));
    }

    @Test
    void aCompleteFormIsValid() {
        assertEquals("Valid", Exercise2.validate("Amina", "amina@example.com"));
    }

    @Test
    void aMissingNameFailsFirst() {
        assertEquals("Name is required", Exercise2.validate("", "amina@example.com"));
    }
}
