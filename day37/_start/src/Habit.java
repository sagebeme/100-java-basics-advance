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
        // TODO: refuse a blank name with IllegalArgumentException
        this.name = name;
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
        // TODO
        return 0;
    }

    public int longestStreak() {
        // TODO: walk the sorted dates and count runs of consecutive days
        return 0;
    }

    // Whole-number percentage of the last `days` days (ending today) that were done.
    public int rate(LocalDate today, int days) {
        // TODO
        return 0;
    }

    public List<LocalDate> sortedDays() {
        return new ArrayList<>(done);
    }
}
