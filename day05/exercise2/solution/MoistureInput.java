import java.util.Scanner;

public class MoistureInput {

    public static double readMoisture(Scanner in) {
        while (true) {
            if (in.hasNextDouble()) {
                double value = in.nextDouble();
                if (value >= 0 && value <= 100) {
                    return value;
                }
            } else {
                in.next(); // throw away the word that wasn't a number
            }
            System.out.println("Please type a number from 0 to 100.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Moisture reading (%)?");
        System.out.println("Got " + readMoisture(scanner));
        scanner.close();
    }
}
