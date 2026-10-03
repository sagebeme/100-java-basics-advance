import java.util.Scanner;

public class GradeLabeller {

    // AA for screen 17 and up, AB for 15-16, C below 15
    public static String grade(int screen) {
        // TODO
        return "";
    }

    // PB for a peaberry, otherwise the same as grade(screen)
    public static String grade(int screen, boolean peaberry) {
        // TODO: don't copy the size rules, call grade(screen)
        return "";
    }

    // "Othaya Hills Factory | Grade AA | screen 17", or "Othaya Hills Factory | Grade PB" for a peaberry
    public static String label(String factory, int screen, boolean peaberry) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Grade Labeller ===");

        // TODO: ask "Factory name?", "Screen size?" and "Is it a peaberry? (yes/no)"
        // TODO: print the label

        scanner.close();
    }
}
