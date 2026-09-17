import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercise1 {

    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] RANKS = {
        "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"
    };

    public static List<String> createDeck() {
        List<String> deck = new ArrayList<>();
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck.add(rank + " of " + suit);
            }
        }
        return deck;
    }

    public static void shuffle(List<String> deck, Random random) {
        java.util.Collections.shuffle(deck, random);
    }

    /**
     * Removes and returns the top card of the deck.
     */
    public static String deal(List<String> deck) {
        return deck.remove(0);
    }

    public static void main(String[] args) {
        List<String> deck = createDeck();
        System.out.println("Deck size: " + deck.size());
        shuffle(deck, new Random());
        System.out.println("Dealt: " + deal(deck));
        System.out.println("Remaining: " + deck.size());
    }
}
