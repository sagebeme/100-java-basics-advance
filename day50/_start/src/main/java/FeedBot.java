import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebDriver;

public class FeedBot {

    public record Report(int posted, int liked, int heldBack) {}

    public static Report run(FeedPage feed, List<ScheduledPost> queue, LocalDateTime now, RateLimiter limiter,
                             Set<String> following, Set<String> interests) {
        int posted = 0, liked = 0, heldBack = 0;
        // TODO: load the whole feed; note the texts "demo" has already posted
        // TODO: publish each due post, if the limiter allows (otherwise count it as held back)
        // TODO: like each post LikeRules approves, if the limiter allows
        return new Report(posted, liked, heldBack);
    }

    public static void main(String[] args) throws Exception {
        String user = System.getenv().getOrDefault("MTAA_USER", "demo");          // Day 35: credentials from
        String password = System.getenv().getOrDefault("MTAA_PASSWORD", "practice"); // the environment, not the code
        Path queueFile = Path.of("posts.txt");
        if (!Files.exists(queueFile)) {
            Files.writeString(queueFile, """
                    # When | what to post (max 280 characters)
                    2026-10-04T07:30 | Habari za asubuhi! Day 50 of 100 Days of Java ☕
                    2026-10-04T12:00 | Lunch break: teaching my bot to be polite
                    2099-01-01T00:00 | A post from the future that won't go out yet
                    """);
        }
        try (PracticeSite site = new PracticeSite()) {
            WebDriver driver = args.length > 0 && args[0].equals("--watch") ? Browsers.visibleChrome() : Browsers.headlessChrome();
            try {
                driver.get(site.url("/").toString());
                FeedPage feed = FeedPage.logIn(driver, user, password);
                Report r = run(feed, PostQueue.parse(Files.readAllLines(queueFile)), LocalDateTime.now(),
                        new RateLimiter(5, Duration.ofMinutes(1), Clock.systemUTC()),
                        Set.of("amina", "wanjiru", "brian"), Set.of("java", "selenium", "football", "harambee"));
                System.out.println("Posted " + r.posted() + ", liked " + r.liked() + ", held back " + r.heldBack() + " (rate limit).");
            } finally {
                driver.quit();
            }
        }
    }
}
