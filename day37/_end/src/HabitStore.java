import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

// Saves habits as {"Java practice": ["2026-10-01", "2026-10-02"], ...}
public class HabitStore {

    public static Map<String, Habit> load(Path file) throws IOException {
        Map<String, Habit> habits = new LinkedHashMap<>();
        if (!Files.exists(file)) return habits;
        JSONObject root = new JSONObject(Files.readString(file));
        for (String name : root.keySet()) {
            Habit habit = new Habit(name);
            JSONArray days = root.getJSONArray(name);
            for (int i = 0; i < days.length(); i++) {
                habit.markDone(LocalDate.parse(days.getString(i)));
            }
            habits.put(habit.name(), habit);
        }
        return habits;
    }

    public static void save(Map<String, Habit> habits, Path file) throws IOException {
        JSONObject root = new JSONObject();
        for (Habit habit : habits.values()) {
            JSONArray days = new JSONArray();
            for (LocalDate day : habit.sortedDays()) days.put(day.toString());
            root.put(habit.name(), days);
        }
        Files.writeString(file, root.toString(2));
    }
}
