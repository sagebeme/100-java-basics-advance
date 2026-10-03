import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Habit {

    private final String name;
    private final TreeSet<LocalDate> done = new TreeSet<>();

    public Habit(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("A habit needs a name");
        this.name = name.trim();
    }

    public String name() {
        return name;
    }

    public void markDone(LocalDate day) {
        done.add(day);
    }

    public boolean isDone(LocalDate day) {
        return done.contains(day);
    }

    public Set<LocalDate> days() {
        return Collections.unmodifiableSet(done);
    }

    // Days in a row up to today. If today isn't done yet, the streak still counts up to yesterday.
    public int currentStreak(LocalDate today) {
        LocalDate day = done.contains(today) ? today : today.minusDays(1);
        int streak = 0;
        while (done.contains(day)) {
            streak++;
            day = day.minusDays(1);
        }
        return streak;
    }

    public int longestStreak() {
        int best = 0;
        int run = 0;
        LocalDate previous = null;
        for (LocalDate day : done) { // a TreeSet walks its dates in order
            run = (previous != null && previous.plusDays(1).equals(day)) ? run + 1 : 1;
            best = Math.max(best, run);
            previous = day;
        }
        return best;
    }

    // Whole-number percentage of the last `days` days (ending today) that were done.
    public int rate(LocalDate today, int days) {
        int count = 0;
        for (int i = 0; i < days; i++) {
            if (done.contains(today.minusDays(i))) count++;
        }
        return Math.round(count * 100f / days);
    }

    public List<LocalDate> sortedDays() {
        return new ArrayList<>(done);
    }
}
