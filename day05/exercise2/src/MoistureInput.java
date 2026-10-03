import java.util.Scanner;

public class MoistureInput {

    public static double readMoisture(Scanner in) {
        // TODO: keep reading until there's a number from 0 to 100, and return it
        // TODO: print "Please type a number from 0 to 100." after every bad answer
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Moisture reading (%)?");
        System.out.println("Got " + readMoisture(scanner));
        scanner.close();
    }
}
