import java.util.Scanner;

public class TipCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the tip calculator!");
        
        // Get total bill
        System.out.print("What was the total bill? $");
        double bill = scanner.nextDouble();
        
        // Get tip percentage
        System.out.print("What percentage tip would you like to give? 10, 12, or 15? ");
        int tipPercent = scanner.nextInt();
        
        // Get number of people
        System.out.print("How many people to split the bill? ");
        int people = scanner.nextInt();
        
        // Calculate tip amount
        double tipAmount = bill * (tipPercent / 100.0);
        
        // Calculate total per person
        double totalPerPerson = (bill + tipAmount) / people;
        
        // Format and display result
        System.out.printf("Each person should pay: $%.2f%n", totalPerPerson);
        
        scanner.close();
    }
}

