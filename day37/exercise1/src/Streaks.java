import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Streaks {

    public static int current(Set<LocalDate> done, LocalDate today) {
        // TODO
        return 0;
    }

    public static int longest(Set<LocalDate> done) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        Set<LocalDate> done = Set.of(LocalDate.of(2026, 9, 28), LocalDate.of(2026, 9, 29), LocalDate.of(2026, 9, 30),
                LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 3));
        System.out.println("current " + current(done, LocalDate.of(2026, 10, 3)) + ", longest " + longest(done));
    }
}
