import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void ordersToTakeAway() {
        assertEquals("One medium latte to take away, please.", Order.orderSentence("medium", "Latte", true));
    }

    @Test
    void ordersToHaveHere() {
        assertEquals("One small americano to have here, please.", Order.orderSentence("Small", "Americano", false));
    }

    @Test
    void ordersOneDrink() {
        assertEquals("One cappuccino, please.", Order.orderFor(1, "Cappuccino"));
    }

    @Test
    void ordersForFriendsInWords() {
        assertEquals("Three flat whites, please.", Order.orderFor(3, "Flat White"));
        assertEquals("Five lattes, please.", Order.orderFor(5, "Latte"));
    }

    @Test
    void bigGroupsUseDigits() {
        assertEquals("7 espressos, please.", Order.orderFor(7, "Espresso"));
    }
}
