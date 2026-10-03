import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryDaysTest {

    private final List<String> monday = List.of("Wanjiru", "Otieno", "Wanjiru", "Achieng");
    private final List<String> tuesday = List.of("Otieno", "Kip", "Kip", "Wanjiru");

    @Test
    void findsFarmersWhoCameBothDays() {
        assertEquals(Set.of("Otieno", "Wanjiru"), DeliveryDays.bothDays(monday, tuesday));
    }

    @Test
    void findsNewFacesOnTuesdayOnce() {
        assertEquals(Set.of("Kip"), DeliveryDays.onlyTuesday(monday, tuesday));
    }

    @Test
    void leavesTheListsAlone() {
        DeliveryDays.bothDays(monday, tuesday);
        DeliveryDays.onlyTuesday(monday, tuesday);
        assertEquals(4, monday.size());
        assertEquals(4, tuesday.size());
    }
}
