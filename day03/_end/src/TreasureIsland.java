import java.util.Scanner;

public class TreasureIsland {

    public static String play(String choice1, String choice2, String choice3) {
        if (choice1.equals("left")) {
            if (choice2.equals("wait")) {
                if (choice3.equals("yellow")) {
                    return "You found the treasure! You Win!";
                } else if (choice3.equals("red")) {
                    return "It's a room full of fire. Game Over.";
                } else if (choice3.equals("blue")) {
                    return "You enter a room of beasts. Game Over.";
                } else {
                    return "You chose a door that doesn't exist. Game Over.";
                }
            } else {
                return "You get attacked by an angry trout. Game Over.";
            }
        } else {
            return "You fell into a hole. Game Over.";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Treasure Island.");
        System.out.println("Your mission is to find the treasure.");

        // First choice: left or right
        System.out.println("\nYou're at a cross road. Where do you want to go? Type \"left\" or \"right\"");
        String choice1 = scanner.nextLine().toLowerCase();

        String choice2 = "";
        String choice3 = "";

        if (choice1.equals("left")) {
            // Second choice: swim or wait
            System.out.println("You come to a lake. There is an island in the middle of the lake.");
            System.out.println("Type \"wait\" to wait for a boat. Type \"swim\" to swim across.");
            choice2 = scanner.nextLine().toLowerCase();

            if (choice2.equals("wait")) {
                // Third choice: door selection
                System.out.println("You arrive at the island unharmed. There is a house with 3 doors.");
                System.out.println("One red, one yellow and one blue. Which colour do you choose?");
                choice3 = scanner.nextLine().toLowerCase();
            }
        }

        System.out.println(play(choice1, choice2, choice3));

        scanner.close();
    }
}
