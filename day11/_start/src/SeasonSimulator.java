import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SeasonSimulator {

    static final double CHERRY_PER_KG_GREEN = 6.0;
    static final double RATE_PER_KG = 85;
    static final double LEVY_PERCENT = 5;

    // 600 up to 2200 mm
    public static double randomRainfall(Random rng) {
        // TODO
        return 0;
    }

    public static double kgPerTree(double rainfallMm) {
        // TODO: 1.0 below 800, 2.5 up to 1800, 1.8 above
        return 0;
    }

    public static double harvestKg(int trees, double rainfallMm) {
        // TODO
        return 0;
    }

    // From 52% to 11% moisture: 3 points a day, or 2 a day after more than 1800 mm of rain
    public static int dryingDays(double rainfallMm) {
        // TODO: a while loop
        return 0;
    }

    // AA 30%, AB 40%, PB 10%, C 20%, in that order
    public static Map<String, Double> gradeSplit(double greenKg) {
        Map<String, Double> grades = new LinkedHashMap<>();
        // TODO
        return grades;
    }

    // RATE_PER_KG for every kg of cherry, minus LEVY_PERCENT
    public static double payout(double cherryKg) {
        // TODO
        return 0;
    }

    // The six report lines from the README, joined with "\n"
    public static String seasonReport(String farmer, int trees, double rainfallMm) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rng = new Random();
        System.out.println("=== Season Simulator ===");

        // TODO: ask for the farmer's name and number of trees
        // TODO: loop: random rainfall, print the report, ask "Simulate another season? (yes/no)"

        scanner.close();
    }
}
