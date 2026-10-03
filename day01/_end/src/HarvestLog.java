import java.util.Scanner;

public class HarvestLog {

    // Builds one line of the weighing station's log book, e.g.
    // "Wanjiru delivered 23.5 kg of cherry. All ripe: yes"
    public static String logLine(String farmer, double kg, boolean allRipe) {
        String ripeText = allRipe ? "yes" : "no";
        return farmer + " delivered " + kg + " kg of cherry. All ripe: " + ripeText;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Harvest Log ===");

        System.out.println("Farmer's name?");
        String farmer = scanner.nextLine();

        System.out.println("Kilograms of cherry picked?");
        double kg = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Were they all red and ripe? (yes/no)");
        boolean allRipe = scanner.nextLine().trim().equalsIgnoreCase("yes");

        System.out.println(logLine(farmer, kg, allRipe));

        scanner.close();
    }
}
