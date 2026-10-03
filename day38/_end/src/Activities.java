import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Activities {

    // Approximate MET values from published exercise research: how hard each activity works the body.
    static final Map<String, Double> MET = new TreeMap<>(Map.of(
            "run", 9.8, "walk", 3.5, "cycle", 7.5, "skipping", 12.3,
            "football", 7.0, "swim", 6.0, "dance", 5.0, "gym", 5.0));

    static final double BODY_KG = 70;

    public static int kcal(String activity, int minutes, double bodyKg) {
        return (int) Math.round(MET.get(activity) * bodyKg * minutes / 60.0);
    }

    // "run 30, skipping 15" → two workouts for the given day.
    public static List<Workout> parse(String text, LocalDate day) {
        List<Workout> workouts = new ArrayList<>();
        for (String part : text.split(",")) {
            String[] words = part.trim().toLowerCase().split("\\s+");
            if (words.length != 2) {
                throw new IllegalArgumentException("Write each workout as \"<activity> <minutes>\", e.g. \"run 30\". Got: \"" + part.trim() + "\"");
            }
            String activity = words[0];
            if (!MET.containsKey(activity)) {
                throw new IllegalArgumentException("Unknown activity \"" + activity + "\". Try: " + String.join(", ", MET.keySet()));
            }
            int minutes;
            try {
                minutes = Integer.parseInt(words[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("\"" + words[1] + "\" isn't a number of minutes");
            }
            if (minutes <= 0 || minutes > 600) {
                throw new IllegalArgumentException("Minutes must be from 1 to 600, not " + minutes);
            }
            workouts.add(Workout.unsaved(day, activity, minutes, kcal(activity, minutes, BODY_KG)));
        }
        return workouts;
    }
}
