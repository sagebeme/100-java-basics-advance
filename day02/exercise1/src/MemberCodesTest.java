import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberCodesTest {

    @Test
    void cleansSpacesAndCase() {
        assertEquals("Wanjiru Kamau", MemberCodes.cleanName("  wanjiru KAMAU "));
    }

    @Test
    void takesTheFirstLetterOfEachName() {
        assertEquals("WK", MemberCodes.initials("Wanjiru Kamau"));
    }

    @Test
    void padsTheMemberNumberToFourDigits() {
        assertEquals("WK-0042", MemberCodes.memberCode("wanjiru kamau", 42));
    }

    @Test
    void worksForALongMemberNumber() {
        assertEquals("OA-1250", MemberCodes.memberCode("OTIENO achieng", 1250));
    }
}
