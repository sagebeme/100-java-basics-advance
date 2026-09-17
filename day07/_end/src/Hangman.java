import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Hangman {

    /**
     * @return true once every letter of the word has been guessed.
     */
    public static boolean isWordComplete(String word, ArrayList<Character> guessedLetters) {
        for (char letter : word.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Renders the word with unguessed letters shown as underscores, e.g. "j_v_" for "java".
     */
    public static String renderWord(String word, ArrayList<Character> guessedLetters) {
        StringBuilder display = new StringBuilder();
        for (char letter : word.toCharArray()) {
            display.append(guessedLetters.contains(letter) ? letter : '_').append(' ');
        }
        return display.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] wordList = {
            "java", "python", "computer", "programming",
            "algorithm", "software", "developer"
        };

        String word = wordList[random.nextInt(wordList.length)];
        ArrayList<Character> guessedLetters = new ArrayList<>();
        int lives = 6;

        System.out.println("Welcome to Hangman!");

        while (lives > 0) {
            // Display word with blanks
            System.out.println(renderWord(word, guessedLetters));

            if (isWordComplete(word, guessedLetters)) {
                System.out.println("You win! The word was: " + word);
                break;
            }

            System.out.println("You have " + lives + " lives remaining.");
            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter!");
                continue;
            }

            guessedLetters.add(guess);

            if (word.indexOf(guess) >= 0) {
                System.out.println("Good guess!");
            } else {
                System.out.println("Sorry, that letter is not in the word.");
                lives--;
            }
        }

        if (lives == 0) {
            System.out.println("You lose! The word was: " + word);
        }

        scanner.close();
    }
}
