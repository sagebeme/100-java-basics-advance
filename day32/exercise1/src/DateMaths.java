import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateMaths {

    public static int ageOn(LocalDate born, LocalDate day) {
        // TODO
        return 0;
    }

    public static long daysUntilNextBirthday(LocalDate born, LocalDate today) {
        // TODO
        return 0;
    }

    // "Friday, 2 October 2026"
    public static String friendly(LocalDate date) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        LocalDate born = LocalDate.of(1991, 10, 2);
        LocalDate today = LocalDate.now();
        System.out.println("Age: " + ageOn(born, today));
        System.out.println("Days to the next birthday: " + daysUntilNextBirthday(born, today));
        System.out.println("Today is " + friendly(today));
    }
}
