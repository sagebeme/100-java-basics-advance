import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobMatcherTest {

    static final List<Job> JOBS = List.of(
            new Job("K-201", "Junior Java Developer", "Mfuko", "Nairobi", false, List.of("java", "sql", "git")),
            new Job("K-202", "Backend Engineer", "Shamba", "Remote", true, List.of("java", "spring", "postgresql", "docker")),
            new Job("K-203", "Frontend Developer", "Duka", "Mombasa", false, List.of("javascript", "react", "css")),
            new Job("K-205", "QA Automation Tester", "Tamasha", "Remote", true, List.of("java", "selenium", "junit")));

    static Applicant me() {
        return new Applicant("Wanjiru Kamau", "w@example.com", "0712345678", 1, Set.of("java", "sql", "git", "selenium", "junit"),
                List.of("Nairobi"), Path.of("cv.txt"), "Dear {{company}}, I'd like the {{title}} job. {{name}}");
    }

    @Test
    void scoresTheShareOfSkillsYouHave() {
        assertEquals(100, JobMatcher.score(JOBS.get(0), me().skills()));
        assertEquals(25, JobMatcher.score(JOBS.get(1), me().skills()));
        assertEquals(0, JobMatcher.score(JOBS.get(2), me().skills()));
    }

    @Test
    void keepsGoodMatchesInYourPlacesOrRemoteBestFirst() {
        assertEquals(List.of("K-201", "K-205"), JobMatcher.worthApplying(JOBS, me(), 60, Set.of()).stream().map(Job::id).toList());
    }

    @Test
    void neverAppliesTwice() {
        assertEquals(List.of("K-205"), JobMatcher.worthApplying(JOBS, me(), 60, Set.of("K-201")).stream().map(Job::id).toList());
    }

    @Test
    void personalisesTheLetter() {
        assertEquals("Dear Mfuko, I'd like the Junior Java Developer job. Wanjiru Kamau", JobMatcher.letterFor(JOBS.get(0), me()));
    }

    @Test
    void theLedgerRemembersApplications() throws Exception {
        Path file = Files.createTempFile("applied", ".csv");
        Files.delete(file);
        Ledger ledger = new Ledger(file);
        assertEquals(Set.of(), ledger.appliedJobIds());
        ledger.record(LocalDate.of(2026, 10, 4), "K-201", "K-201-6");
        assertEquals(Set.of("K-201"), new Ledger(file).appliedJobIds());
        Files.delete(file);
    }

    @Test
    void mapsYearsToTheFormsChoices() {
        assertEquals(0, ApplyPage.bucket(0));
        assertEquals(1, ApplyPage.bucket(2));
        assertEquals(3, ApplyPage.bucket(5));
        assertEquals(6, ApplyPage.bucket(10));
    }
}
