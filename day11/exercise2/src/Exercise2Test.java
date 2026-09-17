import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void aceAndFaceCardIsABlackjack() {
        ArrayList<Integer> hand = new ArrayList<>(List.of(1, 11));
        assertTrue(Exercise2.isBlackjack(hand));
    }

    @Test
    void threeCardsCannotBeABlackjackEvenAt21() {
        ArrayList<Integer> hand = new ArrayList<>(List.of(7, 7, 7));
        assertFalse(Exercise2.isBlackjack(hand));
    }

    @Test
    void overTwentyOneIsABust() {
        ArrayList<Integer> hand = new ArrayList<>(List.of(10, 10, 5));
        assertTrue(Exercise2.isBust(hand));
    }

    @Test
    void anAceDowngradesToAvoidBusting() {
        ArrayList<Integer> hand = new ArrayList<>(List.of(1, 10, 5));
        assertFalse(Exercise2.isBust(hand));
    }
}
