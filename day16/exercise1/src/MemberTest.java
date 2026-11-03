import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberTest {

    @Test
    void aNewMemberHasNoSavings() {
        Member wanjiru = new Member("Wanjiru", "0712345678");
        assertEquals("Wanjiru", wanjiru.getName());
        assertEquals("0712345678", wanjiru.getPhone());
        assertEquals(0, wanjiru.getSavings());
    }

    @Test
    void contributionsAddUp() {
        Member wanjiru = new Member("Wanjiru", "0712345678");
        wanjiru.contribute(500);
        wanjiru.contribute(1000);
        assertEquals(1500, wanjiru.getSavings());
    }

    @Test
    void refusesAContributionOfNothingOrLess() {
        Member wanjiru = new Member("Wanjiru", "0712345678");
        wanjiru.contribute(500);
        assertThrows(IllegalArgumentException.class, () -> wanjiru.contribute(0));
        assertThrows(IllegalArgumentException.class, () -> wanjiru.contribute(-200));
        assertEquals(500, wanjiru.getSavings());
    }

    @Test
    void acceptsSafaricomAndAirtelStyleNumbers() {
        assertEquals("0112345678", new Member("Otieno", "0112345678").getPhone());
    }

    @Test
    void refusesABadNumberWhenJoining() {
        assertThrows(IllegalArgumentException.class, () -> new Member("Otieno", "712345678"));
        assertThrows(IllegalArgumentException.class, () -> new Member("Otieno", "0812345678"));
        assertThrows(IllegalArgumentException.class, () -> new Member("Otieno", "07123456789"));
        assertThrows(IllegalArgumentException.class, () -> new Member("Otieno", "07123x5678"));
    }

    @Test
    void aBadNewNumberLeavesTheOldOne() {
        Member wanjiru = new Member("Wanjiru", "0712345678");
        wanjiru.setPhone("0798765432");
        assertEquals("0798765432", wanjiru.getPhone());
        assertThrows(IllegalArgumentException.class, () -> wanjiru.setPhone("12345"));
        assertEquals("0798765432", wanjiru.getPhone());
    }

    @Test
    void theCardShowsNamePhoneAndSavings() {
        Member wanjiru = new Member("Wanjiru", "0712345678");
        wanjiru.contribute(1500);
        assertEquals("Wanjiru · 0712345678 · KES 1500 saved", wanjiru.card());
    }
}
