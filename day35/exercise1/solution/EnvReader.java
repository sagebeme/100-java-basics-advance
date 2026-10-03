import java.util.Map;
import java.util.Set;

public class EnvReader {

    private static final Set<String> YES = Set.of("true", "yes", "1", "on");

    public static String text(Map<String, String> env, String name, String fallback) {
        String value = env.get(name);
        return value == null || value.isBlank() ? fallback : value.trim();
    }

    public static int number(Map<String, String> env, String name, int fallback) {
        try {
            return Integer.parseInt(text(env, name, "").trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public static boolean flag(Map<String, String> env, String name) {
        return YES.contains(text(env, name, "").toLowerCase());
    }

    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        System.out.println("PORT: " + number(env, "PORT", 8080));
        System.out.println("USER: " + text(env, "USER", text(env, "USERNAME", "unknown")));
        System.out.println("DEBUG: " + flag(env, "DEBUG"));
    }
}
