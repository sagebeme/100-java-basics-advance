import java.util.Comparator;
import java.util.List;
import java.util.Set;

// Which jobs are worth applying for. Plain Java, so it's tested without a browser.
public class JobMatcher {

    // The share of the job's skills the applicant has, from 0 to 100.
    public static int score(Job job, Set<String> skills) {
        if (job.skills().isEmpty()) return 0;
        long have = job.skills().stream().filter(skills::contains).count();
        return (int) Math.round(have * 100.0 / job.skills().size());
    }

    // Jobs in one of the applicant's places (or remote) scoring at least minScore, best first.
    public static List<Job> worthApplying(List<Job> jobs, Applicant me, int minScore, Set<String> alreadyApplied) {
        return jobs.stream()
                .filter(j -> !alreadyApplied.contains(j.id()))
                .filter(j -> j.remote() || me.places().contains(j.location()))
                .filter(j -> score(j, me.skills()) >= minScore)
                .sorted(Comparator.comparingInt((Job j) -> score(j, me.skills())).reversed().thenComparing(Job::id))
                .toList();
    }

    // Fills {{title}}, {{company}} and {{name}} in the cover letter template.
    public static String letterFor(Job job, Applicant me) {
        return me.letterTemplate().replace("{{title}}", job.title()).replace("{{company}}", job.company()).replace("{{name}}", me.name());
    }
}
