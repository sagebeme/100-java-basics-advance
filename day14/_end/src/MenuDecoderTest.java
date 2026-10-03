import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuDecoderTest {

    @Test
    void noEspressoIsNotCoffee() {
        assertEquals("Not coffee", MenuDecoder.identify(0, 0, 200, 20));
    }

    @Test
    void espressoAloneOrWithWater() {
        assertEquals("Espresso", MenuDecoder.identify(30, 0, 0, 0));
        assertEquals("Americano", MenuDecoder.identify(30, 120, 0, 0));
    }

    @Test
    void foamWithoutMilkIsAMacchiato() {
        assertEquals("Macchiato", MenuDecoder.identify(30, 0, 0, 15));
    }

    @Test
    void lotsOfFoamIsACappuccinoEvenWithLotsOfMilk() {
        assertEquals("Cappuccino", MenuDecoder.identify(60, 0, 60, 60));
        assertEquals("Cappuccino", MenuDecoder.identify(30, 0, 160, 120)); // foam rule comes before the latte rule
    }

    @Test
    void mostlyMilkWithLittleFoamIsALatte() {
        assertEquals("Latte", MenuDecoder.identify(30, 0, 200, 10));
    }

    @Test
    void anythingElseWithMilkIsAFlatWhite() {
        assertEquals("Flat White", MenuDecoder.identify(60, 0, 100, 10));
    }

    @Test
    void explainsEveryDrinkInMenuOrder() {
        assertEquals(List.of("Espresso", "Americano", "Macchiato", "Cappuccino", "Latte", "Flat White"),
                List.copyOf(MenuDecoder.explanations().keySet()));
        for (String description : MenuDecoder.explanations().values()) {
            assertTrue(description.length() > 20, "every drink needs a real description");
        }
    }

    @Test
    void explainIgnoresCapitalsAndHandlesUnknownWords() {
        assertEquals(MenuDecoder.explanations().get("Flat White"), MenuDecoder.explain("flat white"));
        assertEquals("Not on this menu. Ask the barista!", MenuDecoder.explain("frappuccino"));
    }

    @Test
    void recommendsByTaste() {
        assertEquals("Flat White", MenuDecoder.recommend(true, true));
        assertEquals("Latte", MenuDecoder.recommend(true, false));
        assertEquals("Espresso", MenuDecoder.recommend(false, true));
        assertEquals("Americano", MenuDecoder.recommend(false, false));
    }
}
