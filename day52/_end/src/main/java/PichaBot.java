import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class PichaBot {

    public record Report(List<String> followed, List<String> skipped, int waits) {}

    public static Report followBackRealPeople(FollowersPage page, Backoff backoff, int maxAttemptsEach) throws InterruptedException {
        List<String> followed = new ArrayList<>();
        List<String> skipped = new ArrayList<>();
        int waits = 0;
        for (Follower f : page.followers()) {
            if (f.followedBack()) continue;
            if (!SpamFilter.looksReal(f)) {
                skipped.add(f.user());
                continue;
            }
            for (int attempt = 1; attempt <= maxAttemptsEach; attempt++) {
                if (page.followBack(f.user())) {
                    followed.add(f.user());
                    backoff.reset();
                    break;
                }
                Duration wait = backoff.nextDelay();  // the site said "slow down": wait longer each time
                waits++;
                Thread.sleep(wait.toMillis());
            }
        }
        return new Report(followed, skipped, waits);
    }

    public static void main(String[] args) throws Exception {
        try (PracticeSite site = new PracticeSite()) {
            WebDriver driver = Browsers.headlessChrome();
            try {
                driver.get(site.url("/").toString());
                Report r = followBackRealPeople(new FollowersPage(driver), new Backoff(Duration.ofSeconds(1), Duration.ofSeconds(8)), 6);
                System.out.println("Followed back: " + String.join(", ", r.followed()));
                System.out.println("Skipped as spam: " + String.join(", ", r.skipped()));
                System.out.println("Backed off " + r.waits() + " times when the site asked.");
            } finally {
                driver.quit();
            }
        }
    }
}
