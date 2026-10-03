import java.util.Scanner;

public class RainfallWeek {

    static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static double total(double[] mm) {
        double sum = 0;
        for (int i = 0; i < mm.length; i++) {
            sum = sum + mm[i];
        }
        return sum;
    }

    public static double average(double[] mm) {
        return total(mm) / mm.length;
    }

    public static String driestDay(double[] mm) {
        int driest = 0;
        for (int i = 1; i < mm.length; i++) {
            if (mm[i] < mm[driest]) {
                driest = i;
            }
        }
        return DAYS[driest];
    }

    public static int rainyDays(double[] mm, double minMm) {
        int count = 0;
        for (int i = 0; i < mm.length; i++) {
            if (mm[i] >= minMm) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Rainfall Week ===");

        double[] rain = new double[7];
        for (int i = 0; i < rain.length; i++) {
            System.out.println("Rain on " + DAYS[i] + " (mm)?");
            rain[i] = Double.parseDouble(scanner.nextLine().trim());
        }

        System.out.println("Total: " + total(rain) + " mm");
        System.out.println(String.format("Average: %.1f mm a day", average(rain)));
        System.out.println("Driest day: " + driestDay(rain));
        System.out.println("Rainy days: " + rainyDays(rain, 1.0));

        scanner.close();
    }
}
