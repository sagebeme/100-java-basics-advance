import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise1Test {

    @Test
    void aFreshDeckHasFiftyTwoCards() {
        assertEquals(52, Exercise1.createDeck().size());
    }

    @Test
    void everyCardInTheDeckIsUnique() {
        Set<String> uniqueCards = new HashSet<>(Exercise1.createDeck());
        assertEquals(52, uniqueCards.size());
    }

    @Test
    void dealingRemovesACardFromTheDeck() {
        List<String> deck = Exercise1.createDeck();
        String dealtCard = Exercise1.deal(deck);

        assertEquals(51, deck.size());
        assertTrue(Exercise1.createDeck().contains(dealtCard));
    }

    @Test
    void shufflingKeepsAllFiftyTwoCards() {
        List<String> deck = Exercise1.createDeck();
        Exercise1.shuffle(deck, new Random(42));
        assertEquals(52, deck.size());
        assertEquals(new HashSet<>(Exercise1.createDeck()), new HashSet<>(deck));
    }
}
