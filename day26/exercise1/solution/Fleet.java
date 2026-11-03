import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Fleet {

    public static Comparator<Matatu> byPlate() {
        return (a, b) -> a.getPlate().compareTo(b.getPlate());
    }

    public static Comparator<Matatu> mostSeatsFirst() {
        return Comparator.comparingInt(Matatu::getSeats).reversed();
    }

    public static Comparator<Matatu> bestRatedThenPlate() {
        return Comparator.comparingDouble(Matatu::getRating).reversed().thenComparing(byPlate());
    }

    public static Predicate<Matatu> onRoute(String route) {
        return m -> m.getRoute().equals(route);
    }

    public static Predicate<Matatu> atLeastSeats(int seats) {
        return m -> m.getSeats() >= seats;
    }

    public static Function<Matatu, String> label() {
        return m -> m.getPlate() + " (route " + m.getRoute() + ", " + m.getSeats() + " seats)";
    }

    // The matatus the test lets through, in the order given, each turned into text by the function.
    public static List<String> pick(List<Matatu> fleet, Predicate<Matatu> test, Function<Matatu, String> describe) {
        List<String> picked = new ArrayList<>();
        for (Matatu m : fleet) {
            if (test.test(m)) {
                picked.add(describe.apply(m));
            }
        }
        return picked;
    }
}
