import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebDriver;

public class KaziBot {

    static final int MAX_PER_RUN = 3; // apply carefully, not to everything

    public static void main(String[] args) throws Exception {
        Path cv = Path.of("my-cv.txt");
        if (!Files.exists(cv)) Files.writeString(cv, "Wanjiru Kamau\nJava developer\nSkills: Java, SQL, Git, Selenium\n");
        Applicant me = new Applicant("Wanjiru Kamau", "wanjiru@example.com", "0712 345 678", 1,
                Set.of("java", "sql", "git", "selenium", "junit"), List.of("Nairobi", "Nakuru"), cv,
                "Dear {{company}} team,\n\nI'd love to join you as a {{title}}. I've built Java projects every day for months, "
                        + "with tests for all of them.\n\nKind regards,\n{{name}}");
        Ledger ledger = new Ledger(Path.of("applied.csv"));

        try (PracticeSite site = new PracticeSite()) {
            WebDriver driver = args.length > 0 && args[0].equals("--watch") ? Browsers.visibleChrome() : Browsers.headlessChrome();
            try {
                driver.get(site.url("/").toString());
                List<Job> matches = JobMatcher.worthApplying(new JobsPage(driver).jobs(), me, 60, ledger.appliedJobIds());
                if (matches.isEmpty()) System.out.println("No new jobs that match. (Delete applied.csv to start over.)");
                for (Job job : matches.subList(0, Math.min(MAX_PER_RUN, matches.size()))) {
                    driver.get(site.url("/apply.html?job=" + job.id()).toString());
                    String ref = new ApplyPage(driver).apply(me, JobMatcher.letterFor(job, me));
                    ledger.record(LocalDate.now(), job.id(), ref);
                    System.out.printf("Applied: %-34s %-16s %3d%% match, reference %s%n", job.title(), job.company(),
                            JobMatcher.score(job, me.skills()), ref);
                }
            } finally {
                driver.quit();
            }
        }
    }
}
