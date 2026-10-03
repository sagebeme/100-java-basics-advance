import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class HabitStats {

    public static int rate(Set<LocalDate> done, LocalDate today, int days) {
        int count = 0;
        for (int i = 0; i < days; i++) {
            if (done.contains(today.minusDays(i))) count++;
        }
        return Math.round(count * 100f / days);
    }

    public static DayOfWeek bestWeekday(Set<LocalDate> done) {
        if (done.isEmpty()) return null;
        int[] counts = new int[7];
        for (LocalDate day : done) {
            counts[day.getDayOfWeek().getValue() - 1]++;
        }
        DayOfWeek best = DayOfWeek.MONDAY;
        for (DayOfWeek d : DayOfWeek.values()) {
            if (counts[d.getValue() - 1] > counts[best.getValue() - 1]) best = d; // strictly more: earlier day wins ties
        }
        return best;
    }

    public static void main(String[] args) {
        Set<LocalDate> done = Set.of(LocalDate.of(2026, 10, 3), LocalDate.of(2026, 9, 26), LocalDate.of(2026, 9, 28));
        System.out.println(rate(done, LocalDate.of(2026, 10, 3), 7) + "% · best on " + bestWeekday(done));
    }
}
