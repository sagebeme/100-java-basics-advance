import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabitStatsTest {

    @Test
    void completionRateIsCompletedDaysOverTotalDays() {
        Habit habit = new Habit("Read");
        LocalDate start = LocalDate.of(2026, 1, 1);
        habit.markComplete(start);
        habit.markComplete(start.plusDays(1));
        // 2 of 4 days (Jan 1-4) completed
        assertEquals(0.5, HabitStats.completionRate(habit, start, start.plusDays(3)), 0.001);
    }

    @Test
    void aPerfectRangeIsFullCompletion() {
        Habit habit = new Habit("Read");
        LocalDate day = LocalDate.of(2026, 1, 1);
        habit.markComplete(day);
        assertEquals(1.0, HabitStats.completionRate(habit, day, day), 0.001);
    }

    @Test
    void longestStreakFindsTheBestRunEvenAfterAGap() {
        Habit habit = new Habit("Read");
        LocalDate start = LocalDate.of(2026, 1, 1);
        habit.markComplete(start);
        habit.markComplete(start.plusDays(1));
        habit.markComplete(start.plusDays(2));
        // gap at day 3
        habit.markComplete(start.plusDays(4));
        habit.markComplete(start.plusDays(5));

        assertEquals(3, HabitStats.longestStreak(habit));
    }

    @Test
    void aHabitWithNoHistoryHasNoStreak() {
        assertEquals(0, HabitStats.longestStreak(new Habit("Read")));
    }
}
