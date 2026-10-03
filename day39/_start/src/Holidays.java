import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Holidays {

    public static List<Holiday> read(Path csv) throws IOException {
        List<Holiday> holidays = new ArrayList<>();
        for (String line : Files.readAllLines(csv)) {
            if (line.isBlank() || line.startsWith("date,")) continue;
            String[] parts = line.split(",", 2);
            holidays.add(new Holiday(LocalDate.parse(parts[0].trim()), parts[1].trim()));
        }
        return holidays;
    }

    // A Friday holiday makes Fri–Sun; a Monday holiday makes Sat–Mon. Weekends that touch or overlap
    // (Good Friday and Easter Monday) merge into one. Each weekend lists every holiday inside it.
    public static List<LongWeekend> longWeekends(List<Holiday> holidays) {
        List<LongWeekend> found = new ArrayList<>();
        // TODO
        return found;
    }

    // The first long weekend that hasn't started yet, or null.
    public static LongWeekend next(List<Holiday> holidays, LocalDate today) {
        // TODO
        return null;
    }
}
