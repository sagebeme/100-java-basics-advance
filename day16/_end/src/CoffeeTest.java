import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoffeeTest {

    @Test
    void storesTheRecipeItWasGiven() {
        Coffee espresso = new Coffee("espresso", 50, 0, 18, 1.50);

        assertEquals("espresso", espresso.getName());
        assertEquals(50, espresso.getWater());
        assertEquals(0, espresso.getMilk());
        assertEquals(18, espresso.getCoffee());
        assertEquals(1.50, espresso.getCost());
    }
}
