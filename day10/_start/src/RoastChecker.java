import java.util.Scanner;

public class RoastChecker {

    public static String roastLevel(int finalTempC) {
        // TODO: UNDERDEVELOPED, LIGHT, MEDIUM, DARK or BURNT
        return "";
    }

    // Percent of the green weight lost. -1 for impossible input.
    public static double weightLossPercent(double greenGrams, double roastedGrams) {
        // TODO
        return 0;
    }

    public static String levelFromLoss(double lossPercent) {
        // TODO: UNKNOWN, LIGHT, MEDIUM or DARK
        return "";
    }

    public static boolean readingsAgree(int finalTempC, double greenGrams, double roastedGrams) {
        // TODO: build this from the three methods above
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Roast Checker ===");

        // TODO: ask for the final temperature and both weights
        // TODO: print what the thermometer says, the weight lost, and whether they agree

        scanner.close();
    }
}
