import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoffeeMachineTest {

    // The machine starts with 300ml water, 200ml milk, 100g coffee (see the fields at the top of CoffeeMachine).

    @Test
    void hasEnoughResourcesForAnEspresso() {
        assertTrue(CoffeeMachine.checkResources(50, 0, 18));
    }

    @Test
    void hasEnoughResourcesForALatte() {
        assertTrue(CoffeeMachine.checkResources(200, 150, 24));
    }

    @Test
    void refusesWhenThereIsNotEnoughWater() {
        assertFalse(CoffeeMachine.checkResources(1000, 0, 0));
    }

    @Test
    void refusesWhenThereIsNotEnoughMilk() {
        assertFalse(CoffeeMachine.checkResources(0, 1000, 0));
    }

    @Test
    void refusesWhenThereIsNotEnoughCoffee() {
        assertFalse(CoffeeMachine.checkResources(0, 0, 1000));
    }
}
