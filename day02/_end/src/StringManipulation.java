import java.util.Scanner;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        
        // Convert to uppercase
        String upper = sentence.toUpperCase();
        
        // Convert to lowercase
        String lower = sentence.toLowerCase();
        
        // Count characters
        int length = sentence.length();
        
        // Find first word (use indexOf and substring)
        String firstWord = "";
        if (sentence.contains(" ")) {
            firstWord = sentence.substring(0, sentence.indexOf(" "));
        } else {
            firstWord = sentence;
        }
        
        // Print all results
        System.out.println("Original: " + sentence);
        System.out.println("Uppercase: " + upper);
        System.out.println("Lowercase: " + lower);
        System.out.println("Character count: " + length);
        System.out.println("First word: " + firstWord);
        
        scanner.close();
    }
}

