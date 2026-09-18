import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionListTest {

    @Test
    void subscribingAddsTheEmail() {
        SubscriptionList list = new SubscriptionList();
        list.subscribe("amina@example.com");
        assertTrue(list.isSubscribed("amina@example.com"));
    }

    @Test
    void unsubscribingRemovesTheEmail() {
        SubscriptionList list = new SubscriptionList();
        list.subscribe("amina@example.com");
        list.unsubscribe("amina@example.com");
        assertFalse(list.isSubscribed("amina@example.com"));
    }

    @Test
    void subscribingTwiceIsHarmless() {
        SubscriptionList list = new SubscriptionList();
        list.subscribe("amina@example.com");
        list.subscribe("amina@example.com");
        assertTrue(list.isSubscribed("amina@example.com"));
        assertTrue(list.getSubscribers().size() == 1);
    }
}
