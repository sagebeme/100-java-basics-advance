import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupFormTest {

    @Test
    void aGoodFormHasNoErrors() {
        assertTrue(SignupForm.validate("Brian Otieno", "0712 345 678", "12345678", "34").isEmpty());
    }

    @Test
    void acceptsPhoneNumbersTheWayPeopleTypeThem() {
        assertEquals("0712345678", SignupForm.normalisePhone("0712345678"));
        assertEquals("0712345678", SignupForm.normalisePhone("0712 345 678"));
        assertEquals("0712345678", SignupForm.normalisePhone("+254712345678"));
        assertEquals("0112345678", SignupForm.normalisePhone("254 112-345-678"));
    }

    @Test
    void refusesWhatIsNotAKenyanMobile() {
        assertNull(SignupForm.normalisePhone("0812345678"));
        assertNull(SignupForm.normalisePhone("071234567"));
        assertNull(SignupForm.normalisePhone("+1 202 555 0100"));
        assertNull(SignupForm.normalisePhone(""));
        assertNull(SignupForm.normalisePhone(null));
    }

    @Test
    void reportsEveryProblemAtOnceInFieldOrder() {
        Map<String, String> errors = SignupForm.validate(" ", "12345", "12AB", "seventeen");
        assertEquals(List.of("name", "phone", "idNumber", "age"), List.copyOf(errors.keySet()));
        assertEquals("Type your full name.", errors.get("name"));
        assertEquals("Use a number like 0712 345 678 or +254 712 345 678.", errors.get("phone"));
        assertEquals("A national ID number is 7 or 8 digits.", errors.get("idNumber"));
        assertEquals("Type your age in years, like 34.", errors.get("age"));
    }

    @Test
    void onlyTheBadFieldsAreReported() {
        Map<String, String> errors = SignupForm.validate("Brian Otieno", "0712345678", "123456789", "34");
        assertEquals(Map.of("idNumber", "A national ID number is 7 or 8 digits."), errors);
    }

    @Test
    void ageMustBeEighteenToAHundred() {
        assertEquals("You must be 18 or over to sign up.",
                SignupForm.validate("Brian Otieno", "0712345678", "1234567", "17").get("age"));
        assertEquals("Check your age: over 100?",
                SignupForm.validate("Brian Otieno", "0712345678", "1234567", "101").get("age"));
        assertTrue(SignupForm.validate("Brian Otieno", "0712345678", "1234567", "18").isEmpty());
        assertTrue(SignupForm.validate("Brian Otieno", "0712345678", "1234567", "100").isEmpty());
    }

    @Test
    void nullFieldsAreProblemsNotCrashes() {
        assertEquals(4, SignupForm.validate(null, null, null, null).size());
    }
}
