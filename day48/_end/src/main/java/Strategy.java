import java.util.Comparator;
import java.util.List;
import java.util.Optional;

// What to buy next. Kept apart from Selenium, so it can be tested and compared in milliseconds.
public class Strategy {

    // The affordable upgrade that pays for itself fastest, if any.
    public static Optional<Upgrade> bestBuy(List<Upgrade> shop, long chapatis) {
        return shop.stream()
                .filter(u -> u.cost() <= chapatis)
                .min(Comparator.comparingDouble(Upgrade::paybackSeconds));
    }

    // Worth saving up instead? True when an upgrade we can't afford yet pays back so much faster
    // that waiting for it beats buying the best one we can afford now.
    public static boolean shouldSaveFor(List<Upgrade> shop, long chapatis, int ratePerSecond) {
        Optional<Upgrade> now = bestBuy(shop, chapatis);
        Optional<Upgrade> best = shop.stream().min(Comparator.comparingDouble(Upgrade::paybackSeconds));
        if (now.isEmpty() || best.isEmpty() || best.get().equals(now.get()) || ratePerSecond == 0) return false;
        double wait = (best.get().cost() - chapatis) / (double) ratePerSecond;
        return wait < now.get().paybackSeconds();
    }
}
