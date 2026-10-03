import java.util.Scanner;

public class CherrySorter {

    public static String verdict(String colour, boolean floats, boolean hasHoles) {
        // TODO: clean up the colour (trim, ignore case)
        // TODO: check the rules in order: holes, floats, red, green/yellow, black/brown
        return "CHECK BY HAND";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cherry Sorter ===");

        // TODO: ask "What colour is the cherry? (red, green, yellow, black...)"
        // TODO: ask "Did it float in the water? (yes/no)" and turn it into a boolean
        // TODO: ask "Does it have small holes? (yes/no)" and turn it into a boolean
        // TODO: print the verdict

        scanner.close();
    }
}
