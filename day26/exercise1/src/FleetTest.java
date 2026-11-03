import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleetTest {

    private final Matatu a = new Matatu("KDA 123A", "46", 33, 4.5);
    private final Matatu b = new Matatu("KBZ 900C", "111", 14, 4.8);
    private final Matatu c = new Matatu("KCE 555B", "46", 14, 3.9);
    private final Matatu d = new Matatu("KAA 001X", "33", 51, 4.5);
    private final List<Matatu> fleet = List.of(a, b, c, d);

    private List<String> plates(List<Matatu> matatus) {
        List<String> plates = new ArrayList<>();
        for (Matatu m : matatus) {
            plates.add(m.getPlate());
        }
        return plates;
    }

    private List<Matatu> sorted(java.util.Comparator<Matatu> order) {
        List<Matatu> copy = new ArrayList<>(fleet);
        copy.sort(order);
        return copy;
    }

    @Test
    void sortsByPlate() {
        assertEquals(List.of("KAA 001X", "KBZ 900C", "KCE 555B", "KDA 123A"), plates(sorted(Fleet.byPlate())));
    }

    @Test
    void sortsByMostSeats() {
        List<String> plates = plates(sorted(Fleet.mostSeatsFirst()));
        assertEquals("KAA 001X", plates.get(0));
        assertEquals("KDA 123A", plates.get(1));
    }

    @Test
    void sortsByRatingThenPlate() {
        assertEquals(List.of("KBZ 900C", "KAA 001X", "KDA 123A", "KCE 555B"), plates(sorted(Fleet.bestRatedThenPlate())));
    }

    @Test
    void picksARoute() {
        assertEquals(List.of("KDA 123A", "KCE 555B"), Fleet.pick(fleet, Fleet.onRoute("46"), Matatu::getPlate));
    }

    @Test
    void picksBySeats() {
        assertEquals(List.of("KDA 123A", "KAA 001X"), Fleet.pick(fleet, Fleet.atLeastSeats(33), Matatu::getPlate));
    }

    @Test
    void predicatesCombine() {
        assertEquals(List.of("KDA 123A"),
                Fleet.pick(fleet, Fleet.onRoute("46").and(Fleet.atLeastSeats(20)), Matatu::getPlate));
        assertEquals(List.of("KBZ 900C", "KCE 555B"),
                Fleet.pick(fleet, Fleet.atLeastSeats(20).negate(), Matatu::getPlate));
    }

    @Test
    void labelsAMatatu() {
        assertEquals(List.of("KBZ 900C (route 111, 14 seats)"),
                Fleet.pick(fleet, Fleet.onRoute("111"), Fleet.label()));
    }
}
