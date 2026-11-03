import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// How many of Kenya's 47 counties can you name? The counties come from counties.csv.
public class CountiesGame {

    public enum GuessResult { CORRECT, ALREADY_GUESSED, INVALID }

    // The county's name as the list spells it, whatever capitals the player typed, or null.
    public static String spelling(String guess, List<String> counties) {
        for (String county : counties) {
            if (county.equalsIgnoreCase(guess.trim())) {
                return county;
            }
        }
        return null;
    }

    public static GuessResult evaluateGuess(String guess, List<String> counties, List<String> guessed) {
        String county = spelling(guess, counties);
        if (county == null) {
            return GuessResult.INVALID;
        }
        if (guessed.contains(county)) {
            return GuessResult.ALREADY_GUESSED;
        }
        return GuessResult.CORRECT;
    }

    // Reads the county column (the second one) of counties.csv, skipping the header line.
    public static List<String> readCounties(File file) throws FileNotFoundException {
        List<String> counties = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length >= 2 && !parts[1].isBlank()) {
                    counties.add(parts[1].trim());
                }
            }
        }
        return counties;
    }

    public static void main(String[] args) {
        List<String> counties;
        try {
            counties = readCounties(new File("counties.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("counties.csv not found. Run the game from the folder that holds it.");
            return;
        }

        List<String> guessed = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("How many of Kenya's " + counties.size() + " counties can you name?");
        System.out.println("Type 'exit' to stop.");

        while (guessed.size() < counties.size()) {
            System.out.print("\n" + guessed.size() + "/" + counties.size() + " named. Next county: ");
            if (!input.hasNextLine()) {
                break;
            }
            String guess = input.nextLine().trim();
            if (guess.equalsIgnoreCase("exit")) {
                break;
            }

            switch (evaluateGuess(guess, counties, guessed)) {
                case CORRECT -> {
                    String county = spelling(guess, counties);
                    guessed.add(county);
                    System.out.println("Yes! " + county + ".");
                }
                case ALREADY_GUESSED -> System.out.println("You already named " + spelling(guess, counties) + ".");
                case INVALID -> System.out.println(guess + " isn't one of the 47 counties.");
            }
        }

        if (guessed.size() == counties.size()) {
            System.out.println("\nAll " + counties.size() + " counties! Hongera!");
        } else {
            List<String> missed = new ArrayList<>(counties);
            missed.removeAll(guessed);
            System.out.println("\nYou named " + guessed.size() + ". You missed: " + String.join(", ", missed));
        }
    }
}
