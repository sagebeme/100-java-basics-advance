import java.util.Scanner;

public class FarmerPayout {

    public static double grossPay(double kg, double ratePerKg) {
        return kg * ratePerKg;
    }

    public static double netPay(double kg, double ratePerKg, double levyPercent, double advance) {
        double gross = grossPay(kg, ratePerKg);
        double levy = gross * levyPercent / 100;
        return Math.max(0, gross - levy - advance);
    }

    public static void fillPayouts(double[] kgs, double ratePerKg, double levyPercent, double[] payouts) {
        for (int i = 0; i < kgs.length; i++) {
            payouts[i] = netPay(kgs[i], ratePerKg, levyPercent, 0); // changes the caller's array
        }
    }

    public static String payslip(String farmer, double kg, double ratePerKg, double levyPercent, double advance) {
        double gross = grossPay(kg, ratePerKg);
        double levy = gross * levyPercent / 100;
        return "Payslip for " + farmer + "\n"
                + String.format("Cherry: %.1f kg at KES %.2f = KES %,.2f\n", kg, ratePerKg, gross)
                + String.format("Levy (%.1f%%): KES %,.2f\n", levyPercent, levy)
                + String.format("Advance repaid: KES %,.2f\n", advance)
                + String.format("To pay: KES %,.2f", netPay(kg, ratePerKg, levyPercent, advance));
    }

    static double ask(Scanner scanner, String question) {
        System.out.println(question);
        return Double.parseDouble(scanner.nextLine().trim());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Farmer Payout ===");

        System.out.println("Farmer?");
        String farmer = scanner.nextLine().trim();
        double kg = ask(scanner, "Kilograms of cherry this season?");
        double rate = ask(scanner, "Rate per kg (KES)?");
        double levy = ask(scanner, "Levy (%)?");
        double advance = ask(scanner, "Advance to repay (KES)?");

        System.out.println(payslip(farmer, kg, rate, levy, advance));

        scanner.close();
    }
}
