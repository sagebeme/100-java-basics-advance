import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RulesTest {

    @Test
    void parsesTheQueueAndSkipsComments() {
        List<ScheduledPost> q = PostQueue.parse(List.of("# a comment", "2026-10-04T07:30 | Habari!", "", "2026-10-04T12:00|Lunch"));
        assertEquals(List.of(new ScheduledPost(LocalDateTime.of(2026, 10, 4, 7, 30), "Habari!"),
                new ScheduledPost(LocalDateTime.of(2026, 10, 4, 12, 0), "Lunch")), q);
    }

    @Test
    void refusesPostsThatAreTooLongOrMalformed() {
        assertThrows(IllegalArgumentException.class, () -> PostQueue.parse(List.of("2026-10-04T07:30 | " + "x".repeat(281))));
        assertThrows(IllegalArgumentException.class, () -> PostQueue.parse(List.of("no time here")));
    }

    @Test
    void onlyDuePostsNotAlreadyPosted() {
        List<ScheduledPost> q = PostQueue.parse(List.of("2026-10-04T12:00 | B", "2026-10-04T07:30 | A", "2026-10-05T07:30 | C"));
        assertEquals(List.of("A"), PostQueue.due(q, LocalDateTime.of(2026, 10, 4, 13, 0), List.of("B")).stream().map(ScheduledPost::text).toList());
    }

    @Test
    void likesOnlyFriendsTalkingAboutYourInterests() {
        Set<String> following = Set.of("wanjiru", "brian");
        Set<String> interests = Set.of("java", "football");
        assertTrue(LikeRules.shouldLike(new Post("1", "wanjiru", "My first Java project!", false), "demo", following, interests));
        assertFalse(LikeRules.shouldLike(new Post("2", "spam_king", "Java phones!!!", false), "demo", following, interests));
        assertFalse(LikeRules.shouldLike(new Post("3", "brian", "Nice weather", false), "demo", following, interests));
        assertFalse(LikeRules.shouldLike(new Post("4", "brian", "Football at 9", true), "demo", following, interests));
    }

    static class FakeClock extends Clock {
        Instant now = Instant.parse("2026-10-04T08:00:00Z");
        public ZoneOffset getZone() { return ZoneOffset.UTC; }
        public Clock withZone(java.time.ZoneId z) { return this; }
        public Instant instant() { return now; }
    }

    @Test
    void theRateLimiterAllowsBurstsUpToTheLimitThenWaits() {
        FakeClock clock = new FakeClock();
        RateLimiter limiter = new RateLimiter(3, Duration.ofMinutes(1), clock);
        assertTrue(limiter.tryAcquire());
        assertTrue(limiter.tryAcquire());
        assertTrue(limiter.tryAcquire());
        assertFalse(limiter.tryAcquire());
        clock.now = clock.now.plusSeconds(61);
        assertTrue(limiter.tryAcquire());
    }
}
