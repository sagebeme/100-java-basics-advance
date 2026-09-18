import java.util.HashSet;
import java.util.Set;

public class SubscriptionList {
    private final Set<String> subscribers = new HashSet<>();

    public void subscribe(String email) {
        subscribers.add(email);
    }

    public void unsubscribe(String email) {
        subscribers.remove(email);
    }

    public boolean isSubscribed(String email) {
        return subscribers.contains(email);
    }

    public Set<String> getSubscribers() {
        return subscribers;
    }
}
