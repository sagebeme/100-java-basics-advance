import java.util.Scanner;

public class DryingBed {

    static final double SUNNY_LOSS = 3.0;
    static final double CLOUDY_LOSS = 1.5;

    // The same loss every day. -1 if the beans can never dry.
    public static int daysToDry(double startMoisture, double targetMoisture, double lossPerDay) {
        // TODO: return -1 if lossPerDay <= 0
        // TODO: a while loop that removes lossPerDay until the moisture is at or below the target
        return 0;
    }

    // forecast is one letter per day: S sunny, C cloudy, R rain. -1 if it isn't dry by the end.
    public static int daysToDryWithWeather(double startMoisture, double targetMoisture, String forecast) {
        // TODO: an enhanced for over forecast.toCharArray()
        return 0;
    }

    // One '#' for every full 5 percentage points: 52 -> "##########"
    public static String moistureBar(double moisture) {
        // TODO: build the bar in a for loop
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Drying Bed Countdown ===");

        // TODO: ask for the starting moisture and the forecast
        // TODO: print "Day N: M% ###" for each day until it's dry or the forecast runs out
        // TODO: print "Dry after N days." or "Not dry yet. Keep turning the beans."

        scanner.close();
    }
}
