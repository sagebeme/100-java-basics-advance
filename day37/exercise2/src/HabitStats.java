import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class HabitStats {

    public static int rate(Set<LocalDate> done, LocalDate today, int days) {
        // TODO
        return 0;
    }

    public static DayOfWeek bestWeekday(Set<LocalDate> done) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        Set<LocalDate> done = Set.of(LocalDate.of(2026, 10, 3), LocalDate.of(2026, 9, 26), LocalDate.of(2026, 9, 28));
        System.out.println(rate(done, LocalDate.of(2026, 10, 3), 7) + "% · best on " + bestWeekday(done));
    }
}
