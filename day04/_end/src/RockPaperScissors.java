import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to Rock Paper Scissors!");
        
        String[] choices = {"Rock", "Paper", "Scissors"};
        int playerScore = 0;
        int computerScore = 0;
        
        while (true) {
            System.out.println("\nWhat do you choose? Type 0 for Rock, 1 for Paper, or 2 for Scissors");
            int playerChoice = scanner.nextInt();
            
            if (playerChoice < 0 || playerChoice > 2) {
                System.out.println("Invalid choice. Please choose 0, 1, or 2.");
                continue;
            }
            
            int computerChoice = random.nextInt(3);
            
            System.out.println("You chose: " + choices[playerChoice]);
            System.out.println("Computer chose: " + choices[computerChoice]);
            
            // Determine winner
            if (playerChoice == computerChoice) {
                System.out.println("It's a draw!");
            } else if ((playerChoice == 0 && computerChoice == 2) ||
                      (playerChoice == 1 && computerChoice == 0) ||
                      (playerChoice == 2 && computerChoice == 1)) {
                System.out.println("You win!");
                playerScore++;
            } else {
                System.out.println("You lose!");
                computerScore++;
            }
            
            System.out.println("\nScore - You: " + playerScore + " | Computer: " + computerScore);
            System.out.println("Play again? (yes/no)");
            String playAgain = scanner.next().toLowerCase();
            
            if (!playAgain.equals("yes")) {
                break;
            }
        }
        
        System.out.println("\nFinal Score - You: " + playerScore + " | Computer: " + computerScore);
        System.out.println("Thanks for playing!");
        
        scanner.close();
    }
}

