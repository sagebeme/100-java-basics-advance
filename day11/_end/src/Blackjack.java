import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Blackjack {
    private static Random random = new Random();
    
    public static int dealCard() {
        return random.nextInt(11) + 1; // 1-11
    }
    
    public static int calculateScore(ArrayList<Integer> cards) {
        int score = 0;
        int aces = 0;
        
        for (int card : cards) {
            if (card == 1) {
                aces++;
                score += 11;
            } else if (card > 10) {
                score += 10;
            } else {
                score += card;
            }
        }
        
        // Adjust for aces
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
        
        return score;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Integer> playerCards = new ArrayList<>();
        ArrayList<Integer> dealerCards = new ArrayList<>();
        
        // Deal initial cards
        playerCards.add(dealCard());
        playerCards.add(dealCard());
        dealerCards.add(dealCard());
        dealerCards.add(dealCard());
        
        System.out.println("Your cards: " + playerCards + ", current score: " + calculateScore(playerCards));
        System.out.println("Dealer's first card: " + dealerCards.get(0));
        
        // Player's turn
        boolean playerBust = false;
        while (true) {
            System.out.print("Type 'y' to get another card, type 'n' to pass: ");
            String choice = scanner.nextLine().toLowerCase();
            
            if (choice.equals("n")) {
                break;
            }
            
            playerCards.add(dealCard());
            int playerScore = calculateScore(playerCards);
            System.out.println("Your cards: " + playerCards + ", current score: " + playerScore);
            
            if (playerScore > 21) {
                System.out.println("You went over. You lose!");
                playerBust = true;
                break;
            }
        }
        
        if (!playerBust) {
            // Dealer's turn
            while (calculateScore(dealerCards) < 17) {
                dealerCards.add(dealCard());
            }
            
            int dealerScore = calculateScore(dealerCards);
            int playerScore = calculateScore(playerCards);
            
            System.out.println("Your final hand: " + playerCards + ", final score: " + playerScore);
            System.out.println("Dealer's final hand: " + dealerCards + ", final score: " + dealerScore);
            
            if (dealerScore > 21) {
                System.out.println("Dealer went over. You win!");
            } else if (dealerScore > playerScore) {
                System.out.println("You lose!");
            } else if (dealerScore < playerScore) {
                System.out.println("You win!");
            } else {
                System.out.println("It's a draw!");
            }
        }
        
        scanner.close();
    }
}

