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
        for (String line : Files.readAllLines(csv)) {
            if (line.isBlank() || line.startsWith("name,")) continue; // skip blanks and the header row
            members.add(Member.fromCsvLine(line));
        }
        return members;
    }

    public static boolean isBirthday(Member member, LocalDate today) {
        int month = member.birthday().getMonthValue();
        int day = member.birthday().getDayOfMonth();
        if (month == 2 && day == 29 && !today.isLeapYear()) {
            day = 28; // leap-day babies celebrate on the 28th in ordinary years
        }
        return today.getMonthValue() == month && today.getDayOfMonth() == day;
    }

    // This month's due date, or next month's if this month's has already passed.
    public static LocalDate contributionDate(Member member, LocalDate today) {
        LocalDate due = dueIn(today.withDayOfMonth(1), member.contributionDay());
        if (due.isBefore(today)) {
            due = dueIn(today.withDayOfMonth(1).plusMonths(1), member.contributionDay());
        }
        return due;
    }

    private static LocalDate dueIn(LocalDate firstOfMonth, int day) {
        return firstOfMonth.withDayOfMonth(Math.min(day, firstOfMonth.lengthOfMonth()));
    }

    public static List<Email> remindersFor(List<Member> members, LocalDate today) {
        List<Email> emails = new ArrayList<>();
        for (Member m : members) {
            if (isBirthday(m, today)) {
                emails.add(new Email(m.email(), "Happy birthday, " + m.name() + "!",
                        "Happy birthday, " + m.name() + "! From all of us in the chama, have a wonderful day."));
            }
            LocalDate due = contributionDate(m, today);
            if (ChronoUnit.DAYS.between(today, due) == DAYS_NOTICE) {
                emails.add(new Email(m.email(), "Your chama contribution is due on " + NICE_DATE.format(due),
                        "Hi " + m.name() + ", a friendly reminder that your monthly contribution is due on "
                                + NICE_DATE.format(due) + ". Asante!"));
            }
        }
        return emails;
    }

    public static void main(String[] args) throws IOException {
        Path csv = Path.of(args.length > 0 ? args[0] : "members.csv");
        LocalDate today = args.length > 1 ? LocalDate.parse(args[1]) : LocalDate.now();

        List<Email> emails = remindersFor(readMembers(csv), today);
        Mailer mailer = new OutboxMailer(Path.of("outbox"));
        for (Email email : emails) {
            mailer.send(email);
        }

        System.out.println("Sent " + emails.size() + " emails (saved in outbox/):");
        for (Email email : emails) {
            System.out.println("  to " + email.to() + ": " + email.subject());
        }
    }
}
