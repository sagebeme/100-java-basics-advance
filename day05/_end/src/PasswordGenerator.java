import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Password Generator!");
        
        // Character sets
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        
        // Get user preferences
        System.out.println("How many letters would you like in your password?");
        int numLetters = scanner.nextInt();
        
        System.out.println("How many symbols would you like?");
        int numSymbols = scanner.nextInt();
        
        System.out.println("How many numbers would you like?");
        int numNumbers = scanner.nextInt();
        
        // Generate password
        ArrayList<Character> password = new ArrayList<>();
        
        // Add letters
        for (int i = 0; i < numLetters; i++) {
            password.add(letters.charAt(random.nextInt(letters.length())));
        }
        
        // Add symbols
        for (int i = 0; i < numSymbols; i++) {
            password.add(symbols.charAt(random.nextInt(symbols.length())));
        }
        
        // Add numbers
        for (int i = 0; i < numNumbers; i++) {
            password.add(numbers.charAt(random.nextInt(numbers.length())));
        }
        
        // Shuffle the password
        Collections.shuffle(password);
        
        // Convert to string
        StringBuilder passwordString = new StringBuilder();
        for (char c : password) {
            passwordString.append(c);
        }
        
        System.out.println("Your password is: " + passwordString.toString());
        
        scanner.close();
    }
}

