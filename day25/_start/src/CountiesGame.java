import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// How many of Kenya's 47 counties can you name? The counties come from counties.csv.
public class CountiesGame {

    public enum GuessResult { CORRECT, ALREADY_GUESSED, INVALID }

    // TODO: return the county as the list spells it ("Tana River"), whatever capitals and spaces
    // the player typed ("  tana river "). Return null if it isn't in the list.
    public static String spelling(String guess, List<String> counties) {
        return null;
    }

    // TODO: INVALID if it isn't a county, ALREADY_GUESSED if it's in guessed, otherwise CORRECT.
    public static GuessResult evaluateGuess(String guess, List<String> counties, List<String> guessed) {
        return GuessResult.INVALID;
    }

    // TODO: read counties.csv. Skip the header line ("code,county"), then add the second column
    // of every line to the list.
    public static List<String> readCounties(File file) throws FileNotFoundException {
        List<String> counties = new ArrayList<>();
        return counties;
    }

    public static void main(String[] args) {
        // TODO: read the counties, then loop: ask for a county, say whether it's right, already
        // named or not a county, until all 47 are named or the player types "exit".
        // At the end, print how many they named and the ones they missed.
        Scanner input = new Scanner(System.in);
        System.out.println("How many of Kenya's 47 counties can you name?");
    }
}
