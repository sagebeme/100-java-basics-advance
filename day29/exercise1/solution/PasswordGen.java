import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordGen {

    public static final String LOWER = "abcdefghijkmnpqrstuvwxyz";
    public static final String UPPER = "ABCDEFGHJKLMNPQRSTUVWXYZ";
    public static final String DIGITS = "23456789";
    public static final String SYMBOLS = "!@#$%&*?-_=+";

    // A password of `length` characters with at least one from every chosen set, in a random order.
    public static String generate(int length, boolean upper, boolean digits, boolean symbols, Random random) {
        List<String> sets = new ArrayList<>();
        sets.add(LOWER);
        if (upper) {
            sets.add(UPPER);
        }
        if (digits) {
            sets.add(DIGITS);
        }
        if (symbols) {
            sets.add(SYMBOLS);
        }
        if (length < sets.size()) {
            throw new IllegalArgumentException(
                    "A password of " + length + " can't hold one of each of " + sets.size() + " kinds");
        }

        String pool = String.join("", sets);
        List<Character> chars = new ArrayList<>();
        for (String set : sets) {
            chars.add(pick(set, random));
        }
        while (chars.size() < length) {
            chars.add(pick(pool, random));
        }
        Collections.shuffle(chars, random);

        StringBuilder password = new StringBuilder();
        for (char c : chars) {
            password.append(c);
        }
        return password.toString();
    }

    private static char pick(String set, Random random) {
        return set.charAt(random.nextInt(set.length()));
    }

    // How many of the four kinds (lower case, upper case, digit, anything else) the password uses.
    public static int kinds(String password) {
        boolean lower = false;
        boolean upperCase = false;
        boolean digit = false;
        boolean other = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lower = true;
            } else if (Character.isUpperCase(c)) {
                upperCase = true;
            } else if (Character.isDigit(c)) {
                digit = true;
            } else {
                other = true;
            }
        }
        return (lower ? 1 : 0) + (upperCase ? 1 : 0) + (digit ? 1 : 0) + (other ? 1 : 0);
    }

    // "weak": under 8 characters, or only one kind. "strong": 12 or more and at least 3 kinds. Else "medium".
    public static String strength(String password) {
        int kinds = kinds(password);
        if (password.length() < 8 || kinds < 2) {
            return "weak";
        }
        if (password.length() >= 12 && kinds >= 3) {
            return "strong";
        }
        return "medium";
    }

    public static void main(String[] args) {
        Random random = new SecureRandom(); // unpredictable, unlike new Random()
        for (int i = 0; i < 3; i++) {
            String password = generate(14, true, true, true, random);
            System.out.println(password + "  " + strength(password));
        }
    }
}
