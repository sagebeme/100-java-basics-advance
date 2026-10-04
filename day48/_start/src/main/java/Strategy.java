import java.util.Comparator;
import java.util.List;
import java.util.Optional;

// What to buy next. Kept apart from Selenium, so it can be tested and compared in milliseconds.
public class Strategy {

    // The affordable upgrade that pays for itself fastest, if any.
    public static Optional<Upgrade> bestBuy(List<Upgrade> shop, long chapatis) {
        // TODO: affordable upgrades only; the lowest paybackSeconds()
        return Optional.empty();
    }

    // Worth saving up instead? True when an upgrade we can't afford yet pays back so much faster
    // that waiting for it beats buying the best one we can afford now.
    public static boolean shouldSaveFor(List<Upgrade> shop, long chapatis, int ratePerSecond) {
        // TODO: true when the best deal overall isn't affordable yet, and the seconds needed to save for it
        //       ((its cost - chapatis) / ratePerSecond) are fewer than the affordable best buy's payback time.
        //       False when there's no income, nothing affordable, or the best is already affordable.
        return false;
    }
}
