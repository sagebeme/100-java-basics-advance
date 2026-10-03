import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class LeaveDays {

    public static long leaveDaysNeeded(LocalDate first, LocalDate last, Set<LocalDate> holidays) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        Set<LocalDate> holidays = Set.of(LocalDate.of(2026, 12, 25), LocalDate.of(2026, 12, 26));
        System.out.println(leaveDaysNeeded(LocalDate.of(2026, 12, 23), LocalDate.of(2026, 12, 27), holidays));
    }
}
