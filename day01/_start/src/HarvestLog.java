import java.util.Scanner;

public class HarvestLog {

    // Builds one line of the weighing station's log book, e.g.
    // "Wanjiru delivered 23.5 kg of cherry. All ripe: yes"
    public static String logLine(String farmer, double kg, boolean allRipe) {
        // TODO: build and return the sentence. Show allRipe as "yes" or "no".
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Harvest Log ===");

        // TODO: ask "Farmer's name?" and store the answer in a String

        // TODO: ask "Kilograms of cherry picked?" and store it in a double (use Double.parseDouble)

        // TODO: ask "Were they all red and ripe? (yes/no)" and turn the answer into a boolean

        // TODO: print logLine(...) with your three variables

        scanner.close();
    }
}
