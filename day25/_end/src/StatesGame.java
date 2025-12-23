import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StatesGame {
    public static void main(String[] args) {
        ArrayList<String> states = new ArrayList<>();
        ArrayList<String> guessedStates = new ArrayList<>();
        
        try {
            // Read states from CSV
            File file = new File("50_states.csv");
            Scanner scanner = new Scanner(file);
            
            // Skip header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    states.add(parts[0].trim());
                }
            }
            scanner.close();
            
            // Game loop
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Welcome to the U.S. States Game!");
            System.out.println("Type 'exit' to quit.");
            
            while (guessedStates.size() < states.size()) {
                System.out.println("\nStates guessed: " + guessedStates.size() + "/" + states.size());
                System.out.print("Enter a state name: ");
                String guess = inputScanner.nextLine().trim();
                
                if (guess.equalsIgnoreCase("exit")) {
                    break;
                }
                
                if (states.contains(guess) && !guessedStates.contains(guess)) {
                    guessedStates.add(guess);
                    System.out.println("Correct! " + guess + " added.");
                } else if (guessedStates.contains(guess)) {
                    System.out.println("You already guessed " + guess + "!");
                } else {
                    System.out.println(guess + " is not a valid state or not in the list.");
                }
            }
            
            inputScanner.close();
            
            if (guessedStates.size() == states.size()) {
                System.out.println("\nCongratulations! You guessed all 50 states!");
            } else {
                System.out.println("\nGame ended. You guessed " + guessedStates.size() + " states.");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found. Please create '50_states.csv' with state names.");
        }
    }
}

