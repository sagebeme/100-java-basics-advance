import java.util.Map;

public class EnvReader {

    public static String text(Map<String, String> env, String name, String fallback) {
        // TODO
        return fallback;
    }

    public static int number(Map<String, String> env, String name, int fallback) {
        // TODO
        return fallback;
    }

    public static boolean flag(Map<String, String> env, String name) {
        // TODO: true, yes, 1, on
        return false;
    }

    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        System.out.println("PORT: " + number(env, "PORT", 8080));
        System.out.println("USER: " + text(env, "USER", text(env, "USERNAME", "unknown")));
        System.out.println("DEBUG: " + flag(env, "DEBUG"));
    }
}
