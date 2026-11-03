import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TillTest {

    private final Payment p1 = new Payment("QK1", "0712000001", 150, 7);
    private final Payment p2 = new Payment("QK2", "0722000002", 1200, 7);
    private final Payment p3 = new Payment("QK3", "0712000001", 80, 9);
    private final Payment p4 = new Payment("QK4", "0733000003", 2500, 13);
    private final Payment p5 = new Payment("QK5", "0722000002", 1000, 13);
    private final Payment p6 = new Payment("QK6", "0700000004", 50, 13);
    private final Payment p7 = new Payment("QK7", "0712000001", 1000, 18);
    private final List<Payment> day = List.of(p1, p2, p3, p4, p5, p6, p7);

    @Test
    void addsUpTheDay() {
        assertEquals(5980, Till.total(day));
        assertEquals(0, Till.total(List.of()));
    }

    @Test
    void listsBigPayersOnceEachInOrder() {
        assertEquals(List.of("0712000001", "0722000002", "0733000003"), Till.bigPayers(day, 1000));
        assertEquals(List.of("0733000003"), Till.bigPayers(day, 2000));
    }

    @Test
    void countsPaymentsPerHourInOrder() {
        Map<Integer, Long> perHour = Till.perHour(day);
        assertEquals(List.of(7, 9, 13, 18), List.copyOf(perHour.keySet()));
        assertEquals(2L, perHour.get(7));
        assertEquals(3L, perHour.get(13));
    }

    @Test
    void findsTheLargestPayment() {
        assertSame(p4, Till.largest(day).orElseThrow());
    }

    @Test
    void aDayWithNoPaymentsHasNoLargest() {
        assertTrue(Till.largest(List.of()).isEmpty());
    }

    @Test
    void joinsOnePhonesCodes() {
        assertEquals("QK1, QK3, QK7", Till.codesFor(day, "0712000001"));
        assertEquals("", Till.codesFor(day, "0799999999"));
    }
}
