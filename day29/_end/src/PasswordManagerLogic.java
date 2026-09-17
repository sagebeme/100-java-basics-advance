import java.util.Random;

public class PasswordManagerLogic {

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";

    public static String generatePassword(int length, Random random) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return password.toString();
    }

    /**
     * Turns a website/username/password entry into the "website|username|password" line
     * the file format uses.
     */
    public static String formatEntry(String website, String username, String password) {
        return website + "|" + username + "|" + password;
    }

    /**
     * Reverses formatEntry. Returns null if the line does not have exactly 3 parts.
     */
    public static String[] parseEntry(String line) {
        String[] parts = line.split("\\|");
        return parts.length == 3 ? parts : null;
    }
}
