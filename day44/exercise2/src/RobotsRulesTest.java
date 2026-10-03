import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotsRulesTest {

    private final RobotsRules rules = new RobotsRules("""
            # Gikomba Online
            User-agent: Googlebot
            Disallow: /search

            User-agent: *
            Disallow: /admin   # staff only
            disallow: /checkout
            """);

    @Test
    void blocksPathsForEveryRobot() {
        assertFalse(rules.isAllowed("/admin"));
        assertFalse(rules.isAllowed("/admin/users"));
        assertFalse(rules.isAllowed("/checkout?cart=12"));
    }

    @Test
    void rulesForOtherRobotsDontApplyToYou() {
        assertTrue(rules.isAllowed("/search?q=jacket"));
        assertTrue(rules.isAllowed("/listings?page=2"));
    }

    @Test
    void anEmptyDisallowAllowsEverything() {
        assertTrue(new RobotsRules("User-agent: *\nDisallow:\n").isAllowed("/anything"));
        assertTrue(new RobotsRules("").isAllowed("/anything"));
    }
}
