import java.util.Scanner;

public class TreasureIsland {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Treasure Island.");
        System.out.println("Your mission is to find the treasure.");
        
        // First choice: left or right
        System.out.println("\nYou're at a cross road. Where do you want to go? Type \"left\" or \"right\"");
        String choice1 = scanner.nextLine().toLowerCase();
        
        if (choice1.equals("left")) {
            // Second choice: swim or wait
            System.out.println("You come to a lake. There is an island in the middle of the lake.");
            System.out.println("Type \"wait\" to wait for a boat. Type \"swim\" to swim across.");
            String choice2 = scanner.nextLine().toLowerCase();
            
            if (choice2.equals("wait")) {
                // Third choice: door selection
                System.out.println("You arrive at the island unharmed. There is a house with 3 doors.");
                System.out.println("One red, one yellow and one blue. Which colour do you choose?");
                String choice3 = scanner.nextLine().toLowerCase();
                
                if (choice3.equals("yellow")) {
                    System.out.println("You found the treasure! You Win!");
                } else if (choice3.equals("red")) {
                    System.out.println("It's a room full of fire. Game Over.");
                } else if (choice3.equals("blue")) {
                    System.out.println("You enter a room of beasts. Game Over.");
                } else {
                    System.out.println("You chose a door that doesn't exist. Game Over.");
                }
            } else {
                System.out.println("You get attacked by an angry trout. Game Over.");
            }
        } else {
            System.out.println("You fell into a hole. Game Over.");
        }
        
        scanner.close();
    }
}

