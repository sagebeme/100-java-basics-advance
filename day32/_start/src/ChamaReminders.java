import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChamaReminders {

    static final int DAYS_NOTICE = 3;
    static final DateTimeFormatter NICE_DATE = DateTimeFormatter.ofPattern("EEE d MMM", Locale.ENGLISH);

    public static List<Member> readMembers(Path csv) throws IOException {
        List<Member> members = new ArrayList<>();
        // TODO: read every line, skip blanks and the "name,..." header, and add Member.fromCsvLine(line)
        return members;
    }

    public static boolean isBirthday(Member member, LocalDate today) {
        // TODO: same month and day. A 29 Feb birthday counts on 28 Feb when today's year isn't a leap year.
        return false;
    }

    // This month's due date, or next month's if this month's has already passed.
    public static LocalDate contributionDate(Member member, LocalDate today) {
        // TODO: a contribution day of 31 in a 30-day month means the 30th (use lengthOfMonth())
        return today;
    }

    public static List<Email> remindersFor(List<Member> members, LocalDate today) {
        List<Email> emails = new ArrayList<>();
        // TODO: "Happy birthday, <name>!" for each birthday today
        // TODO: "Your chama contribution is due on <Mon 5 Oct>" when the due date is exactly DAYS_NOTICE days away
        return emails;
    }

    public static void main(String[] args) throws IOException {
        Path csv = Path.of(args.length > 0 ? args[0] : "members.csv");
        LocalDate today = args.length > 1 ? LocalDate.parse(args[1]) : LocalDate.now();

        // TODO: work out the reminders, send each one through an OutboxMailer, and print what was sent
    }
}
