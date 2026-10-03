import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Streaks {

    public static int current(Set<LocalDate> done, LocalDate today) {
        LocalDate day = done.contains(today) ? today : today.minusDays(1);
        int streak = 0;
        while (done.contains(day)) {
            streak++;
            day = day.minusDays(1);
        }
        return streak;
    }

    public static int longest(Set<LocalDate> done) {
        int best = 0;
        int run = 0;
        LocalDate previous = null;
        for (LocalDate day : new TreeSet<>(done)) {
            run = previous != null && previous.plusDays(1).equals(day) ? run + 1 : 1;
            best = Math.max(best, run);
            previous = day;
        }
        return best;
    }

    public static void main(String[] args) {
        Set<LocalDate> done = Set.of(LocalDate.of(2026, 9, 28), LocalDate.of(2026, 9, 29), LocalDate.of(2026, 9, 30),
                LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 3));
        System.out.println("current " + current(done, LocalDate.of(2026, 10, 3)) + ", longest " + longest(done));
    }
}
