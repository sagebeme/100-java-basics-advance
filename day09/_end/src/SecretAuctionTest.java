import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecretAuctionTest {

    @Test
    void theHighestBidderWins() {
        Map<String, Integer> bids = new HashMap<>();
        bids.put("Wakanda Forever", 250);
        bids.put("Black Panther", 120);
        bids.put("Storm", 300);

        assertEquals("Storm", SecretAuction.findWinner(bids));
    }

    @Test
    void aSingleBidderWinsByDefault() {
        Map<String, Integer> bids = new HashMap<>();
        bids.put("T'Challa", 50);

        assertEquals("T'Challa", SecretAuction.findWinner(bids));
    }

    @Test
    void noBidsMeansNoWinner() {
        assertEquals("", SecretAuction.findWinner(new HashMap<>()));
    }
}
