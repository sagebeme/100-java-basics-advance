import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Till {

    public static int total(List<Payment> payments) {
        return payments.stream().mapToInt(Payment::getShillings).sum();
    }

    // Each phone that paid at least this much in one go, once, in order.
    public static List<String> bigPayers(List<Payment> payments, int atLeast) {
        return payments.stream()
                .filter(p -> p.getShillings() >= atLeast)
                .map(Payment::getPhone)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // How many payments came in each hour, hours in order.
    public static Map<Integer, Long> perHour(List<Payment> payments) {
        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getHour, TreeMap::new, Collectors.counting()));
    }

    public static Optional<Payment> largest(List<Payment> payments) {
        return payments.stream().max(Comparator.comparingInt(Payment::getShillings));
    }

    // The M-Pesa codes one phone paid with, as "QK1, QK7".
    public static String codesFor(List<Payment> payments, String phone) {
        return payments.stream()
                .filter(p -> p.getPhone().equals(phone))
                .map(Payment::getCode)
                .collect(Collectors.joining(", "));
    }
}
