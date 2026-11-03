import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Till {

    public static int total(List<Payment> payments) {
        // TODO: one stream, no loop
        return -1;
    }

    // Each phone that paid at least this much in one go, once, in order.
    public static List<String> bigPayers(List<Payment> payments, int atLeast) {
        // TODO
        return List.of();
    }

    // How many payments came in each hour, hours in order.
    public static Map<Integer, Long> perHour(List<Payment> payments) {
        // TODO
        return new TreeMap<>();
    }

    public static Optional<Payment> largest(List<Payment> payments) {
        // TODO
        return Optional.empty();
    }

    // The M-Pesa codes one phone paid with, as "QK1, QK7".
    public static String codesFor(List<Payment> payments, String phone) {
        // TODO
        return "";
    }
}
