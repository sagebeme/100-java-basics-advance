import java.util.LinkedHashMap;
import java.util.Map;

// The rules behind the sign-up window: no JavaFX here, so they can be tested.
public class SignupForm {

    // Every problem with the form at once, field name → message, in the order the fields appear.
    // An empty map means the form is fine.
    public static Map<String, String> validate(String name, String phone, String idNumber, String age) {
        Map<String, String> errors = new LinkedHashMap<>();
        // TODO: check each field and put its message under "name", "phone", "idNumber" or "age"
        return errors;
    }

    // "0712 345 678", "+254712345678" and "254 712-345-678" all become "0712345678". Not a phone: null.
    public static String normalisePhone(String phone) {
        // TODO
        return null;
    }
}
