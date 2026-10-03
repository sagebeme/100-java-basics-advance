import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChamaRemindersTest {

    private static Member member(String birthday, int contributionDay) {
        return new Member("Wanjiku", "wanjiku@example.com", LocalDate.parse(birthday), contributionDay);
    }

    @Test
    void readsAMemberFromACsvLine() {
        Member m = Member.fromCsvLine("Otieno, otieno@example.com, 1985-03-17, 5");
        assertEquals("Otieno", m.name());
        assertEquals(LocalDate.of(1985, 3, 17), m.birthday());
        assertEquals(5, m.contributionDay());
    }

    @Test
    void refusesALineWithMissingColumns() {
        assertThrows(IllegalArgumentException.class, () -> Member.fromCsvLine("Otieno,otieno@example.com"));
    }

    @Test
    void aBirthdayMatchesMonthAndDayInAnyYear() {
        assertTrue(ChamaReminders.isBirthday(member("1991-10-02", 1), LocalDate.of(2026, 10, 2)));
        assertFalse(ChamaReminders.isBirthday(member("1991-10-02", 1), LocalDate.of(2026, 10, 3)));
    }

    @Test
    void leapDayBabiesCelebrateOnThe28thInOrdinaryYears() {
        Member leap = member("1996-02-29", 1);
        assertTrue(ChamaReminders.isBirthday(leap, LocalDate.of(2027, 2, 28)));
        assertFalse(ChamaReminders.isBirthday(leap, LocalDate.of(2028, 2, 28)));
        assertTrue(ChamaReminders.isBirthday(leap, LocalDate.of(2028, 2, 29)));
    }

    @Test
    void the31stBecomesTheLastDayOfAShortMonth() {
        assertEquals(LocalDate.of(2026, 9, 30), ChamaReminders.contributionDate(member("1990-01-01", 31), LocalDate.of(2026, 9, 10)));
        assertEquals(LocalDate.of(2027, 2, 28), ChamaReminders.contributionDate(member("1990-01-01", 31), LocalDate.of(2027, 2, 1)));
    }

    @Test
    void aPassedDueDateRollsOverToNextMonth() {
        assertEquals(LocalDate.of(2026, 11, 3), ChamaReminders.contributionDate(member("1990-01-01", 3), LocalDate.of(2026, 10, 30)));
    }

    @Test
    void remindsThreeDaysBeforeTheDueDateEvenAcrossAMonthEnd() {
        List<Email> emails = ChamaReminders.remindersFor(List.of(member("1990-01-01", 2)), LocalDate.of(2026, 9, 29));
        assertEquals(1, emails.size());
        assertEquals("Your chama contribution is due on Fri 2 Oct", emails.get(0).subject());
    }

    @Test
    void sendsBirthdaysAndRemindersTogether() {
        Member birthdayAndDue = member("1991-10-02", 5);
        Member nothingToday = new Member("Kamau", "kamau@example.com", LocalDate.of(1979, 12, 25), 20);
        List<Email> emails = ChamaReminders.remindersFor(List.of(birthdayAndDue, nothingToday), LocalDate.of(2026, 10, 2));
        assertEquals(List.of("Happy birthday, Wanjiku!", "Your chama contribution is due on Mon 5 Oct"),
                emails.stream().map(Email::subject).toList());
    }

    @Test
    void anyMailerWorksBecauseRemindersOnlySeeTheInterface() {
        List<Email> received = new ArrayList<>();
        Mailer recording = received::add; // a fake Mailer in one line
        for (Email e : ChamaReminders.remindersFor(List.of(member("1991-10-02", 20)), LocalDate.of(2026, 10, 2))) {
            recording.send(e);
        }
        assertEquals("wanjiku@example.com", received.get(0).to());
    }
}
