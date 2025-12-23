import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Choose a difficulty. Type 'easy' or 'hard': ");
        String difficulty = scanner.nextLine().toLowerCase();
        
        int attempts = difficulty.equals("easy") ? 10 : 5;
        int number = random.nextInt(100) + 1;
        
        System.out.println("You have " + attempts + " attempts remaining to guess the number.");
        
        while (attempts > 0) {
            System.out.print("Make a guess: ");
            int guess = scanner.nextInt();
            
            if (guess == number) {
                System.out.println("You got it! The answer was " + number);
                return;
            } else if (guess > number) {
                System.out.println("Too high.");
            } else {
                System.out.println("Too low.");
            }
            
            attempts--;
            if (attempts > 0) {
                System.out.println("You have " + attempts + " attempts remaining to guess the number.");
            }
        }
        
        System.out.println("You've run out of guesses. The number was " + number);
        
        scanner.close();
    }
}

