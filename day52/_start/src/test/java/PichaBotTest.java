import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PichaBotTest extends BrowserTest {

    @Test
    void readsEveryFollower() {
        driver.get(site.url("/").toString());
        List<Follower> all = new FollowersPage(driver).followers();
        assertEquals(8, all.size());
        assertEquals(new Follower("amina_designs", 214, 830, false), all.get(0));
    }

    @Test
    void theSiteThrottlesTheFourthQuickFollow() {
        driver.get(site.url("/?window=2000").toString());
        FollowersPage page = new FollowersPage(driver);
        assertTrue(page.followBack("amina_designs"));
        assertTrue(page.followBack("brian.k"));
        assertTrue(page.followBack("wanjiru.codes"));
        assertFalse(page.followBack("otieno_photos"));
        assertTrue(page.isThrottled());
    }

    @Test
    void followsBackRealPeopleAndBacksOffWhenTold() throws InterruptedException {
        driver.get(site.url("/?window=1200").toString());   // a short window keeps the test quick
        PichaBot.Report r = PichaBot.followBackRealPeople(new FollowersPage(driver),
                new Backoff(Duration.ofMillis(300), Duration.ofMillis(1200)), 8);
        assertEquals(List.of("amina_designs", "brian.k", "wanjiru.codes", "otieno_photos", "kevin_mitumba", "neema.art"), r.followed());
        assertEquals(List.of("free_phones_4u", "xx_win_now_xx"), r.skipped());
        assertTrue(r.waits() >= 1);
    }
}
