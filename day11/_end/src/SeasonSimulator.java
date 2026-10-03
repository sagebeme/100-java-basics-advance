import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SeasonSimulator {

    static final double CHERRY_PER_KG_GREEN = 6.0;
    static final double RATE_PER_KG = 85;
    static final double LEVY_PERCENT = 5;

    public static double randomRainfall(Random rng) {
        return 600 + rng.nextDouble() * 1600;
    }

    public static double kgPerTree(double rainfallMm) {
        if (rainfallMm < 800) return 1.0;   // drought
        if (rainfallMm <= 1800) return 2.5;
        return 1.8;                         // too wet: disease
    }

    public static double harvestKg(int trees, double rainfallMm) {
        return trees * kgPerTree(rainfallMm);
    }

    public static int dryingDays(double rainfallMm) {
        double lossPerDay = rainfallMm > 1800 ? 2 : 3;
        double moisture = 52;
        int days = 0;
        while (moisture > 11) {
            moisture -= lossPerDay;
            days++;
        }
        return days;
    }

    public static Map<String, Double> gradeSplit(double greenKg) {
        Map<String, Double> grades = new LinkedHashMap<>();
        grades.put("AA", greenKg * 0.30);
        grades.put("AB", greenKg * 0.40);
        grades.put("PB", greenKg * 0.10);
        grades.put("C", greenKg * 0.20);
        return grades;
    }

    public static double payout(double cherryKg) {
        double gross = cherryKg * RATE_PER_KG;
        return gross - gross * LEVY_PERCENT / 100;
    }

    static String describeRainfall(double rainfallMm) {
        if (rainfallMm < 800) return "a dry year";
        if (rainfallMm > 1800) return "a very wet year";
        return "a good year";
    }

    public static String seasonReport(String farmer, int trees, double rainfallMm) {
        double cherry = harvestKg(trees, rainfallMm);
        double green = cherry / CHERRY_PER_KG_GREEN;

        String grades = "";
        for (Map.Entry<String, Double> grade : gradeSplit(green).entrySet()) {
            if (!grades.isEmpty()) {
                grades += ", ";
            }
            grades += String.format("%s %.1f", grade.getKey(), grade.getValue());
        }

        return "Season report for " + farmer + "\n"
                + String.format("Rainfall: %.0f mm (%s)\n", rainfallMm, describeRainfall(rainfallMm))
                + String.format("Harvest: %.1f kg of cherry from %d trees\n", cherry, trees)
                + "Drying: " + dryingDays(rainfallMm) + " days on the beds\n"
                + String.format("Green beans: %.1f kg (%s)\n", green, grades)
                + String.format("Payout: KES %,.2f", payout(cherry));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rng = new Random();
        System.out.println("=== Season Simulator ===");

        System.out.println("Farmer's name?");
        String farmer = scanner.nextLine().trim();
        System.out.println("How many coffee trees?");
        int trees = Integer.parseInt(scanner.nextLine().trim());

        boolean again = true;
        while (again) {
            System.out.println(seasonReport(farmer, trees, randomRainfall(rng)));
            System.out.println("Simulate another season? (yes/no)");
            again = scanner.nextLine().trim().equalsIgnoreCase("yes");
        }

        scanner.close();
    }
}
