import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpamFilterTest {

    @Test
    void realPeoplePass() {
        assertTrue(SpamFilter.looksReal(new Follower("wanjiru.codes", 58, 960, false)));
        assertTrue(SpamFilter.looksReal(new Follower("kev_27", 10, 100, false))); // two digits are fine
    }

    @Test
    void emptyAccountsAndAdvertsDont() {
        assertFalse(SpamFilter.looksReal(new Follower("free_phones_4u", 40, 900, false)));
        assertFalse(SpamFilter.looksReal(new Follower("WIN_BIG", 40, 900, false)));
        assertFalse(SpamFilter.looksReal(new Follower("amina", 0, 900, false)));
        assertFalse(SpamFilter.looksReal(new Follower("amina", 40, 5, false)));
        assertFalse(SpamFilter.looksReal(new Follower("user48213", 40, 900, false)));
    }
}
