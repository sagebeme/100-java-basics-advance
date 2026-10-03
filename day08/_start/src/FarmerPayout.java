import java.util.Scanner;

public class FarmerPayout {

    public static double grossPay(double kg, double ratePerKg) {
        // TODO
        return 0;
    }

    // Gross, minus the levy, minus the advance. Never below 0.
    public static double netPay(double kg, double ratePerKg, double levyPercent, double advance) {
        // TODO
        return 0;
    }

    // Writes each farmer's net pay (no advances) into payouts. Returns nothing.
    public static void fillPayouts(double[] kgs, double ratePerKg, double levyPercent, double[] payouts) {
        // TODO
    }

    // The five payslip lines from the README, joined with "\n"
    public static String payslip(String farmer, double kg, double ratePerKg, double levyPercent, double advance) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Farmer Payout ===");

        // TODO: ask for the farmer, kilograms, rate, levy and advance
        // TODO: print the payslip

        scanner.close();
    }
}
