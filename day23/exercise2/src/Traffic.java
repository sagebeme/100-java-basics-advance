import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Traffic {

    // TODO: private fields: the road's width, the height of one lane, and the cars

    public Traffic(int width, int laneHeight) {
        // TODO
    }

    public void add(Car car) {
        // TODO
    }

    public List<Car> cars() {
        // TODO: a copy
        return new ArrayList<>();
    }

    // Every car moves once; cars that have driven off the right-hand side are gone.
    public void tick() {
        // TODO
    }

    // Is there a car on the point (x, y)? Lane 0 covers y from 0 to laneHeight - 1, and so on.
    public boolean hits(int x, int y) {
        // TODO
        return false;
    }

    // Each lane gets a new car, just off the left edge, with the given chance. Higher levels drive faster.
    public void spawn(Random random, int lanes, int chancePercent, int level) {
        // TODO: one random.nextInt(100) per lane; below chancePercent means a car
    }
}
