import java.util.Random;

public class WeatherDice {

    public static double between(Random rng, double min, double max) {
        return min + rng.nextDouble() * (max - min);
    }

    public static boolean chance(Random rng, double percent) {
        return rng.nextDouble() * 100 < percent;
    }

    public static void main(String[] args) {
        Random rng = new Random();
        int hailDays = 0;
        for (int day = 1; day <= 90; day++) {
            if (chance(rng, 5)) {
                hailDays++;
            }
        }
        System.out.println("Hailstorms this main crop: " + hailDays);
    }
}
