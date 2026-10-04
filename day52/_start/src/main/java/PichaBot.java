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
        // TODO: for each follower not already followed back:
        //   skip (and note) spam accounts; otherwise try followBack up to maxAttemptsEach times,
        //   sleeping backoff.nextDelay() after each refusal, and backoff.reset() after a success
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
