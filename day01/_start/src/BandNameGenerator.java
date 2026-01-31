import java.util.Scanner;

public class BandNameGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Band Name Generator!");
        
        // Your code here
        System.out.println("What City Did You Grow up in?");
        String city = scanner.nextLine();

        System.out.println("What's your pet's name?");
        String petName = scanner.nextLine();
        
        String bandName = city + " " + petName;
        System.out.println("Your band name could be: " + bandName);
        
        scanner.close();
    }
}

