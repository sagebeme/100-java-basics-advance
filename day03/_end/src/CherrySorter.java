import java.util.Scanner;

public class CherrySorter {

    public static String verdict(String colour, boolean floats, boolean hasHoles) {
        String c = colour.trim().toLowerCase();

        if (hasHoles) {
            return "REJECT: insect damage";
        }
        if (floats) {
            return "REJECT: floater";
        }
        if (c.equals("red")) {
            return "ACCEPT";
        } else if (c.equals("green") || c.equals("yellow")) {
            return "REJECT: unripe";
        } else if (c.equals("black") || c.equals("brown")) {
            return "REJECT: overripe";
        } else {
            return "CHECK BY HAND";
        }
    }

    static boolean isYes(String answer) {
        return answer.trim().equalsIgnoreCase("yes");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cherry Sorter ===");

        System.out.println("What colour is the cherry? (red, green, yellow, black...)");
        String colour = scanner.nextLine();

        System.out.println("Did it float in the water? (yes/no)");
        boolean floats = isYes(scanner.nextLine());

        System.out.println("Does it have small holes? (yes/no)");
        boolean hasHoles = isYes(scanner.nextLine());

        System.out.println(verdict(colour, floats, hasHoles));

        scanner.close();
    }
}
