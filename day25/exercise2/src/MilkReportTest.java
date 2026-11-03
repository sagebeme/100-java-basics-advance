import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class MilkReportTest {

    private final Delivery w1 = new Delivery("Wanjiku", "2026-11-01", 18, "A");
    private final Delivery k1 = new Delivery("Kamau", "2026-11-01", 12, "B");
    private final Delivery n1 = new Delivery("Njoroge", "2026-11-01", 25, "A");
    private final Delivery w2 = new Delivery("Wanjiku", "2026-11-02", 20, "A");
    private final Delivery k2 = new Delivery("Kamau", "2026-11-02", 7, "C");
    private final Delivery n2 = new Delivery("Njoroge", "2026-11-02", 18, "B");
    private final List<Delivery> week = List.of(w1, k1, n1, w2, k2, n2);

    @Test
    void totalsEachFarmerAlphabetically() {
        Map<String, Integer> totals = MilkReport.totalByFarmer(week);
        assertEquals(List.of("Kamau", "Njoroge", "Wanjiku"), List.copyOf(totals.keySet()));
        assertEquals(19, totals.get("Kamau"));
        assertEquals(43, totals.get("Njoroge"));
        assertEquals(38, totals.get("Wanjiku"));
    }

    @Test
    void ranksTheTopFarmers() {
        assertEquals(List.of("Njoroge 43", "Wanjiku 38"), MilkReport.topFarmers(week, 2));
        assertEquals(3, MilkReport.topFarmers(week, 10).size());
    }

    @Test
    void aTieGoesAlphabetically() {
        List<Delivery> tie = List.of(new Delivery("Otieno", "2026-11-01", 10, "A"),
                new Delivery("Achieng", "2026-11-01", 10, "A"));
        assertEquals(List.of("Achieng 10", "Otieno 10"), MilkReport.topFarmers(tie, 2));
    }

    @Test
    void averagesLitresPerDelivery() {
        assertEquals(100.0 / 6, MilkReport.averageLitres(week), 1e-9);
        assertEquals(0, MilkReport.averageLitres(List.of()), 1e-9);
    }

    @Test
    void sharesAreByLitresNotByDeliveries() {
        assertEquals(63, MilkReport.gradeShare(week, "A"));
        assertEquals(30, MilkReport.gradeShare(week, "B"));
        assertEquals(7, MilkReport.gradeShare(week, "C"));
        assertEquals(0, MilkReport.gradeShare(List.of(), "A"));
    }

    @Test
    void listsTurnedAwayMilkInOrder() {
        List<Delivery> rejected = MilkReport.rejected(week);
        assertEquals(1, rejected.size());
        assertSame(k2, rejected.get(0));
    }
}
