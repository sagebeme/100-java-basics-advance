import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StationQueueTest {

    @Test
    void joinsAtTheBackOnlyOnce() {
        ArrayList<String> queue = new ArrayList<>();
        StationQueue.join(queue, "Wanjiru");
        StationQueue.join(queue, "Otieno");
        StationQueue.join(queue, "Wanjiru");
        assertEquals(List.of("Wanjiru", "Otieno"), queue);
    }

    @Test
    void servesFromTheFront() {
        ArrayList<String> queue = new ArrayList<>(List.of("Wanjiru", "Otieno"));
        assertEquals("Wanjiru", StationQueue.serveNext(queue));
        assertEquals(List.of("Otieno"), queue);
    }

    @Test
    void anEmptyQueueServesNobody() {
        assertEquals("nobody", StationQueue.serveNext(new ArrayList<>()));
    }

    @Test
    void positionsCountFromOne() {
        ArrayList<String> queue = new ArrayList<>(List.of("Wanjiru", "Otieno", "Achieng"));
        assertEquals(1, StationQueue.positionOf(queue, "Wanjiru"));
        assertEquals(3, StationQueue.positionOf(queue, "Achieng"));
        assertEquals(-1, StationQueue.positionOf(queue, "Kip"));
    }
}
