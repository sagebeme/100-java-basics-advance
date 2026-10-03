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
    // (Good Friday and Easter Monday) merge into one.
    public static List<LongWeekend> longWeekends(List<Holiday> holidays) {
        List<LongWeekend> found = new ArrayList<>();
        List<Holiday> sorted = holidays.stream().sorted(Comparator.comparing(Holiday::date)).toList();
        for (Holiday h : sorted) {
            DayOfWeek dow = h.date().getDayOfWeek();
            LocalDate start, end;
            if (dow == DayOfWeek.FRIDAY) {
                start = h.date();
                end = h.date().plusDays(2);
            } else if (dow == DayOfWeek.MONDAY) {
                start = h.date().minusDays(2);
                end = h.date();
            } else {
                continue;
            }
            List<String> names = new ArrayList<>(List.of(h.name()));
            // also name any other holidays that fall inside this weekend (Boxing Day on the Saturday)
            for (Holiday other : sorted) {
                if (other != h && !other.date().isBefore(start) && !other.date().isAfter(end)) names.add(other.name());
            }
            LongWeekend weekend = new LongWeekend(start, end, names);
            if (!found.isEmpty() && !found.get(found.size() - 1).end().plusDays(1).isBefore(start)) {
                LongWeekend last = found.remove(found.size() - 1);
                List<String> merged = new ArrayList<>(last.holidays());
                for (String n : names) if (!merged.contains(n)) merged.add(n);
                weekend = new LongWeekend(last.start(), end.isAfter(last.end()) ? end : last.end(), merged);
            }
            found.add(weekend);
        }
        return found;
    }

    // The first long weekend that hasn't started yet, or null.
    public static LongWeekend next(List<Holiday> holidays, LocalDate today) {
        for (LongWeekend w : longWeekends(holidays)) {
            if (!w.start().isBefore(today)) return w;
        }
        return null;
    }
}
