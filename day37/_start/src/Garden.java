import java.time.DayOfWeek;
import java.time.LocalDate;

public class Garden {

    static final String[] ROWS = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    // Rows Mon..Sun, one column per week (newest on the right). Each row starts with 5 spaces, the day name
    // and a space; each cell is a space then ✿ (done), · (missed) or a space (after today). Trim the ends of rows.
    public static String render(Habit habit, LocalDate today, int weeks) {
        // TODO
        return "";
    }

    public static String summary(Habit habit, LocalDate today) {
        return String.format("%-16s streak %d · best %d · %d%% of the last 30 days",
                habit.name(), habit.currentStreak(today), habit.longestStreak(), habit.rate(today, 30));
    }
}
