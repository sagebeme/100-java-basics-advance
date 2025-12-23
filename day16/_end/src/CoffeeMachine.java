import java.util.Scanner;

public class CoffeeMachine {
    private ResourceManager resources;
    private double money;
    private Scanner scanner;
    
    public CoffeeMachine() {
        this.resources = new ResourceManager(300, 200, 100);
        this.money = 0;
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        System.out.println("\nWhat would you like? (espresso/latte/cappuccino):");
        System.out.println("Type 'report' to see resources or 'off' to turn off the machine.");
    }
    
    public void processPayment(double cost) {
        System.out.println("Please insert coins.");
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
            money += cost;
            double change = total - cost;
            if (change > 0) {
                System.out.println("Here is $" + String.format("%.2f", change) + " in change.");
            }
        } else {
            System.out.println("Sorry, that's not enough money. Money refunded.");
        }
    }
    
    public void makeCoffee(Coffee coffee) {
        if (resources.checkResources(coffee.getWater(), coffee.getMilk(), coffee.getCoffee())) {
            processPayment(coffee.getCost());
            if (money >= coffee.getCost()) {
                resources.useResources(coffee.getWater(), coffee.getMilk(), coffee.getCoffee());
                System.out.println("Here is your " + coffee.getName() + ". Enjoy!");
            }
        }
    }
    
    public void showReport() {
        resources.displayReport();
        System.out.println("Money: $" + String.format("%.2f", money));
    }
    
    public void run() {
        Coffee espresso = new Coffee("espresso", 50, 0, 18, 1.50);
        Coffee latte = new Coffee("latte", 200, 150, 24, 2.50);
        Coffee cappuccino = new Coffee("cappuccino", 250, 100, 24, 3.00);
        
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().toLowerCase();
            
            if (choice.equals("off")) {
                break;
            } else if (choice.equals("report")) {
                showReport();
            } else if (choice.equals("espresso")) {
                makeCoffee(espresso);
            } else if (choice.equals("latte")) {
                makeCoffee(latte);
            } else if (choice.equals("cappuccino")) {
                makeCoffee(cappuccino);
            } else {
                System.out.println("Invalid choice!");
            }
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        machine.run();
    }
}

