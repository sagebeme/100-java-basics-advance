import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        String[] wordList = {
            "java", "python", "computer", "programming",
            "algorithm", "software", "developer"
        };
        
        // Your code here
        
        scanner.close();
    }
}

