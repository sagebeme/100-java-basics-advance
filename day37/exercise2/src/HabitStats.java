import java.time.LocalDate;
import java.util.List;

public class HabitStats {

    public static double completionRate(Habit habit, LocalDate start, LocalDate end) {
        int totalDays = (int) (java.time.temporal.ChronoUnit.DAYS.between(start, end)) + 1;
        int completed = 0;
        for (LocalDate day = start; !day.isAfter(end); day = day.plusDays(1)) {
            if (habit.isCompletedOn(day)) completed++;
        }
        return (double) completed / totalDays;
    }

    /**
     * The longest run of consecutive completed days found anywhere in the habit's history.
     */
    public static int longestStreak(Habit habit) {
        List<LocalDate> history = habit.getHistory().stream().sorted().toList();
        if (history.isEmpty()) return 0;

        int longest = 1;
        int current = 1;
        for (int i = 1; i < history.size(); i++) {
            if (history.get(i).equals(history.get(i - 1).plusDays(1))) {
                current++;
                longest = Math.max(longest, current);
            } else {
                current = 1;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        Habit habit = new Habit("Read");
        LocalDate start = LocalDate.of(2026, 1, 1);
        habit.markComplete(start);
        habit.markComplete(start.plusDays(1));
        habit.markComplete(start.plusDays(3));

        System.out.println("Completion rate: " + completionRate(habit, start, start.plusDays(3)));
        System.out.println("Longest streak: " + longestStreak(habit));
    }
}
