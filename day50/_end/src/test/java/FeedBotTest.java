import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedBotTest extends BrowserTest {

    private FeedPage logIn() {
        driver.get(site.url("/").toString());
        return FeedPage.logIn(driver, "demo", "practice");
    }

    @Test
    void aWrongPasswordIsReported() {
        driver.get(site.url("/").toString());
        assertTrue(assertThrows(IllegalStateException.class, () -> FeedPage.logIn(driver, "demo", "nope")).getMessage().contains("Wrong"));
    }

    @Test
    void loadsEveryPageOfTheFeed() {
        FeedPage feed = logIn();
        assertEquals(4, feed.posts().size());
        feed.loadAll(10);
        assertEquals(9, feed.posts().size());
    }

    @Test
    void postsWhatsDueLikesWhatFitsAndRespectsTheLimit() {
        FeedPage feed = logIn();
        List<ScheduledPost> queue = PostQueue.parse(List.of("2026-10-04T07:30 | Day 50!", "2099-01-01T00:00 | Not yet"));
        FeedBot.Report r = FeedBot.run(feed, queue, LocalDateTime.of(2026, 10, 4, 9, 0),
                new RateLimiter(3, Duration.ofMinutes(1), Clock.systemUTC()),
                Set.of("wanjiru", "brian"), Set.of("java", "selenium", "harambee", "football"));
        assertEquals(1, r.posted());
        assertEquals(2, r.liked());      // the limit of 3 stops the bot after one post and two likes...
        assertEquals(2, r.heldBack());   // ...holding back the other two likes it wanted
        assertEquals("demo", feed.posts().get(0).author());
        assertEquals(2, feed.posts().stream().filter(Post::liked).count());
    }
}
