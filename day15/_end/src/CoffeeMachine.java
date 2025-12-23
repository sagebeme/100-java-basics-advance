import java.util.Scanner;

public class CoffeeMachine {
    private static int water = 300;
    private static int milk = 200;
    private static int coffee = 100;
    private static double money = 0;
    
    public static void printReport() {
        System.out.println("Water: " + water + "ml");
        System.out.println("Milk: " + milk + "ml");
        System.out.println("Coffee: " + coffee + "g");
        System.out.println("Money: $" + String.format("%.2f", money));
    }
    
    public static boolean checkResources(int waterNeeded, int milkNeeded, int coffeeNeeded) {
        if (water < waterNeeded) {
            System.out.println("Sorry, there is not enough water.");
            return false;
        }
        if (milk < milkNeeded) {
            System.out.println("Sorry, there is not enough milk.");
            return false;
        }
        if (coffee < coffeeNeeded) {
            System.out.println("Sorry, there is not enough coffee.");
            return false;
        }
        return true;
    }
    
    public static void makeCoffee(String drink, int waterNeeded, int milkNeeded, int coffeeNeeded, double cost) {
        if (checkResources(waterNeeded, milkNeeded, coffeeNeeded)) {
            System.out.println("Please insert coins.");
            Scanner scanner = new Scanner(System.in);
            System.out.print("How many quarters? ");
            int quarters = scanner.nextInt();
            System.out.print("How many dimes? ");
            int dimes = scanner.nextInt();
            System.out.print("How many nickels? ");
            int nickels = scanner.nextInt();
            System.out.print("How many pennies? ");
            int pennies = scanner.nextInt();
            
            double total = quarters * 0.25 + dimes * 0.10 + nickels * 0.05 + pennies * 0.01;
            
            if (total >= cost) {
                water -= waterNeeded;
                milk -= milkNeeded;
                coffee -= coffeeNeeded;
                money += cost;
                double change = total - cost;
                System.out.println("Here is your " + drink + ". Enjoy!");
                if (change > 0) {
                    System.out.println("Here is $" + String.format("%.2f", change) + " in change.");
                }
            } else {
                System.out.println("Sorry, that's not enough money. Money refunded.");
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nWhat would you like? (espresso/latte/cappuccino):");
            System.out.println("Type 'report' to see resources or 'off' to turn off the machine.");
            String choice = scanner.nextLine().toLowerCase();
            
            if (choice.equals("off")) {
                break;
            } else if (choice.equals("report")) {
                printReport();
            } else if (choice.equals("espresso")) {
                makeCoffee("espresso", 50, 0, 18, 1.50);
            } else if (choice.equals("latte")) {
                makeCoffee("latte", 200, 150, 24, 2.50);
            } else if (choice.equals("cappuccino")) {
                makeCoffee("cappuccino", 250, 100, 24, 3.00);
            } else {
                System.out.println("Invalid choice!");
            }
        }
        
        scanner.close();
    }
}

