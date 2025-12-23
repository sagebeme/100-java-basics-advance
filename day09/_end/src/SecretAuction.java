import java.util.Scanner;
import java.util.HashMap;

public class SecretAuction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> bids = new HashMap<>();
        
        System.out.println("Welcome to the Secret Auction!");
        
        boolean moreBidders = true;
        while (moreBidders) {
            System.out.print("What is your name? ");
            String name = scanner.nextLine();
            
            System.out.print("What's your bid? $");
            int bid = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            bids.put(name, bid);
            
            System.out.print("Are there any other bidders? Type 'yes' or 'no': ");
            String response = scanner.nextLine().toLowerCase();
            moreBidders = response.equals("yes");
            
            // Clear screen (simplified - just print newlines)
            System.out.println("\n\n\n");
        }
        
        // Find highest bidder
        String winner = "";
        int maxBid = 0;
        for (String name : bids.keySet()) {
            if (bids.get(name) > maxBid) {
                maxBid = bids.get(name);
                winner = name;
            }
        }
        
        System.out.println("The winner is " + winner + " with a bid of $" + maxBid);
        
        scanner.close();
    }
}

