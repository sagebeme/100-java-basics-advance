import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class SecretAuction {

    /**
     * @return the name of the highest bidder, or "" if there are no bids.
     */
    public static String findWinner(Map<String, Integer> bids) {
        String winner = "";
        int maxBid = 0;
        for (Map.Entry<String, Integer> bid : bids.entrySet()) {
            if (bid.getValue() > maxBid) {
                maxBid = bid.getValue();
                winner = bid.getKey();
            }
        }
        return winner;
    }

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
        String winner = findWinner(bids);
        int maxBid = bids.getOrDefault(winner, 0);

        System.out.println("The winner is " + winner + " with a bid of $" + maxBid);

        scanner.close();
    }
}
