import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Fleet {

    public static Comparator<Matatu> byPlate() {
        // TODO: a lambda (a, b) -> ...
        return (a, b) -> 0;
    }

    public static Comparator<Matatu> mostSeatsFirst() {
        // TODO
        return (a, b) -> 0;
    }

    public static Comparator<Matatu> bestRatedThenPlate() {
        // TODO: highest rating first; equal ratings by plate
        return (a, b) -> 0;
    }

    public static Predicate<Matatu> onRoute(String route) {
        // TODO
        return m -> false;
    }

    public static Predicate<Matatu> atLeastSeats(int seats) {
        // TODO
        return m -> false;
    }

    public static Function<Matatu, String> label() {
        // TODO: "KDA 123A (route 46, 33 seats)"
        return m -> "";
    }

    // The matatus the test lets through, in the order given, each turned into text by the function.
    public static List<String> pick(List<Matatu> fleet, Predicate<Matatu> test, Function<Matatu, String> describe) {
        // TODO: a plain loop that calls test.test(m) and describe.apply(m)
        return new ArrayList<>();
    }
}
