import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Traffic {

    private final int width;
    private final int laneHeight;
    private final List<Car> cars = new ArrayList<>();

    public Traffic(int width, int laneHeight) {
        this.width = width;
        this.laneHeight = laneHeight;
    }

    public void add(Car car) {
        cars.add(car);
    }

    public List<Car> cars() {
        return new ArrayList<>(cars);
    }

    // Every car moves once; cars that have driven off the right-hand side are gone.
    public void tick() {
        for (Car car : cars) {
            car.move();
        }
        cars.removeIf(car -> car.getX() > width);
    }

    // Is there a car on the point (x, y)? Lane 0 covers y from 0 to laneHeight - 1, and so on.
    public boolean hits(int x, int y) {
        int lane = y / laneHeight;
        for (Car car : cars) {
            if (car.getLane() == lane && x >= car.getX() && x <= car.getX() + Car.LENGTH) {
                return true;
            }
        }
        return false;
    }

    // Each lane gets a new car, just off the left edge, with the given chance. Higher levels drive faster.
    public void spawn(Random random, int lanes, int chancePercent, int level) {
        for (int lane = 0; lane < lanes; lane++) {
            if (random.nextInt(100) < chancePercent) {
                cars.add(new Car(-Car.LENGTH, lane, 2 + level));
            }
        }
    }
}
