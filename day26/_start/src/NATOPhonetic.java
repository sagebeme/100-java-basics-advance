import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NATOPhonetic {
    private static final Map<Character, String> natoAlphabet = new HashMap<>();
    
    static {
        // TODO: Initialize NATO alphabet map
        // natoAlphabet.put('A', "Alpha");
        // natoAlphabet.put('B', "Bravo");
        // ... add all letters
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a word: ");
        String word = scanner.nextLine().toUpperCase();
        
        // TODO: Convert word to NATO phonetic using streams and lambdas
        // Use word.chars() to get stream of characters
        // Map each character to its NATO equivalent
        // Collect and display
        
        scanner.close();
    }
}

