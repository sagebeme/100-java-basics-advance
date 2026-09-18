import java.util.Random;

public class PasswordGen {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-=";

    public static String generate(int length, boolean useLetters, boolean useDigits, boolean useSymbols, Random random) {
        StringBuilder pool = new StringBuilder();
        if (useLetters) pool.append(LETTERS);
        if (useDigits) pool.append(DIGITS);
        if (useSymbols) pool.append(SYMBOLS);
        if (pool.length() == 0) {
            throw new IllegalArgumentException("At least one character set must be enabled");
        }

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(pool.charAt(random.nextInt(pool.length())));
        }
        return password.toString();
    }

    /**
     * @return "weak" (under 8 chars), "medium" (8+ chars, one character type), or
     * "strong" (12+ chars, at least two character types).
     */
    public static String strength(String password) {
        boolean hasLetter = password.chars().anyMatch(Character::isLetter);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSymbol = password.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        int varietyCount = (hasLetter ? 1 : 0) + (hasDigit ? 1 : 0) + (hasSymbol ? 1 : 0);

        if (password.length() < 8) return "weak";
        if (password.length() >= 12 && varietyCount >= 2) return "strong";
        return "medium";
    }

    public static void main(String[] args) {
        String password = generate(16, true, true, true, new Random());
        System.out.println(password + " (" + strength(password) + ")");
    }
}
