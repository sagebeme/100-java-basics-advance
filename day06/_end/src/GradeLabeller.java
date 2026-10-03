import java.util.Scanner;

public class GradeLabeller {

    public static String grade(int screen) {
        if (screen >= 17) {
            return "AA";
        } else if (screen >= 15) {
            return "AB";
        }
        return "C";
    }

    public static String grade(int screen, boolean peaberry) {
        if (peaberry) {
            return "PB";
        }
        return grade(screen);
    }

    public static String label(String factory, int screen, boolean peaberry) {
        String text = factory + " Factory | Grade " + grade(screen, peaberry);
        if (!peaberry) {
            text = text + " | screen " + screen;
        }
        return text;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Grade Labeller ===");

        System.out.println("Factory name?");
        String factory = scanner.nextLine().trim();
        System.out.println("Screen size?");
        int screen = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Is it a peaberry? (yes/no)");
        boolean peaberry = scanner.nextLine().trim().equalsIgnoreCase("yes");

        System.out.println(label(factory, screen, peaberry));

        scanner.close();
    }
}
