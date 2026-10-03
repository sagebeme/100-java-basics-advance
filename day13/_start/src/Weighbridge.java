import java.util.Scanner;

// The mill's weighbridge program. A lorry of parchment is weighed full (gross) on the way in
// and empty (tare) on the way out; the coffee is the difference. It has three bugs.
public class Weighbridge {

    public static double netKg(double grossKg, double tareKg) {
        return grossKg - tareKg;
    }

    public static double totalNet(double[] grossKg, double[] tareKg) {
        double total = 0;
        for (int i = 0; i <= grossKg.length; i++) {
            total += netKg(grossKg[i], tareKg[i]);
        }
        return total;
    }

    public static double totalFor(String[] factories, double[] netKg, String factory) {
        double total = 0;
        for (int i = 0; i < factories.length; i++) {
            if (factories[i] == factory) {
                total += netKg[i];
            }
        }
        return total;
    }

    public static double averageNet(double[] netKg) {
        int total = 0;
        for (double kg : netKg) {
            total += kg;
        }
        return total / netKg.length;
    }

    public static void main(String[] args) {
        String[] factories = {"Othaya", "Karindundu", "Othaya"};
        double[] gross = {9120.5, 8450.0, 9900.0};
        double[] tare = {5200.0, 5150.0, 5210.0};

        double[] net = new double[gross.length];
        for (int i = 0; i < gross.length; i++) {
            net[i] = netKg(gross[i], tare[i]);
        }

        System.out.println("=== Weighbridge ===");
        System.out.println("Total coffee in: " + totalNet(gross, tare) + " kg");
        System.out.println("Average per lorry: " + averageNet(net) + " kg");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which factory's total?");
        String factory = scanner.nextLine().trim();
        System.out.println(factory + ": " + totalFor(factories, net, factory) + " kg");
        scanner.close();
    }
}
