import java.time.LocalDate;

public class Exercise1 {
    public static void main(String[] args) {
        Habit habit = new Habit("Read for 20 minutes");
        LocalDate today = LocalDate.now();
        habit.markComplete(today);
        habit.markComplete(today.minusDays(1));
        System.out.println(habit.getName() + " streak: " + habit.currentStreak(today));
    }
}
