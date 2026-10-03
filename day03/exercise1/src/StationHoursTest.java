import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StationHoursTest {

    @Test
    void weekdaysOpenAtSevenAndCloseAtFive() {
        assertTrue(StationHours.isOpen("Monday", 7));
        assertTrue(StationHours.isOpen("Wednesday", 16));
        assertFalse(StationHours.isOpen("Monday", 17));
        assertFalse(StationHours.isOpen("Friday", 6));
    }

    @Test
    void saturdayClosesAtNoon() {
        assertTrue(StationHours.isOpen("Saturday", 11));
        assertFalse(StationHours.isOpen("Saturday", 12));
    }

    @Test
    void sundayIsClosedAllDay() {
        assertFalse(StationHours.isOpen("Sunday", 10));
    }

    @Test
    void theDayIgnoresCapitals() {
        assertFalse(StationHours.isOpen("SUNDAY", 10));
        assertFalse(StationHours.isOpen("saturday", 13));
    }
}
