import java.util.Scanner;

public class BandNameGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Band Name Generator!");
        
        // Ask for city name
        System.out.println("What city did you grow up in?");
        String city = scanner.nextLine();
        
        // Ask for pet's name
        System.out.println("What's your pet's name?");
        String petName = scanner.nextLine();
        
        // Generate and display band name
        String bandName = city + " " + petName;
        System.out.println("Your band name could be: " + bandName);
        
        scanner.close();
    }
}

