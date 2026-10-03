import java.util.Scanner;

public class CherryToCup {

    static final double CHERRY_PER_KG_GREEN = 6.0;
    static final double ROAST_KEEPS = 0.85;
    static final int GRAMS_PER_CUP = 10;

    public static double greenKg(double cherryKg) {
        return cherryKg / CHERRY_PER_KG_GREEN;
    }

    public static double roastedKg(double greenKg) {
        return greenKg * ROAST_KEEPS;
    }

    public static int cups(double roastedKg) {
        double grams = roastedKg * 1000;
        return (int) (grams / GRAMS_PER_CUP); // a part-cup doesn't count
    }

    public static String summary(double cherryKg) {
        double green = greenKg(cherryKg);
        double roasted = roastedKg(green);
        return String.format("%.1f kg of cherry makes %.2f kg of green beans, %.2f kg roasted, about %d cups.",
                cherryKg, green, roasted, cups(roasted));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cherry to Cup ===");

        System.out.println("Kilograms of cherry?");
        double cherryKg = Double.parseDouble(scanner.nextLine().trim());

        System.out.println(summary(cherryKg));

        int totalCups = cups(roastedKg(greenKg(cherryKg)));
        int years = totalCups / 365; // integer division on purpose: whole years only
        int days = totalCups % 365;
        System.out.println("That's enough for one cup a day for " + years + " years and " + days + " days.");

        scanner.close();
    }
}
