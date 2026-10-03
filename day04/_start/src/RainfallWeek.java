import java.util.Scanner;

public class RainfallWeek {

    static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static double total(double[] mm) {
        // TODO: add up every reading
        return 0;
    }

    public static double average(double[] mm) {
        // TODO: the total divided by the number of readings
        return 0;
    }

    public static String driestDay(double[] mm) {
        // TODO: find the index of the smallest reading, then return DAYS at that index
        return "";
    }

    public static int rainyDays(double[] mm, double minMm) {
        // TODO: count the readings that are at least minMm
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Rainfall Week ===");

        // TODO: make a new double[7], and for each day ask "Rain on <day> (mm)?"
        // TODO: print the total, the average (one decimal place), the driest day and the rainy days (at least 1.0 mm)

        scanner.close();
    }
}
