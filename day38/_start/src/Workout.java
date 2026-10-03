import java.time.LocalDate;

public record Workout(long id, LocalDate day, String activity, int minutes, int kcal) {

    // A workout that hasn't been saved yet has no id.
    public static Workout unsaved(LocalDate day, String activity, int minutes, int kcal) {
        return new Workout(0, day, activity, minutes, kcal);
    }
}
