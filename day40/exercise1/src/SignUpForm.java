import java.util.ArrayList;
import java.util.List;

public class SignUpForm {

    public static List<String> problems(String name, String email, String confirmEmail, boolean agreed) {
        List<String> problems = new ArrayList<>();
        // TODO: one if per check, in the README's order
        return problems;
    }

    public static void main(String[] args) {
        System.out.println(problems("", "wanjiku@example", "wanjiku@example.com", false));
    }
}
