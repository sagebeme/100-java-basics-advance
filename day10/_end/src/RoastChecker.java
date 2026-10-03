import java.util.Scanner;

public class RoastChecker {

    public static String roastLevel(int finalTempC) {
        if (finalTempC < 196) return "UNDERDEVELOPED";
        if (finalTempC <= 205) return "LIGHT";
        if (finalTempC <= 218) return "MEDIUM";
        if (finalTempC <= 230) return "DARK";
        return "BURNT";
    }

    public static double weightLossPercent(double greenGrams, double roastedGrams) {
        if (greenGrams <= 0 || roastedGrams > greenGrams) {
            return -1;
        }
        return (greenGrams - roastedGrams) / greenGrams * 100;
    }

    public static String levelFromLoss(double lossPercent) {
        if (lossPercent < 0) return "UNKNOWN";
        if (lossPercent < 14) return "LIGHT";
        if (lossPercent < 17) return "MEDIUM";
        return "DARK";
    }

    public static boolean readingsAgree(int finalTempC, double greenGrams, double roastedGrams) {
        return roastLevel(finalTempC).equals(levelFromLoss(weightLossPercent(greenGrams, roastedGrams)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Roast Checker ===");

        System.out.println("Final bean temperature (°C)?");
        int temp = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Green weight (g)?");
        double green = Double.parseDouble(scanner.nextLine().trim());
        System.out.println("Roasted weight (g)?");
        double roasted = Double.parseDouble(scanner.nextLine().trim());

        double loss = weightLossPercent(green, roasted);
        System.out.println("Thermometer says: " + roastLevel(temp));
        System.out.println(String.format("Weight lost: %.1f%% (%s)", loss, levelFromLoss(loss)));

        if (readingsAgree(temp, green, roasted)) {
            System.out.println("The thermometer and the scale agree.");
        } else {
            System.out.println("Check your thermometer or your scale.");
        }

        scanner.close();
    }
}
