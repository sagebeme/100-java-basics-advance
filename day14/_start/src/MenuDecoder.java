import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuDecoder {

    // Follow the README's rules table, in order
    public static String identify(int espressoMl, int waterMl, int milkMl, int foamMl) {
        // TODO
        return "";
    }

    // The six drinks, in menu order, each with a plain-language description
    public static Map<String, String> explanations() {
        Map<String, String> menu = new LinkedHashMap<>();
        // TODO: Espresso, Americano, Macchiato, Cappuccino, Latte, Flat White
        return menu;
    }

    // Ignores capitals. "Not on this menu. Ask the barista!" for anything else.
    public static String explain(String drink) {
        // TODO
        return "";
    }

    public static String recommend(boolean likesMilk, boolean likesStrong) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Menu Decoder ===");

        // TODO: the menu from the README, in a loop until option 4

        scanner.close();
    }
}
