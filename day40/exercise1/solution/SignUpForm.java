import java.util.ArrayList;
import java.util.List;

public class SignUpForm {

    public static List<String> problems(String name, String email, String confirmEmail, boolean agreed) {
        List<String> problems = new ArrayList<>();
        if (name == null || name.isBlank()) {
            problems.add("Please enter your name.");
        }
        boolean validEmail = email != null && email.trim().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+");
        if (!validEmail) {
            problems.add("Please enter a valid email address.");
        } else if (confirmEmail == null || !email.trim().equalsIgnoreCase(confirmEmail.trim())) {
            problems.add("The email addresses don't match.");
        }
        if (!agreed) {
            problems.add("Please agree to receive deal emails.");
        }
        return problems;
    }

    public static void main(String[] args) {
        System.out.println(problems("", "wanjiku@example", "wanjiku@example.com", false));
    }
}
