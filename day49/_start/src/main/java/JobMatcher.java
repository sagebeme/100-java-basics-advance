import java.util.Comparator;
import java.util.List;
import java.util.Set;

// Which jobs are worth applying for. Plain Java, so it's tested without a browser.
public class JobMatcher {

    // The share of the job's skills the applicant has, from 0 to 100.
    public static int score(Job job, Set<String> skills) {
        // TODO: percentage of job.skills() the applicant has, rounded; 0 for a job with no skills
        return 0;
    }

    // Jobs in one of the applicant's places (or remote) scoring at least minScore, best first.
    public static List<Job> worthApplying(List<Job> jobs, Applicant me, int minScore, Set<String> alreadyApplied) {
        // TODO: not already applied; remote or in one of me.places(); score >= minScore; best score first, then by id
        return List.of();
    }

    // Fills {{title}}, {{company}} and {{name}} in the cover letter template.
    public static String letterFor(Job job, Applicant me) {
        return me.letterTemplate().replace("{{title}}", job.title()).replace("{{company}}", job.company()).replace("{{name}}", me.name());
    }
}
