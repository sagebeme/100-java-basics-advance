import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventPickerTest {

    private static EventCard e(int id, String date, int price) {
        return new EventCard(id, "Event " + id, "Nairobi", LocalDate.parse(date), price);
    }

    @Test
    void picksTheCheapestWithinBudget() {
        List<EventCard> events = List.of(e(1, "2026-12-12", 1800), e(2, "2026-12-06", 1200), e(3, "2026-12-07", 900));
        assertEquals(3, EventPicker.pick(events, 1500, LocalDate.of(2026, 12, 1)).orElseThrow().id());
    }

    @Test
    void ignoresEventsThatHaveAlreadyHappened() {
        List<EventCard> events = List.of(e(1, "2026-11-20", 500), e(2, "2026-12-06", 1200));
        assertEquals(2, EventPicker.pick(events, 1500, LocalDate.of(2026, 12, 1)).orElseThrow().id());
    }

    @Test
    void theEarlierEventWinsATie() {
        List<EventCard> events = List.of(e(1, "2026-12-12", 1000), e(2, "2026-12-06", 1000));
        assertEquals(2, EventPicker.pick(events, 1500, LocalDate.of(2026, 12, 1)).orElseThrow().id());
    }

    @Test
    void nothingWithinBudgetIsEmpty() {
        assertTrue(EventPicker.pick(List.of(e(1, "2026-12-12", 1800)), 1500, LocalDate.of(2026, 12, 1)).isEmpty());
    }
}
