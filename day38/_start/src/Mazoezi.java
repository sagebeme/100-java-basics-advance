import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Mazoezi {

    static final DateTimeFormatter NICE = DateTimeFormatter.ofPattern("EEE d MMM", Locale.ENGLISH);

    public static void main(String[] args) throws SQLException {
        if (args.length == 0) {
            System.out.println("Commands: log \"run 30, skipping 15\" · today · week");
            return;
        }
        LocalDate today = LocalDate.now();
        try (WorkoutDb db = new WorkoutDb("jdbc:h2:./mazoezi")) {
            db.createTable();
            switch (args[0]) {
                case "log" -> {
                    if (args.length < 2) { System.out.println("Usage: log \"run 30, skipping 15\""); return; }
                    List<String> saved = new ArrayList<>();
                    for (Workout w : Activities.parse(args[1], today)) {
                        db.add(w);
                        saved.add(w.activity() + " " + w.minutes() + " min (" + w.kcal() + " kcal)");
                    }
                    System.out.println("Logged " + saved.size() + " workouts for " + NICE.format(today) + ": " + String.join(", ", saved));
                }
                case "today" -> {
                    List<Workout> workouts = db.forDay(today);
                    if (workouts.isEmpty()) System.out.println("Nothing logged today. A 20-minute walk counts!");
                    for (Workout w : workouts) {
                        System.out.printf("  #%d %-10s %3d min %5d kcal%n", w.id(), w.activity(), w.minutes(), w.kcal());
                    }
                }
                case "week" -> {
                    LocalDate monday = today.with(DayOfWeek.MONDAY);
                    LocalDate sunday = monday.plusDays(6);
                    System.out.println("This week (" + NICE.format(monday) + " – " + NICE.format(sunday) + ")");
                    int minutes = 0, kcal = 0;
                    for (ActivityTotal t : db.totalsBetween(monday, sunday)) {
                        System.out.printf("  %-10s %4d min %5d kcal%n", t.activity(), t.minutes(), t.kcal());
                        minutes += t.minutes();
                        kcal += t.kcal();
                    }
                    System.out.printf("  %-10s %4d min %5d kcal%n", "total", minutes, kcal);
                }
                default -> System.out.println("Unknown command \"" + args[0] + "\". Try log, today or week.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
