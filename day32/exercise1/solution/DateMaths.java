import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateMaths {

    static final DateTimeFormatter FRIENDLY = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH);

    public static int ageOn(LocalDate born, LocalDate day) {
        return Period.between(born, day).getYears();
    }

    public static long daysUntilNextBirthday(LocalDate born, LocalDate today) {
        LocalDate next = born.withYear(today.getYear()); // 29 Feb becomes 28 Feb in ordinary years
        if (next.isBefore(today)) {
            next = born.withYear(today.getYear() + 1);
        }
        return ChronoUnit.DAYS.between(today, next);
    }

    public static String friendly(LocalDate date) {
        return FRIENDLY.format(date);
    }

    public static void main(String[] args) {
        LocalDate born = LocalDate.of(1991, 10, 2);
        LocalDate today = LocalDate.now();
        System.out.println("Age: " + ageOn(born, today));
        System.out.println("Days to the next birthday: " + daysUntilNextBirthday(born, today));
        System.out.println("Today is " + friendly(today));
    }
}
