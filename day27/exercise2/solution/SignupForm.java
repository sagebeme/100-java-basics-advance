import java.util.LinkedHashMap;
import java.util.Map;

// The rules behind the sign-up window: no JavaFX here, so they can be tested.
public class SignupForm {

    // Every problem with the form at once, field name → message, in the order the fields appear.
    // An empty map means the form is fine.
    public static Map<String, String> validate(String name, String phone, String idNumber, String age) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (name == null || name.strip().length() < 2) {
            errors.put("name", "Type your full name.");
        }

        if (normalisePhone(phone) == null) {
            errors.put("phone", "Use a number like 0712 345 678 or +254 712 345 678.");
        }

        String id = idNumber == null ? "" : idNumber.strip();
        if (!id.matches("\\d{7,8}")) {
            errors.put("idNumber", "A national ID number is 7 or 8 digits.");
        }

        String years = age == null ? "" : age.strip();
        if (!years.matches("\\d{1,3}")) {
            errors.put("age", "Type your age in years, like 34.");
        } else if (Integer.parseInt(years) < 18) {
            errors.put("age", "You must be 18 or over to sign up.");
        } else if (Integer.parseInt(years) > 100) {
            errors.put("age", "Check your age: over 100?");
        }

        return errors;
    }

    // "0712 345 678", "+254712345678" and "254 712-345-678" all become "0712345678". Not a phone: null.
    public static String normalisePhone(String phone) {
        if (phone == null) {
            return null;
        }
        String digits = phone.replaceAll("[\\s-]", "");
        if (digits.startsWith("+254")) {
            digits = "0" + digits.substring(4);
        } else if (digits.startsWith("254")) {
            digits = "0" + digits.substring(3);
        }
        return digits.matches("0[17]\\d{8}") ? digits : null;
    }
}
