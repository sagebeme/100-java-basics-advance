import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class PasswordGenerator {

    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    /**
     * Builds a password with exactly numLetters letters, numSymbols symbols and numNumbers digits,
     * in a random order.
     */
    public static ArrayList<Character> buildPasswordChars(int numLetters, int numSymbols, int numNumbers, Random random) {
        ArrayList<Character> password = new ArrayList<>();

        for (int i = 0; i < numLetters; i++) {
            password.add(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }
        for (int i = 0; i < numSymbols; i++) {
            password.add(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        }
        for (int i = 0; i < numNumbers; i++) {
            password.add(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }

        Collections.shuffle(password, random);
        return password;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Password Generator!");

        // Get user preferences
        System.out.println("How many letters would you like in your password?");
        int numLetters = scanner.nextInt();

        System.out.println("How many symbols would you like?");
        int numSymbols = scanner.nextInt();

        System.out.println("How many numbers would you like?");
        int numNumbers = scanner.nextInt();

        // Generate password
        ArrayList<Character> password = buildPasswordChars(numLetters, numSymbols, numNumbers, random);

        // Convert to string
        StringBuilder passwordString = new StringBuilder();
        for (char c : password) {
            passwordString.append(c);
        }

        System.out.println("Your password is: " + passwordString.toString());

        scanner.close();
    }
}
