import java.util.Scanner;

public class CherryToCup {

    // TODO: a final double for the kg of cherry needed for 1 kg of green beans (6.0)
    // TODO: a final double for the share of weight kept after roasting (0.85)
    // TODO: a final int for the grams of ground coffee in one cup (10)

    public static double greenKg(double cherryKg) {
        // TODO: divide by the cherry-per-green constant
        return 0;
    }

    public static double roastedKg(double greenKg) {
        // TODO: multiply by the share kept after roasting
        return 0;
    }

    public static int cups(double roastedKg) {
        // TODO: kilograms to grams, divide by grams per cup, cast to int
        return 0;
    }

    // e.g. "60.0 kg of cherry makes 10.00 kg of green beans, 8.50 kg roasted, about 850 cups."
    public static String summary(double cherryKg) {
        // TODO: use greenKg, roastedKg and cups with String.format
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cherry to Cup ===");

        // TODO: ask "Kilograms of cherry?" and read a double
        // TODO: print the summary
        // TODO: print "That's enough for one cup a day for X years and Y days." using / and %

        scanner.close();
    }
}
