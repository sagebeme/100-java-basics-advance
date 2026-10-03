import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuDecoder {

    public static String identify(int espressoMl, int waterMl, int milkMl, int foamMl) {
        if (espressoMl <= 0) return "Not coffee";
        if (milkMl == 0 && foamMl == 0) return waterMl > 0 ? "Americano" : "Espresso";
        if (milkMl == 0) return "Macchiato";
        if (foamMl >= milkMl * 0.75) return "Cappuccino";
        if (milkMl >= espressoMl * 4) return "Latte";
        return "Flat White";
    }

    public static Map<String, String> explanations() {
        Map<String, String> menu = new LinkedHashMap<>();
        menu.put("Espresso", "A small, strong shot of coffee, made by forcing hot water through finely ground beans. Two or three sips.");
        menu.put("Americano", "An espresso topped up with hot water. The closest thing on the menu to plain black coffee.");
        menu.put("Macchiato", "An espresso \"marked\" with a spoonful of milk foam. Still strong, with a soft top.");
        menu.put("Cappuccino", "Espresso with steamed milk and a thick, airy layer of foam. Light and frothy.");
        menu.put("Latte", "An espresso in a big cup of steamed milk with a thin layer of foam. The closest thing on the menu to milky chai.");
        menu.put("Flat White", "Espresso with a little steamed milk and almost no foam. Smoother than a cappuccino, stronger than a latte.");
        return menu;
    }

    public static String explain(String drink) {
        Map<String, String> menu = explanations();
        for (String name : menu.keySet()) {
            if (name.equalsIgnoreCase(drink.trim())) {
                return menu.get(name);
            }
        }
        return "Not on this menu. Ask the barista!";
    }

    public static String recommend(boolean likesMilk, boolean likesStrong) {
        if (likesMilk) {
            return likesStrong ? "Flat White" : "Latte";
        }
        return likesStrong ? "Espresso" : "Americano";
    }

    static boolean askYes(Scanner scanner, String question) {
        System.out.println(question + " (yes/no)");
        return scanner.nextLine().trim().equalsIgnoreCase("yes");
    }

    static int askMl(Scanner scanner, String what) {
        System.out.println("How many ml of " + what + "?");
        String answer = scanner.nextLine().trim();
        return answer.matches("\\d+") ? Integer.parseInt(answer) : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Menu Decoder ===");
        System.out.println("1. What's in my cup?");
        System.out.println("2. What does this word on the menu mean?");
        System.out.println("3. What should I order?");
        System.out.println("4. Leave the café");

        boolean inCafe = true;
        while (inCafe) {
            System.out.println("Choose 1-4:");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                int espresso = askMl(scanner, "espresso");
                int water = askMl(scanner, "hot water");
                int milk = askMl(scanner, "steamed milk");
                int foam = askMl(scanner, "foam");
                System.out.println("That's a " + identify(espresso, water, milk, foam) + ".");
            } else if (choice.equals("2")) {
                System.out.println("Which word? (" + String.join(", ", explanations().keySet()) + ")");
                System.out.println(explain(scanner.nextLine()));
            } else if (choice.equals("3")) {
                boolean milk = askYes(scanner, "Do you like milk in your drinks?");
                boolean strong = askYes(scanner, "Do you like your coffee strong?");
                String drink = recommend(milk, strong);
                System.out.println("Try a " + drink + ": " + explain(drink));
            } else if (choice.equals("4")) {
                inCafe = false;
            } else {
                System.out.println("Please choose 1, 2, 3 or 4.");
            }
        }

        System.out.println("Enjoy your coffee!");
        scanner.close();
    }
}
