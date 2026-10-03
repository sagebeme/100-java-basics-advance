import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

public class StreakGarden {

    static final Path FILE = Path.of("habits.json");
    static final DateTimeFormatter NICE = DateTimeFormatter.ofPattern("EEE d MMM", Locale.ENGLISH);

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Commands: add <habit> · done <habit> [yyyy-mm-dd] · show");
            return;
        }
        Map<String, Habit> habits = HabitStore.load(FILE);
        LocalDate today = LocalDate.now();

        switch (args[0]) {
            case "add" -> {
                if (args.length < 2) { System.out.println("Usage: add <habit>"); return; }
                habits.putIfAbsent(args[1].trim(), new Habit(args[1]));
                HabitStore.save(habits, FILE);
                System.out.println("🌱 Planted \"" + args[1].trim() + "\". Mark it done with: done \"" + args[1].trim() + "\"");
            }
            case "done" -> {
                if (args.length < 2) { System.out.println("Usage: done <habit> [yyyy-mm-dd]"); return; }
                Habit habit = habits.get(args[1].trim());
                if (habit == null) { System.out.println("No habit called \"" + args[1] + "\". Add it first."); return; }
                LocalDate day = args.length > 2 ? LocalDate.parse(args[2]) : today;
                if (day.isAfter(today)) { System.out.println("You can't do a habit in the future."); return; }
                habit.markDone(day);
                HabitStore.save(habits, FILE);
                System.out.println("✔ " + habit.name() + " done for " + NICE.format(day) + ". Streak: "
                        + habit.currentStreak(today) + " days 🔥");
            }
            case "show" -> {
                if (habits.isEmpty()) System.out.println("No habits yet. Start with: add \"Read 10 pages\"");
                for (Habit habit : habits.values()) {
                    System.out.println(Garden.summary(habit, today));
                    System.out.println(Garden.render(habit, today, 6));
                    System.out.println();
                }
            }
            default -> System.out.println("Unknown command \"" + args[0] + "\". Try add, done or show.");
        }
    }
}
