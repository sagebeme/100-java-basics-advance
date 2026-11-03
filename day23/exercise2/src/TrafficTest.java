import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrafficTest {

    // A Random that always rolls the numbers it's given, in turn, so the test knows what happens.
    static class LoadedDice extends Random {
        private final int[] rolls;
        private int next;

        LoadedDice(int... rolls) {
            this.rolls = rolls;
        }

        @Override
        public int nextInt(int bound) {
            return rolls[next++];
        }
    }

    @Test
    void carsMoveEveryTick() {
        Traffic traffic = new Traffic(300, 20);
        traffic.add(new Car(10, 0, 3));
        traffic.add(new Car(50, 1, 5));
        traffic.tick();
        assertEquals(13, traffic.cars().get(0).getX());
        assertEquals(55, traffic.cars().get(1).getX());
    }

    @Test
    void carsThatLeaveTheRoadAreGone() {
        Traffic traffic = new Traffic(300, 20);
        traffic.add(new Car(296, 0, 5));
        traffic.add(new Car(290, 1, 5));
        traffic.add(new Car(100, 2, 5));
        traffic.tick();
        List<Integer> left = new ArrayList<>();
        for (Car car : traffic.cars()) {
            left.add(car.getX());
        }
        assertEquals(List.of(295, 105), left);
    }

    @Test
    void aCarHitsAPointInItsLaneAlongItsLength() {
        Traffic traffic = new Traffic(300, 20);
        traffic.add(new Car(100, 2, 3));
        assertTrue(traffic.hits(100, 45));
        assertTrue(traffic.hits(140, 40));
        assertTrue(traffic.hits(120, 59));
        assertFalse(traffic.hits(99, 45));
        assertFalse(traffic.hits(141, 45));
    }

    @Test
    void aCarInAnotherLaneMisses() {
        Traffic traffic = new Traffic(300, 20);
        traffic.add(new Car(100, 2, 3));
        assertFalse(traffic.hits(120, 39));
        assertFalse(traffic.hits(120, 60));
    }

    @Test
    void spawnsACarInEachLaneThatRollsUnderTheChance() {
        Traffic traffic = new Traffic(300, 20);
        traffic.spawn(new LoadedDice(10, 90, 29, 30), 4, 30, 1);
        List<Integer> lanes = new ArrayList<>();
        for (Car car : traffic.cars()) {
            lanes.add(car.getLane());
            assertEquals(-Car.LENGTH, car.getX());
        }
        assertEquals(List.of(0, 2), lanes);
    }

    @Test
    void higherLevelsSpawnFasterCars() {
        Traffic traffic = new Traffic(300, 20);
        traffic.spawn(new LoadedDice(0), 1, 50, 1);
        traffic.spawn(new LoadedDice(0), 1, 50, 4);
        assertEquals(3, traffic.cars().get(0).getSpeed());
        assertEquals(6, traffic.cars().get(1).getSpeed());
    }

    @Test
    void changingTheCopyDoesNotChangeTheRoad() {
        Traffic traffic = new Traffic(300, 20);
        traffic.add(new Car(100, 2, 3));
        traffic.cars().clear();
        assertEquals(1, traffic.cars().size());
    }
}
