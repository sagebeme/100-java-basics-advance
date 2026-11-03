import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordGen {

    // No l, o, I, O, 0 or 1: staff read these off a printed slip, and those are easy to mix up.
    public static final String LOWER = "abcdefghijkmnpqrstuvwxyz";
    public static final String UPPER = "ABCDEFGHJKLMNPQRSTUVWXYZ";
    public static final String DIGITS = "23456789";
    public static final String SYMBOLS = "!@#$%&*?-_=+";

    // A password of `length` characters with at least one from every chosen set, in a random order.
    // Lower case is always used. Use only `random` for chance, so the tests can repeat a run.
    public static String generate(int length, boolean upper, boolean digits, boolean symbols, Random random) {
        // TODO: refuse a length too short to hold one of each chosen kind
        // TODO: one from each chosen set, fill up from all of them, then Collections.shuffle(list, random)
        return "";
    }

    // How many of the four kinds (lower case, upper case, digit, anything else) the password uses.
    public static int kinds(String password) {
        // TODO
        return 0;
    }

    // "weak": under 8 characters, or only one kind. "strong": 12 or more and at least 3 kinds. Else "medium".
    public static String strength(String password) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            String password = generate(14, true, true, true, random);
            System.out.println(password + "  " + strength(password));
        }
    }
}
