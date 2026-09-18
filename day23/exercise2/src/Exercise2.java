public class Exercise2 {
    public static void main(String[] args) {
        Obstacle car = new Obstacle(0, 100, 5, "car");
        car.move();
        System.out.println("Car at x=" + car.getX() + ", off screen: " + car.isOffScreen(600));
    }
}
