import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

public record LongWeekend(LocalDate start, LocalDate end, List<String> holidays) {

    static final DateTimeFormatter NICE = DateTimeFormatter.ofPattern("EEE d MMM", Locale.ENGLISH);

    public long days() {
        return ChronoUnit.DAYS.between(start, end) + 1;
    }

    @Override
    public String toString() {
        return NICE.format(start) + " – " + NICE.format(end) + " (" + String.join(", ", holidays) + ")";
    }
}
