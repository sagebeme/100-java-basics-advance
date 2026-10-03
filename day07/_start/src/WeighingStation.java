import java.util.ArrayList;
import java.util.Scanner;

public class WeighingStation {

    // Adds the delivery to both lists. Refuses an empty name or kg <= 0 by returning false.
    public static boolean record(ArrayList<String> farmers, ArrayList<Double> kgs, String farmer, double kg) {
        // TODO
        return false;
    }

    public static double totalKg(ArrayList<Double> kgs) {
        // TODO
        return 0;
    }

    // One farmer's kilograms. Names match whatever their capitals.
    public static double totalFor(ArrayList<String> farmers, ArrayList<Double> kgs, String farmer) {
        // TODO
        return 0;
    }

    public static int deliveriesBy(ArrayList<String> farmers, String farmer) {
        // TODO
        return 0;
    }

    // "2 deliveries, 54.5 kg in total"
    public static String summary(ArrayList<String> farmers, ArrayList<Double> kgs) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> farmers = new ArrayList<>();
        ArrayList<Double> kgs = new ArrayList<>();

        System.out.println("=== Weighing Station ===");
        System.out.println("1. Record a delivery");
        System.out.println("2. A farmer's total");
        System.out.println("3. Today's summary");
        System.out.println("4. Close the station");

        // TODO: loop until option 4: ask "Choose 1-4:", then do what was chosen

        scanner.close();
    }
}
