import java.time.LocalDate;
import java.util.List;

public class DailyLimit {

    public static int sentOn(List<LocalDate> history, LocalDate day) {
        return (int) history.stream().filter(day::equals).count();
    }

    public static int remaining(List<LocalDate> history, LocalDate day, int limit) {
        return Math.max(0, limit - sentOn(history, day));
    }
}
