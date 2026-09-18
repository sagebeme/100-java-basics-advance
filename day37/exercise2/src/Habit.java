import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Habit {
    private final String name;
    private final List<LocalDate> completions = new ArrayList<>();

    public Habit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void markComplete(LocalDate date) {
        if (!completions.contains(date)) {
            completions.add(date);
        }
    }

    public boolean isCompletedOn(LocalDate date) {
        return completions.contains(date);
    }

    public List<LocalDate> getHistory() {
        return completions;
    }

    /**
     * The number of consecutive days, counting back from today, that are marked complete.
     */
    public int currentStreak(LocalDate today) {
        int streak = 0;
        LocalDate day = today;
        while (isCompletedOn(day)) {
            streak++;
            day = day.minusDays(1);
        }
        return streak;
    }
}
