import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlackjackTest {

    @Test
    void addsUpNonAceCards() {
        assertEquals(15, Blackjack.calculateScore(new ArrayList<>(Arrays.asList(5, 10))));
    }

    @Test
    void treatsCardsOverTenAsFaceCardsWorthTen() {
        assertEquals(20, Blackjack.calculateScore(new ArrayList<>(Arrays.asList(11, 11))));
    }

    @Test
    void countsAnAceAsElevenWhenItFitsUnderTwentyOne() {
        // Ace + 9 = 20, not 10
        assertEquals(20, Blackjack.calculateScore(new ArrayList<>(Arrays.asList(1, 9))));
    }

    @Test
    void downgradesAnAceToOneToAvoidBusting() {
        // Ace + King(11) + 5 would be 27 if the ace stayed at 11; it should drop to 1, giving 16.
        assertEquals(16, Blackjack.calculateScore(new ArrayList<>(Arrays.asList(1, 11, 5))));
    }

    @Test
    void dealtCardsAreAlwaysBetweenOneAndEleven() {
        for (int i = 0; i < 200; i++) {
            int card = Blackjack.dealCard();
            assertTrue(card >= 1 && card <= 11, "dealt card " + card + " out of the expected 1-11 range");
        }
    }
}
