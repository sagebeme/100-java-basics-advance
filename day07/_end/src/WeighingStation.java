import java.util.ArrayList;
import java.util.Scanner;

public class WeighingStation {

    public static boolean record(ArrayList<String> farmers, ArrayList<Double> kgs, String farmer, double kg) {
        if (farmer.trim().isEmpty() || kg <= 0) {
            return false;
        }
        farmers.add(farmer.trim());
        kgs.add(kg);
        return true;
    }

    public static double totalKg(ArrayList<Double> kgs) {
        double total = 0;
        for (double kg : kgs) {
            total += kg;
        }
        return total;
    }

    public static double totalFor(ArrayList<String> farmers, ArrayList<Double> kgs, String farmer) {
        double total = 0;
        for (int i = 0; i < farmers.size(); i++) {
            if (farmers.get(i).equalsIgnoreCase(farmer.trim())) {
                total += kgs.get(i);
            }
        }
        return total;
    }

    public static int deliveriesBy(ArrayList<String> farmers, String farmer) {
        int count = 0;
        for (String name : farmers) {
            if (name.equalsIgnoreCase(farmer.trim())) {
                count++;
            }
        }
        return count;
    }

    public static String summary(ArrayList<String> farmers, ArrayList<Double> kgs) {
        return farmers.size() + " deliveries, " + totalKg(kgs) + " kg in total";
    }

    static boolean looksLikeANumber(String text) {
        return text.matches("\\d+(\\.\\d+)?"); // digits, with an optional decimal part
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

        boolean open = true;
        while (open) {
            System.out.println("Choose 1-4:");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.println("Farmer's name?");
                String farmer = scanner.nextLine();
                System.out.println("Kilograms?");
                String weight = scanner.nextLine().trim();
                if (looksLikeANumber(weight) && record(farmers, kgs, farmer, Double.parseDouble(weight))) {
                    System.out.println("Recorded.");
                } else {
                    System.out.println("Not recorded: check the name and the weight.");
                }
            } else if (choice.equals("2")) {
                System.out.println("Farmer's name?");
                String farmer = scanner.nextLine().trim();
                int count = deliveriesBy(farmers, farmer);
                if (count == 0) {
                    System.out.println("No deliveries from " + farmer + " today.");
                } else {
                    // show the name as it was first recorded, e.g. "Wanjiru" even if the clerk typed "wanjiru"
                    String name = farmers.get(indexOfIgnoringCase(farmers, farmer));
                    System.out.println(name + ": " + count + " deliveries, " + totalFor(farmers, kgs, farmer) + " kg");
                }
            } else if (choice.equals("3")) {
                System.out.println(summary(farmers, kgs));
            } else if (choice.equals("4")) {
                open = false;
            } else {
                System.out.println("Please choose 1, 2, 3 or 4.");
            }
        }

        System.out.println("Station closed. Safe journey home!");
        scanner.close();
    }

    static int indexOfIgnoringCase(ArrayList<String> names, String name) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}
