import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HabitTest {

    @Test
    void marksADateAsComplete() {
        Habit habit = new Habit("Read");
        LocalDate today = LocalDate.of(2026, 1, 10);
        habit.markComplete(today);
        assertTrue(habit.isCompletedOn(today));
    }

    @Test
    void aDateNeverMarkedIsNotComplete() {
        Habit habit = new Habit("Read");
        assertFalse(habit.isCompletedOn(LocalDate.of(2026, 1, 10)));
    }

    @Test
    void streakCountsConsecutiveDaysBackFromToday() {
        Habit habit = new Habit("Read");
        LocalDate today = LocalDate.of(2026, 1, 10);
        habit.markComplete(today);
        habit.markComplete(today.minusDays(1));
        habit.markComplete(today.minusDays(2));
        assertEquals(3, habit.currentStreak(today));
    }

    @Test
    void streakStopsAtTheFirstMissedDay() {
        Habit habit = new Habit("Read");
        LocalDate today = LocalDate.of(2026, 1, 10);
        habit.markComplete(today);
        habit.markComplete(today.minusDays(2)); // gap at minusDays(1)
        assertEquals(1, habit.currentStreak(today));
    }

    @Test
    void markingTheSameDateTwiceDoesNotDuplicateHistory() {
        Habit habit = new Habit("Read");
        LocalDate today = LocalDate.of(2026, 1, 10);
        habit.markComplete(today);
        habit.markComplete(today);
        assertEquals(1, habit.getHistory().size());
    }
}
