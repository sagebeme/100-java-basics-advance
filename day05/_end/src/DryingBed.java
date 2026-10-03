import java.util.Scanner;

public class DryingBed {

    static final double SUNNY_LOSS = 3.0;
    static final double CLOUDY_LOSS = 1.5;

    public static int daysToDry(double startMoisture, double targetMoisture, double lossPerDay) {
        if (lossPerDay <= 0) {
            return -1; // it would never dry, and the loop would never end
        }
        double moisture = startMoisture;
        int days = 0;
        while (moisture > targetMoisture) {
            moisture = moisture - lossPerDay;
            days++;
        }
        return days;
    }

    static double lossFor(char weather) {
        if (weather == 'S') {
            return SUNNY_LOSS;
        } else if (weather == 'C') {
            return CLOUDY_LOSS;
        }
        return 0; // rain: the beans are covered
    }

    public static int daysToDryWithWeather(double startMoisture, double targetMoisture, String forecast) {
        double moisture = startMoisture;
        int day = 0;
        for (char weather : forecast.toUpperCase().toCharArray()) {
            day++;
            moisture = moisture - lossFor(weather);
            if (moisture <= targetMoisture) {
                return day;
            }
        }
        return -1;
    }

    public static String moistureBar(double moisture) {
        String bar = "";
        for (int i = 0; i < (int) (moisture / 5); i++) {
            bar = bar + "#";
        }
        return bar;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Drying Bed Countdown ===");

        System.out.println("Starting moisture (%)?");
        double moisture = Double.parseDouble(scanner.nextLine().trim());
        System.out.println("Weather for the next days (S = sunny, C = cloudy, R = rain)?");
        String forecast = scanner.nextLine().trim().toUpperCase();

        int day = 0;
        boolean dry = false;
        for (char weather : forecast.toCharArray()) {
            day++;
            moisture = moisture - lossFor(weather);
            System.out.println(String.format("Day %d: %.1f%% %s", day, moisture, moistureBar(moisture)));
            if (moisture <= 11) {
                dry = true;
                break;
            }
        }

        if (dry) {
            System.out.println("Dry after " + day + " days.");
        } else {
            System.out.println("Not dry yet. Keep turning the beans.");
        }

        scanner.close();
    }
}
