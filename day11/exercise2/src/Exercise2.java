import java.util.ArrayList;

public class Exercise2 {

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
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
        return score;
    }

    public static boolean isBlackjack(ArrayList<Integer> cards) {
        return cards.size() == 2 && calculateScore(cards) == 21;
    }

    public static boolean isBust(ArrayList<Integer> cards) {
        return calculateScore(cards) > 21;
    }

    public static void main(String[] args) {
        ArrayList<Integer> hand = new ArrayList<>(java.util.List.of(1, 11));
        System.out.println("Score: " + calculateScore(hand));
        System.out.println("Blackjack: " + isBlackjack(hand));
        System.out.println("Bust: " + isBust(hand));
    }
}
