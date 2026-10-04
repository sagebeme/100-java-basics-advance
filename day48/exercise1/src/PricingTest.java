import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingTest {

    @Test
    void eachOneCostsFifteenPercentMore() {
        assertEquals(15, Pricing.costAfter(15, 0));
        assertEquals(17, Pricing.costAfter(15, 1));
        assertEquals(61, Pricing.costAfter(15, 10));
        assertEquals(100, Pricing.costAfter(100, 0));
    }

    @Test
    void buyingSeveralAddsTheRisingPrices() {
        assertEquals(52, Pricing.totalCost(15, 0, 3));
        assertEquals(0, Pricing.totalCost(15, 4, 0));
        assertEquals(Pricing.costAfter(15, 4), Pricing.totalCost(15, 4, 1));
    }
}
