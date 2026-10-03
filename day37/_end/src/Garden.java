import java.time.DayOfWeek;
import java.time.LocalDate;

// Draws a habit as a calendar: one row per weekday, one column per week, the newest week on the right.
public class Garden {

    static final String[] ROWS = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    public static String render(Habit habit, LocalDate today, int weeks) {
        LocalDate firstMonday = today.with(DayOfWeek.MONDAY).minusWeeks(weeks - 1);
        StringBuilder out = new StringBuilder();
        for (int row = 0; row < 7; row++) {
            out.append("     ").append(ROWS[row]).append(" ");
            for (int week = 0; week < weeks; week++) {
                LocalDate day = firstMonday.plusWeeks(week).plusDays(row);
                String cell = day.isAfter(today) ? " " : habit.isDone(day) ? "✿" : "·";
                out.append(' ').append(cell);
            }
            out.append(row < 6 ? "\n" : "");
        }
        return out.toString().replaceAll(" +\n", "\n").stripTrailing();
    }

    public static String summary(Habit habit, LocalDate today) {
        return String.format("%-16s streak %d · best %d · %d%% of the last 30 days",
                habit.name(), habit.currentStreak(today), habit.longestStreak(), habit.rate(today, 30));
    }
}
