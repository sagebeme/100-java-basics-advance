// One car in one lane, driving left to right. Already done: Traffic is today's work.
public class Car {

    public static final int LENGTH = 40;

    private int x;
    private final int lane;
    private final int speed;

    public Car(int x, int lane, int speed) {
        this.x = x;
        this.lane = lane;
        this.speed = speed;
    }

    public void move() {
        x += speed;
    }

    public int getX() {
        return x;
    }

    public int getLane() {
        return lane;
    }

    public int getSpeed() {
        return speed;
    }
}
