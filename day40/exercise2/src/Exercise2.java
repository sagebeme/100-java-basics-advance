public class Exercise2 {
    public static void main(String[] args) {
        SubscriptionList newsletter = new SubscriptionList();
        newsletter.subscribe("amina@example.com");
        System.out.println("Subscribed: " + newsletter.isSubscribed("amina@example.com"));
        newsletter.unsubscribe("amina@example.com");
        System.out.println("Subscribed: " + newsletter.isSubscribed("amina@example.com"));
    }
}
